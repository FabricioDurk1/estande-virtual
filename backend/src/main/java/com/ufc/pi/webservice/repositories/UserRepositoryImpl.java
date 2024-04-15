package com.ufc.pi.webservice.repositories;

import com.ufc.pi.webservice.database.UserTable;
import com.ufc.pi.webservice.utils.ParamReplacer;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.ufc.pi.webservice.models.User;
import com.ufc.pi.webservice.database.UserMapper;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepository {
  private final JdbcTemplate jdbcTemplate;
  private final UserMapper userMapper;

  public List<User> getAll(int id) {
    String query = "SELECT * FROM users WHERE id = ?";

    PreparedStatementSetter preparedStatementSetter = new PreparedStatementSetter() {
      @Override
      public void setValues(PreparedStatement ps) throws SQLException {
        ps.setLong(1, id);
      }
    };

    var result = jdbcTemplate.query(query, preparedStatementSetter, this.userMapper);

    return result;
  }

  @Override
  public Optional<User> findByEmail(String email) {
    final String PARAMETERIZED_COMMAND =
      "SELECT * FROM :tableName WHERE :emailColumn = ?";

    HashMap<String, String> tableStructureParams = new HashMap<>();

    tableStructureParams.put("tableName", UserTable.TABLE_NAME);
    tableStructureParams.put("emailColumn", UserTable.EMAIL_COLUMN);

    final String tableStructuredCommand = ParamReplacer.fillParams(PARAMETERIZED_COMMAND, tableStructureParams);

    PreparedStatementSetter queryDynamicParamsSetter = new PreparedStatementSetter() {
      @Override
      public void setValues(java.sql.PreparedStatement ps) throws java.sql.SQLException {
        ps.setString(1, email);
      }
    };

    var result = jdbcTemplate.query(tableStructuredCommand, queryDynamicParamsSetter, this.userMapper);

    if (result.isEmpty()) {
      return Optional.empty();
    }

    return Optional.of(result.get(0));
  }

  @Override
  public void create(User user) {
    String tableStructuredCommand = this.generateCreateUserStructuredCommand();

    System.out.println(user);

    PreparedStatementSetter queryDynamicParamsSetter = new PreparedStatementSetter() {
      @Override
      public void setValues(java.sql.PreparedStatement ps) throws java.sql.SQLException {
        ps.setString(1, user.getName());
        ps.setString(2, user.getEmail());
        ps.setString(3, user.getPassword());
        ps.setString(4, user.getPhoneNumber());
        ps.setString(5, user.getCpf());
        ps.setString(6, user.getBirthDate().toString());
        ps.setString(7, user.getRole().toString());
      }
    };

    jdbcTemplate.update(
      tableStructuredCommand,
      queryDynamicParamsSetter
    );
  }

  private String generateCreateUserStructuredCommand() {
    HashMap<String, String> tableStructureParams = new HashMap<>();

    tableStructureParams.put("tableName", UserTable.TABLE_NAME);
    tableStructureParams.put("nameColumn", UserTable.NAME_COLUMN);
    tableStructureParams.put("emailColumn", UserTable.EMAIL_COLUMN);
    tableStructureParams.put("passwordColumn", UserTable.PASSWORD_COLUMN);
    tableStructureParams.put("phoneNumberColumn", UserTable.PHONE_NUMBER_COLUMN);
    tableStructureParams.put("cpfColumn", UserTable.CPF_COLUMN);
    tableStructureParams.put("birthDateColumn", UserTable.BIRTH_DATE_COLUMN);
    tableStructureParams.put("roleColumn", UserTable.ROLE_COLUMN);

    return ParamReplacer.fillParams("INSERT INTO :tableName (:nameColumn, :emailColumn, :passwordColumn, :phoneNumberColumn, :cpfColumn, :birthDateColumn, :roleColumn) VALUES (?, ?, ?, ?, ?, ?, ?) ", tableStructureParams);
  }
}
