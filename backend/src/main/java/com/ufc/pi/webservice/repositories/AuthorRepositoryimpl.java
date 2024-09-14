package com.ufc.pi.webservice.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.ufc.pi.webservice.database.AuthorMapper;
import com.ufc.pi.webservice.database.AuthorTable;
import com.ufc.pi.webservice.models.Author;
import com.ufc.pi.webservice.utils.ParamReplacer;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class AuthorRepositoryimpl implements AuthorRespository {
    private final JdbcTemplate jdbcTemplate;
    private final AuthorMapper authorMapper;

    @Override
    public void create(Author author) {
        final String PARAMETERIZED_COMMAND = "INSERT INTO :tableName (:nameColumn) VALUES (?) ";

        HashMap<String, String> tableStructureParams = new HashMap<>();
        tableStructureParams.put("tableName", AuthorTable.TABLE_NAME);
        tableStructureParams.put("nameColumn", AuthorTable.NAME_COLUMN);

        final String tableStructuredCommand = ParamReplacer.fillParams(PARAMETERIZED_COMMAND, tableStructureParams);

        PreparedStatementSetter queryDynamicParamsSetter = ps -> ps.setString(1, author.getName());

        jdbcTemplate.update(tableStructuredCommand, queryDynamicParamsSetter);
    }

    @Override
    public void update(Author author) {
        final String PARAMETERIZED_COMMAND = "UPDATE :tableName SET :nameColumn = ? WHERE id = ?";

        HashMap<String, String> tableStructureParams = new HashMap<>();
        tableStructureParams.put("tableName", AuthorTable.TABLE_NAME);
        tableStructureParams.put("nameColumn", AuthorTable.NAME_COLUMN);

        final String tableStructuredCommand = ParamReplacer.fillParams(PARAMETERIZED_COMMAND, tableStructureParams);

        PreparedStatementSetter queryDynamicParamsSetter = ps -> {
            ps.setString(1, author.getName());
            ps.setLong(2, author.getId());
        };

        jdbcTemplate.update(tableStructuredCommand, queryDynamicParamsSetter);
    }

    @Override
    public Optional<Author> findById(Long id) {
        final String PARAMETERIZED_COMMAND = "SELECT * FROM :tableName WHERE :idColumn = ?";

        HashMap<String, String> tableStructureParams = new HashMap<>();
        tableStructureParams.put("tableName", AuthorTable.TABLE_NAME);
        tableStructureParams.put("idColumn", AuthorTable.ID_COLUMN);

        final String tableStructuredCommand = ParamReplacer.fillParams(PARAMETERIZED_COMMAND, tableStructureParams);

        PreparedStatementSetter queryDynamicParamsSetter = ps -> ps.setLong(1, id);

        var result = jdbcTemplate.query(tableStructuredCommand, queryDynamicParamsSetter, this.authorMapper);

        if (result.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(result.get(0));
    }

    @Override
    public List<Author> findAll() {
        final String PARAMETERIZED_COMMAND = "SELECT * FROM :tableName";

        HashMap<String, String> tableStructureParams = new HashMap<>();
        tableStructureParams.put("tableName", AuthorTable.TABLE_NAME);

        final String tableStructuredCommand = ParamReplacer.fillParams(PARAMETERIZED_COMMAND, tableStructureParams);

        return jdbcTemplate.query(tableStructuredCommand, this.authorMapper);
    }
}


