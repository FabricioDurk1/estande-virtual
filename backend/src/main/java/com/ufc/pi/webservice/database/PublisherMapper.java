package com.ufc.pi.webservice.database;

import com.ufc.pi.webservice.models.Publisher;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class PublisherMapper implements RowMapper<Publisher> {

  @Override
  public Publisher mapRow(ResultSet resultSet, int rowNum) throws SQLException {
    Long id = resultSet.getLong(PublisherTable.ID_COLUMN);
    String name = resultSet.getString(PublisherTable.NAME_COLUMN);

    Publisher publisher = new Publisher();

    publisher.setId(id);
    publisher.setName(name);

    return publisher;
  }

}
