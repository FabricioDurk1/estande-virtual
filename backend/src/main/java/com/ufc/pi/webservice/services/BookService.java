package com.ufc.pi.webservice.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufc.pi.webservice.dtos.input.CreateBookDTO;
import com.ufc.pi.webservice.models.Author;
import com.ufc.pi.webservice.models.Book;
import com.ufc.pi.webservice.models.Publisher;
import com.ufc.pi.webservice.repositories.AuthorRespository;
import com.ufc.pi.webservice.repositories.BookRepository;
import com.ufc.pi.webservice.repositories.PublisherRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {
 
  @Autowired
  private BookRepository bookRepository;

  @Autowired
  private AuthorRespository authorRepository;

  @Autowired
  private PublisherRepository publisherRepository;

  public List<Book> getAllBooks() {
    return bookRepository.findAll();
  }

  // Método para criar um novo livro
  public void createBook(CreateBookDTO createBookDTO) throws Exception {
    // Buscar o autor
    Author author = authorRepository.findById(createBookDTO.getAuthorId())
        .orElseThrow(() -> new Exception("Autor não encontrado com o ID: " + createBookDTO.getAuthorId()));

    // Buscar a editora
    Publisher publisher = publisherRepository.findById(createBookDTO.getPublisherId())
        .orElseThrow(() -> new Exception("Editora não encontrada com o ID: " + createBookDTO.getPublisherId()));

    // Criar o livro
    Book book = new Book();
    book.setTitle(createBookDTO.getTitle());
    book.setDescription(createBookDTO.getDescription());
    book.setPrice(createBookDTO.getPrice());
    book.setQuantity(createBookDTO.getQuantity());
    book.setAuthor(author);
    book.setPublisher(publisher);

    // Salvar o livro
    bookRepository.create(book);
  }

      // Método para atualizar um livro
    public void updateBook(Long id, CreateBookDTO updateBookDTO) throws Exception {
        // Buscar o livro existente pelo ID
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isEmpty()) {
            throw new Exception("Livro não encontrado com o ID: " + id);
        }

        Book book = bookOptional.get();

        // Buscar o autor e editora
        Author author = authorRepository.findById(updateBookDTO.getAuthorId())
            .orElseThrow(() -> new Exception("Autor não encontrado com o ID: " + updateBookDTO.getAuthorId()));

        Publisher publisher = publisherRepository.findById(updateBookDTO.getPublisherId())
            .orElseThrow(() -> new Exception("Editora não encontrada com o ID: " + updateBookDTO.getPublisherId()));

        // Atualizar os dados do livro
        book.setTitle(updateBookDTO.getTitle());
        book.setDescription(updateBookDTO.getDescription());
        book.setPrice(updateBookDTO.getPrice());
        book.setQuantity(updateBookDTO.getQuantity());
        book.setAuthor(author);
        book.setPublisher(publisher);

        // Atualizar no banco de dados
        bookRepository.update(book);
    }
}

