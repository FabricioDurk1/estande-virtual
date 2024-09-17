package com.ufc.pi.webservice.services;

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

  public DoublyLinkedList<Book> getAllBooks() {
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
    DoublyLinkedList<Publisher> publisher = publisherRepository.findById(createBookDTO.getPublisherId());

    if (publisher.getHead() == null) {
      throw new Exception("Editora não encontrada com o ID: " + createBookDTO.getPublisherId());
    }

    // Criar o livro
    Book book = new Book();
    book.setTitle(createBookDTO.getTitle());
    book.setDescription(createBookDTO.getDescription());
    book.setPrice(createBookDTO.getPrice());
    book.setQuantity(createBookDTO.getQuantity());
    book.setAuthor(author.getHead().data);
    book.setPublisher(publisher.getHead().data);

    // Salvar o livro
    bookRepository.create(book);
  }

      // Método para atualizar um livro
    public void updateBook(Long id, CreateBookDTO updateBookDTO) throws Exception {
        // Buscar o livro existente pelo ID
        DoublyLinkedList<Book> bookList = bookRepository.findById(id);
        
        if (bookList.getHead() == null) {
            throw new Exception("Livro não encontrado com o ID: " + id);
        }

        Book book = bookList.getHead().data;

        // Buscar o autor e editora
        DoublyLinkedList<Author> authorList = authorRepository.findById(updateBookDTO.getAuthorId());

        if (authorList.getHead() == null) {
          throw new Exception("Autor não encontrado com o ID: " + updateBookDTO.getAuthorId());
        }

        DoublyLinkedList<Publisher> publisherList = publisherRepository.findById(updateBookDTO.getPublisherId());
        
        if (publisherList.getHead() == null) {
          throw new Exception("Editora não encontrada com o ID: " + updateBookDTO.getPublisherId());
        }
        
        // Atualizar os dados do livro
        book.setTitle(updateBookDTO.getTitle());
        book.setDescription(updateBookDTO.getDescription());
        book.setPrice(updateBookDTO.getPrice());
        book.setQuantity(updateBookDTO.getQuantity());
        book.setAuthor(authorList.getHead().data);
        book.setPublisher(publisherList.getHead().data);

        // Atualizar no banco de dados
        bookRepository.update(book);
    }

    public Book getBookDetailsById(Long id) throws Exception {
        DoublyLinkedList<Book> bookOptional = bookRepository.findById(id);
        
        if (bookOptional.getHead() == null) {
            throw new Exception("Livro não encontrado com o ID: " + id);
        }

        return bookOptional.getHead().data;
    }
}

