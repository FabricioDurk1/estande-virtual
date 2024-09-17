package com.ufc.pi.webservice.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufc.pi.webservice.data.structures.DoublyLinkedList;
import com.ufc.pi.webservice.dtos.input.CreateAuthorDTO;
import com.ufc.pi.webservice.dtos.input.UpdateAuthorDTO;
import com.ufc.pi.webservice.models.Author;
import com.ufc.pi.webservice.services.AuthorService;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/v1/authors")
@RequiredArgsConstructor
public class AuthorController {
    // Injeção de dependência via construtor com @RequiredArgsConstructor
    private final AuthorService authorService;

    @PostMapping
    public ResponseEntity<?> createAuthor(@RequestBody CreateAuthorDTO input) {
        authorService.createAuthor(input);
        return ResponseEntity.ok("Retornando da rota de cadastro de Autores: " + input.name());
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateAuthor(@PathVariable("id")  Long id, @RequestBody UpdateAuthorDTO updateAuthorDTO) {
      try {
        authorService.updateAuthor(id, updateAuthorDTO);
        return ResponseEntity.ok("Autor atualizado com sucesso!");
      } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
      }  
    }

    @GetMapping
    public ResponseEntity<List<Author>> getAllAuthors() {
        List<Author> authorsResponse = new ArrayList<>();

        DoublyLinkedList<Author> authors = authorService.getAllAuthors();
        DoublyLinkedList<Author>.Node current = authors.getHead();

        while (current != null) {
            authorsResponse.add(current.data);
            current = current.next;
        }

        return ResponseEntity.ok(authorsResponse);
    }

        @GetMapping("/{id}")
        public ResponseEntity<?> getAuthorById(@PathVariable Long id) {
            try {
                DoublyLinkedList<Author> author = authorService.findAuthorById(id);
                return ResponseEntity.ok(author.getHead().data);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
            }
        }
}

