package com.ufc.pi.webservice.repositories;

import com.ufc.pi.webservice.data.structures.DoublyLinkedList;
import com.ufc.pi.webservice.models.Book;

public interface BookRepository {
    DoublyLinkedList<Book> findAll();
    void create(Book book); 
    DoublyLinkedList<Book> findById(Long id);  
    void update(Book book); 
}
