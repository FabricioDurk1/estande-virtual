package com.ufc.pi.webservice.repositories;

import com.ufc.pi.webservice.data.structures.DoublyLinkedList;
import com.ufc.pi.webservice.models.Author;

public interface AuthorRespository {
  void create(Author author);
  void update(Author author);
  DoublyLinkedList<Author> findById(Long id);
  DoublyLinkedList<Author> findAll();
}


