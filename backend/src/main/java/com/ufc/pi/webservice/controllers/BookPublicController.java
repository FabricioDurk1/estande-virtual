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
}
