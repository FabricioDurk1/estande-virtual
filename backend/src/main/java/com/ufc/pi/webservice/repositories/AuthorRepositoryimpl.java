package com.ufc.pi.webservice.repositories;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.ufc.pi.webservice.data.structures.DoublyLinkedList;
import com.ufc.pi.webservice.database.AuthorMapper;
import com.ufc.pi.webservice.database.AuthorTable;
import com.ufc.pi.webservice.models.Author;
import com.ufc.pi.webservice.models.CreditCard;
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
    public DoublyLinkedList<Author> findById(Long id) {
        String sql = "SELECT * FROM authors WHERE id = ?";

        PreparedStatementSetter queryDynamicParamsSetter = ps -> {  
            ps.setLong(1, id);
        };

        DoublyLinkedList<Author> authors = new DoublyLinkedList<>();

        jdbcTemplate.query(sql, queryDynamicParamsSetter, (rs, rowNum) -> {
            Author author = mapRowToAuthor(rs, rowNum);
            authors.addNode(author);
            return author;
        });

        return authors;
    }

    @Override
    public DoublyLinkedList<Author> findAll() {
        final String PARAMETERIZED_COMMAND = "SELECT * FROM authors";


        DoublyLinkedList<Author> authors = new DoublyLinkedList<>();

        jdbcTemplate.query(PARAMETERIZED_COMMAND, (rs, rowNum) -> {
            Author author = mapRowToAuthor(rs, rowNum);
            authors.addNode(author);
            return author;
        });

        return authors;
    }

    public Author mapRowToAuthor(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
        Author entity = new Author();

        entity.setId(rs.getLong("id"));
        entity.setName(rs.getString("name"));
        
        return entity;
    }
}


