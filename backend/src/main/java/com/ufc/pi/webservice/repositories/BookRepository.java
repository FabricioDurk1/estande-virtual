package com.ufc.pi.webservice.repositories;

import java.util.List;

import com.ufc.pi.webservice.models.Book;

public interface BookRepository {
    List<Book> findAll();
}
