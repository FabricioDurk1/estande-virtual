package com.ufc.pi.webservice.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufc.pi.webservice.data.structures.DoublyLinkedList;
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
    DoublyLinkedList<Author> author = authorRepository.findById(createBookDTO.getAuthorId());

    if (author.getHead() == null) {
      throw new Exception("Autor não encontrado com o ID: " + createBookDTO.getAuthorId());
    }

    // Buscar a editora
    Optional<Publisher> publisher = publisherRepository.findById(createBookDTO.getPublisherId());

    if (publisher.isEmpty()) {
      throw new Exception("Editora não encontrada com o ID: " + createBookDTO.getPublisherId());
    }

    // Criar o livro
    Book book = new Book();
    book.setTitle(createBookDTO.getTitle());
    book.setDescription(createBookDTO.getDescription());
    book.setPrice(createBookDTO.getPrice());
    book.setQuantity(createBookDTO.getQuantity());
    book.setAuthor(author.getHead().data);
    book.setPublisher(publisher.get());

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
        DoublyLinkedList<Author> author = authorRepository.findById(updateBookDTO.getAuthorId());

        if (author.getHead() == null) {
          throw new Exception("Autor não encontrado com o ID: " + updateBookDTO.getAuthorId());
        }

        Publisher publisher = publisherRepository.findById(updateBookDTO.getPublisherId())
            .orElseThrow(() -> new Exception("Editora não encontrada com o ID: " + updateBookDTO.getPublisherId()));

        // Atualizar os dados do livro
        book.setTitle(updateBookDTO.getTitle());
        book.setDescription(updateBookDTO.getDescription());
        book.setPrice(updateBookDTO.getPrice());
        book.setQuantity(updateBookDTO.getQuantity());
        book.setAuthor(author.getHead().data);
        book.setPublisher(publisher);

        // Atualizar no banco de dados
        bookRepository.update(book);
    }

    public Book getBookDetailsById(Long id) throws Exception {
        Optional<Book> bookOptional = bookRepository.findById(id);
        
        if (bookOptional.isEmpty()) {
            throw new Exception("Livro não encontrado com o ID: " + id);
        }

        return bookOptional.get();
    }
}

