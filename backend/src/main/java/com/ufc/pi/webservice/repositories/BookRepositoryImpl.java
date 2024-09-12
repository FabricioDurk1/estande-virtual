package com.ufc.pi.webservice.repositories;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
        String parameterizedCommand = "SELECT books.*, authors.name as author_name, publishers.name as publisher_name  FROM books";
        parameterizedCommand += " JOIN authors ON authors.id = books.author_id";
        parameterizedCommand += " JOIN publishers ON publishers.id = books.publisher_id";

        var result = jdbcTemplate.query(parameterizedCommand, this.bookMapper);

        return result;
    }

    
}
