package com.ufc.pi.webservice.repositories;

import java.sql.ResultSet;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.ufc.pi.webservice.data.structures.DoublyLinkedList;
import com.ufc.pi.webservice.database.BookMapper;
import com.ufc.pi.webservice.models.Author;
import com.ufc.pi.webservice.models.Book;
import com.ufc.pi.webservice.models.Publisher;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class BookRepositoryImpl implements BookRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public DoublyLinkedList<Book> findAll() {
        String parameterizedCommand = "SELECT books.*, authors.name AS author_name, publishers.name AS publisher_name " +
                                      "FROM books " +
                                      "JOIN authors ON authors.id = books.author_id " +
                                      "JOIN publishers ON publishers.id = books.publisher_id";
    
        DoublyLinkedList<Book> books = new DoublyLinkedList<>();

        jdbcTemplate.query(parameterizedCommand, (rs, rowNum) -> {
            Book book = mapRowToBook(rs, rowNum);
            books.addNode(book);
            return book;
        });

        return books;
    }

    @Override
    public void create(Book book) {
        String sql = "INSERT INTO books (title, description, price, quantity, author_id, publisher_id) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, book.getTitle(), book.getDescription(), book.getPrice(), book.getQuantity(),
                            book.getAuthor().getId(), book.getPublisher().getId());
    }

    @Override
    public DoublyLinkedList<Book> findById(Long id) {
        String sql = "SELECT books.*, authors.name as author_name, publishers.name as publisher_name " +
                    "FROM books " +
                    "JOIN authors ON authors.id = books.author_id " +
                    "JOIN publishers ON publishers.id = books.publisher_id " +
                    "WHERE books.id = ?";

        PreparedStatementSetter queryDynamicParamsSetter = ps -> {
            ps.setLong(1, id);
        };

        DoublyLinkedList<Book> books = new DoublyLinkedList<>();

        jdbcTemplate.query(sql, queryDynamicParamsSetter, (rs, rowNum) -> {
            Book book = mapRowToBook(rs, rowNum);
            books.addNode(book);
            return book;
        });

        return books;
    }

    @Override
    public void update(Book book) {
        String sql = "UPDATE books SET title = ?, description = ?, price = ?, quantity = ?, author_id = ?, publisher_id = ? WHERE id = ?";

        jdbcTemplate.update(sql, 
            book.getTitle(), 
            book.getDescription(), 
            book.getPrice(), 
            book.getQuantity(), 
            book.getAuthor().getId(), 
            book.getPublisher().getId(), 
            book.getId()
        );
    }

    
    public Book mapRowToBook(ResultSet rs, int rowNum) throws java.sql.SQLException {
        Book entity = new Book();

        entity.setId(rs.getLong("id"));
        entity.setTitle(rs.getString("title"));
        entity.setDescription(rs.getString("description"));
        entity.setPrice(rs.getDouble("price"));
        entity.setQuantity(rs.getInt("quantity"));

        Publisher publisher = new Publisher();
        publisher.setId(rs.getLong("publisher_id"));
        publisher.setName(rs.getString("publisher_name"));
        entity.setPublisher(publisher);

        Author author = new Author();
        author.setId(rs.getLong("author_id"));
        author.setName(rs.getString("author_name"));
        entity.setAuthor(author);
    
        return entity;
    }
}

