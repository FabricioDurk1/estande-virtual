package com.ufc.pi.webservice.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.ufc.pi.webservice.models.Publisher;

@Repository
public interface PublisherRepository {
    void create(Publisher publisher); 
    void update(Publisher publisher); 
    Optional<Publisher> findById(Long id);
    List<Publisher> findAll();
}
