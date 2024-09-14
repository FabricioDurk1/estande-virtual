package com.ufc.pi.webservice.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

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
        Optional<Publisher> publisherOptional = publisherRepository.findById(id);

        if(publisherOptional.isEmpty()){
            throw new Exception("Não existe uma editora com o id " + id.toString());
        }

        Publisher existingPublisher = publisherOptional.get();
        existingPublisher.setName(updatePublisherDTO.name());

        publisherRepository.update(existingPublisher);
    }

    public List<Publisher> getAllPublishers() {
        List<Publisher> publishers = publisherRepository.findAll();
        return publishers;
    }

    public Publisher getPublisherById(Long id) throws Exception {
        Optional<Publisher> publisherOptional = publisherRepository.findById(id);

        if(publisherOptional.isEmpty()){
            throw new Exception("Editora não encontrada");
        }

        return publisherOptional.get();
    }
}
