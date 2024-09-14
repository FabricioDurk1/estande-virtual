package com.ufc.pi.webservice.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufc.pi.webservice.dtos.input.CreatePublisherDTO;
import com.ufc.pi.webservice.dtos.input.UpdatePublisherDTO;
import com.ufc.pi.webservice.services.PublisherService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/v1/publishers")
@RequiredArgsConstructor
public class PublisherController {
    private final PublisherService publisherService;

    @PostMapping
    public ResponseEntity<?> createPublisher(@RequestBody CreatePublisherDTO createPublisherDTO) {
        publisherService.createPublisher(createPublisherDTO);
        return ResponseEntity.ok("Editora cadastrada com sucesso");
    }

    @GetMapping
    public ResponseEntity<?> getAllPublishers() {
        return ResponseEntity.ok(publisherService.getAllPublishers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPublisherById(@PathVariable("id") Long id) {
        try {
            var publisher = publisherService.getPublisherById(id);
            return ResponseEntity.ok(publisher);    
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePublisher(@PathVariable("id") Long id, @RequestBody UpdatePublisherDTO updatePublisherDTO) {
        try {
            publisherService.updatePublisher(id, updatePublisherDTO);
            return ResponseEntity.ok("Editora atualizada com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        
    }
}
