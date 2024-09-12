package com.ufc.pi.webservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufc.pi.webservice.models.Book;
import com.ufc.pi.webservice.repositories.BookRepository;

@Service
public class BookService {
 
  @Autowired
  private BookRepository bookRepository;

  public List<Book> getAllBooks() {
    return bookRepository.findAll();
  }
}
