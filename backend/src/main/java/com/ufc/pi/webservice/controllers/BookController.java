package com.ufc.pi.webservice.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufc.pi.webservice.data.structures.DoublyLinkedList;
import com.ufc.pi.webservice.dtos.input.CreateBookDTO;
import com.ufc.pi.webservice.models.Book;
import com.ufc.pi.webservice.services.BookService;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/v1/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping
    public ResponseEntity<?> getAllBooks() {
        List<Book> booksResponse = new ArrayList<>();

        DoublyLinkedList<Book> books = bookService.getAllBooks();
        DoublyLinkedList<Book>.Node current = books.getHead();

        while (current != null) {
            booksResponse.add(current.data);
            current = current.next;
        }

        return ResponseEntity.ok(booksResponse);
    }
    
    @PostMapping
    public ResponseEntity<?> createBook(@RequestBody CreateBookDTO createBookDTO) {
        try {
            bookService.createBook(createBookDTO);
            return ResponseEntity.ok("Livro cadastrado com sucesso: " + createBookDTO.getTitle());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao cadastrar o livro: " + e.getMessage());
        }
    }
        @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@PathVariable Long id, @RequestBody CreateBookDTO updateBookDTO) {
        try {
            bookService.updateBook(id, updateBookDTO);
            return ResponseEntity.ok("Livro atualizado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao atualizar o livro: " + e.getMessage());
        }
    }
}
