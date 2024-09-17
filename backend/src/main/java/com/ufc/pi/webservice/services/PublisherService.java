package com.ufc.pi.webservice.services;

import org.springframework.stereotype.Service;

import com.ufc.pi.webservice.data.structures.DoublyLinkedList;
import com.ufc.pi.webservice.dtos.input.CreatePublisherDTO;
import com.ufc.pi.webservice.dtos.input.UpdatePublisherDTO;
import com.ufc.pi.webservice.models.Publisher;
import com.ufc.pi.webservice.repositories.PublisherRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PublisherService {
    public final PublisherRepository publisherRepository;

    public void createPublisher(CreatePublisherDTO createPublisherDTO) {
        var publisher = new Publisher();
        publisher.setName(createPublisherDTO.name());

        publisherRepository.create(publisher);
    }

    public void updatePublisher(Long id, UpdatePublisherDTO updatePublisherDTO) throws Exception {
        DoublyLinkedList<Publisher> publisherOptional = publisherRepository.findById(id);

        if(publisherOptional.getHead() == null) {
            throw new Exception("Não existe uma editora com o id " + id.toString());
        }

        Publisher existingPublisher = publisherOptional.getHead().data;
        existingPublisher.setName(updatePublisherDTO.name());

        publisherRepository.update(existingPublisher);
    }

    public DoublyLinkedList<Publisher> getAllPublishers() {
        DoublyLinkedList<Publisher> publishers = publisherRepository.findAll();
        return publishers;
    }

    public Publisher getPublisherById(Long id) throws Exception {
        DoublyLinkedList<Publisher> publisherOptional = publisherRepository.findById(id);

        if(publisherOptional.getHead() == null) {
            throw new Exception("Editora não encontrada");
        }

        return publisherOptional.getHead().data;
    }
}
