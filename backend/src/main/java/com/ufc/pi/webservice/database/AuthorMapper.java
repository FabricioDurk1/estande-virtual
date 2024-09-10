package com.ufc.pi.webservice.database;


import com.ufc.pi.webservice.models.Author;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;


@Component
public class AuthorMapper implements RowMapper<Author> {

  @Override
  public Author mapRow(ResultSet resultSet, int rowNum) throws SQLException {
    Long id = resultSet.getLong(AuthorTable.ID_COLUMN);
    String name = resultSet.getString(AuthorTable.NAME_COLUMN);

    Author author = new Author();

    author.setId(id);
    author.setName(name);

    return author;
  }

}
