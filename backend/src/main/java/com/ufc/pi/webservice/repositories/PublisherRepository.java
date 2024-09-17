package com.ufc.pi.webservice.repositories;

import org.springframework.stereotype.Repository;

import com.ufc.pi.webservice.data.structures.DoublyLinkedList;
import com.ufc.pi.webservice.models.Publisher;

@Repository
public interface PublisherRepository {
    void create(Publisher publisher); 
    void update(Publisher publisher); 
    DoublyLinkedList<Publisher> findById(Long id);
    DoublyLinkedList<Publisher> findAll();
}
