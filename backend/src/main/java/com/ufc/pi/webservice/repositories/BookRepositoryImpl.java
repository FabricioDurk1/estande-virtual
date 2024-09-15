package com.ufc.pi.webservice.repositories;

import java.util.HashMap;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import com.ufc.pi.webservice.database.BookMapper;
import com.ufc.pi.webservice.models.Book;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class BookRepositoryImpl implements BookRepository {
    private final JdbcTemplate jdbcTemplate;
    private final BookMapper bookMapper;

    @Override
    public List<Book> findAll() {
        String parameterizedCommand = "SELECT books.*, authors.name AS author_name, publishers.name AS publisher_name " +
                                      "FROM books " +
                                      "JOIN authors ON authors.id = books.author_id " +
                                      "JOIN publishers ON publishers.id = books.publisher_id";
    
        return jdbcTemplate.query(parameterizedCommand, bookMapper);
    }

    @Override
    public void create(Book book) {
        String sql = "INSERT INTO books (title, description, price, quantity, author_id, publisher_id) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, book.getTitle(), book.getDescription(), book.getPrice(), book.getQuantity(),
                            book.getAuthor().getId(), book.getPublisher().getId());
    }

@Override
public Optional<Book> findById(Long id) {
    String sql = "SELECT books.*, authors.name as author_name, publishers.name as publisher_name " +
                 "FROM books " +
                 "JOIN authors ON authors.id = books.author_id " +
                 "JOIN publishers ON publishers.id = books.publisher_id " +
                 "WHERE books.id = ?";

    PreparedStatementSetter queryDynamicParamsSetter = ps -> {
        ps.setLong(1, id);
    };

    List<Book> result = jdbcTemplate.query(sql, queryDynamicParamsSetter, bookMapper);

    if (result.isEmpty()) {
        return Optional.empty();
    }

    return Optional.of(result.get(0));
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
}

