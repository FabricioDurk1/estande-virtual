package com.ufc.pi.webservice.repositories;

import java.util.List;

import com.ufc.pi.webservice.models.Book;
import java.util.Optional;


public interface BookRepository {
    List<Book> findAll();
    void create(Book book); 
    Optional<Book> findById(Long id);  
    void update(Book book); 
}
