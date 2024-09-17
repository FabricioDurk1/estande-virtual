package com.ufc.pi.webservice.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufc.pi.webservice.data.structures.DoublyLinkedList;
import com.ufc.pi.webservice.models.Book;
import com.ufc.pi.webservice.services.BookService;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("api/v1/public")
@RequiredArgsConstructor
public class BookPublicController {
    private final BookService bookService;

    @GetMapping("/books")
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

    
    // Nova rota para obter os detalhes de um livro
    @GetMapping("/books/{id}")
    public ResponseEntity<?> getBookDetails(@PathVariable Long id) {
        try {
            Book book = bookService.getBookDetailsById(id);
            return ResponseEntity.ok(book);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
