package com.ufc.pi.webservice.repositories;

import com.ufc.pi.webservice.enums.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.ufc.pi.webservice.models.Address;
import com.ufc.pi.webservice.models.User;
import com.ufc.pi.webservice.data.structures.DoublyLinkedList;
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
  public DoublyLinkedList<User> findByEmail(String email) {
    final String query =
      "SELECT * FROM users WHERE email = ?";

    PreparedStatementSetter queryDynamicParamsSetter = new PreparedStatementSetter() {
      @Override
      public void setValues(java.sql.PreparedStatement ps) throws java.sql.SQLException {
        ps.setString(1, email);
      }
    };

    DoublyLinkedList<User> users = new DoublyLinkedList<>();

    jdbcTemplate.query(query, queryDynamicParamsSetter, (rs, rowNum) -> {
      User user = mapRowToUser(rs, rowNum);
      users.addNode(user);
      return user;
    });

    return users;
  }

  @Override
  public void create(User user) {
    String query = "INSERT INTO users (name, email, password, phone_number, cpf, birth_date, role) VALUES (?, ?, ?, ?, ?, ?, ?)";

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
      query,
      queryDynamicParamsSetter
    );
  }

  @Override
  public void update(User user) {
    final String query = "UPDATE users SET name = ?, email = ?, password = ?, phone_number = ?, cpf = ?, birth_date = ? WHERE id = ?";

    PreparedStatementSetter queryDynamicParamsSetter = new PreparedStatementSetter() {
      @Override
      public void setValues(java.sql.PreparedStatement ps) throws java.sql.SQLException {
        ps.setString(1, user.getName());
        ps.setString(2, user.getEmail());
        ps.setString(3, user.getPassword());
        ps.setString(4, user.getPhoneNumber());
        ps.setString(5, user.getCpf());
        ps.setString(6, user.getBirthDate().toString());
        ps.setLong(7, user.getId());
      }
    };
    
    jdbcTemplate.update(
      query,
      queryDynamicParamsSetter
    );
  }

  @Override
  public void updateAddress(Address address, long userId) {
    final String query = "UPDATE users SET address_street = ?, address_number = ?, address_complement = ?, address_neighborhood = ?, address_city = ?, address_state = ?, address_zip_code = ? WHERE id = ?";
  
    PreparedStatementSetter queryDynamicParamsSetter = new PreparedStatementSetter() {  
      @Override
      public void setValues(java.sql.PreparedStatement ps) throws java.sql.SQLException {
        ps.setString(1, address.getStreet());
        ps.setString(2, address.getNumber());
        ps.setString(3, address.getComplement());
        ps.setString(4, address.getNeighborhood());
        ps.setString(5, address.getCity());
        ps.setString(6, address.getState());
        ps.setString(7, address.getZipCode());
        ps.setLong(8, userId);
      }
    };

    jdbcTemplate.update(
      query,
      queryDynamicParamsSetter
    );
  }

  @Override
  public DoublyLinkedList<User> findById(long id) {
    final String query = "SELECT * FROM users WHERE id = ?";

    PreparedStatementSetter queryDynamicParamsSetter = new PreparedStatementSetter() {
      @Override 
      public void setValues(java.sql.PreparedStatement ps) throws java.sql.SQLException {
        ps.setLong(1, id);
      }
    };

    DoublyLinkedList<User> users = new DoublyLinkedList<>();

    jdbcTemplate.query(query, queryDynamicParamsSetter, (rs, rowNum) -> {
      User user = mapRowToUser(rs, rowNum);
      users.addNode(user);
      return user;
    });

    return users;
  }

  public User mapRowToUser(ResultSet rs, int rowNum) throws SQLException {
    User user = User.builder()
      .id(rs.getLong("id"))
      .name(rs.getString("name"))
      .email(rs.getString("email"))
      .password(rs.getString("password"))
      .phoneNumber(rs.getString("phone_number"))
      .cpf(rs.getString("cpf"))
      .birthDate(LocalDate.parse(rs.getString("birth_date")))
      .role(UserRole.valueOf(rs.getString("role")))
      .build();

    if(rs.getString("address_zip_code") == null) {
      user.setAddress(null);
    } else{
      Address address = new Address();

      address.setStreet(rs.getString("address_street"));
      address.setNumber(rs.getString("address_number"));
      address.setComplement(rs.getString("address_complement"));
      address.setNeighborhood(rs.getString("address_neighborhood"));
      address.setCity(rs.getString("address_city"));
      address.setState(rs.getString("address_state"));
      address.setZipCode(rs.getString("address_zip_code"));
      
      user.setAddress(address);
    }

    return user;
  }

  @Override
  public DoublyLinkedList<User> findByCPFAndNotId(String cpf, long id) {
    
    final String query = "SELECT * FROM users WHERE cpf = ? AND id != ?";

    PreparedStatementSetter queryDynamicParamsSetter = new PreparedStatementSetter() {
      @Override
      public void setValues(java.sql.PreparedStatement ps) throws java.sql.SQLException {
        ps.setString(1, cpf);
        ps.setLong(2, id);
      }
    };

    DoublyLinkedList<User> users = new DoublyLinkedList<>();

    jdbcTemplate.query(query, queryDynamicParamsSetter, (rs, rowNum) -> {
      User user = mapRowToUser(rs, rowNum);
      users.addNode(user);
      return user;
    });

    return users;
  }
}
