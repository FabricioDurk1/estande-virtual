package com.ufc.pi.webservice.repositories;

import java.util.List;
import java.util.Optional;

import com.ufc.pi.webservice.models.Author;

public interface AuthorRespository {
  void create(Author author);
  void update(Author author);
  Optional<Author> findById(Long id);
  List<Author> findAll();
}


