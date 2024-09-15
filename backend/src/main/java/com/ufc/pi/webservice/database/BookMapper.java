package com.ufc.pi.webservice.database;

import com.ufc.pi.webservice.models.Author;
import com.ufc.pi.webservice.models.Book;
import com.ufc.pi.webservice.models.Publisher;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class BookMapper implements RowMapper<Book> {

  @Override
  public Book mapRow(ResultSet resultSet, int rowNum) throws SQLException {
    Long id = resultSet.getLong(BookTable.ID_COLUMN);
    String title = resultSet.getString(BookTable.TITLE_COLUMN);
    String description = resultSet.getString(BookTable.DESCRIPTION_COLUMN);
    double price = resultSet.getDouble(BookTable.PRICE_COLUMN);
    int quantity = resultSet.getInt(BookTable.QUANTITY_COLUMN);

    Long publisherId = resultSet.getLong(BookTable.PUBLISHER_ID_COLUMN);
    String publisherName = resultSet.getString("publisher_name");

    Long authorId = resultSet.getLong(BookTable.AUTHOR_ID_COLUMN);
    String authorName = resultSet.getString("author_name");

    Book book = new Book();
    book.setId(id);
    book.setTitle(title);
    book.setDescription(description);
    book.setPrice(price);
    book.setQuantity(quantity);

    Publisher publisher = new Publisher();
    publisher.setId(publisherId);
    publisher.setName(publisherName);
    book.setPublisher(publisher);

    Author author = new Author();
    author.setId(authorId);
    author.setName(authorName);
    book.setAuthor(author);
    
    return book;
  }

}