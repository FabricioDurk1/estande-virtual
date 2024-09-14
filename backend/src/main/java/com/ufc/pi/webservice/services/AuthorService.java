package com.ufc.pi.webservice.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ufc.pi.webservice.dtos.input.CreateAuthorDTO;
import com.ufc.pi.webservice.dtos.input.UpdateAuthorDTO;
import com.ufc.pi.webservice.models.Author;
import com.ufc.pi.webservice.repositories.AuthorRespository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
// Verifique se não há outro elemento chamado AuthorService aqui
public class AuthorService {
    private final AuthorRespository authorRepository;

    public void createAuthor(CreateAuthorDTO createAuthorDTO) {
        var author = new Author();
        author.setName(createAuthorDTO.name());
        authorRepository.create(author);
    }

    public void updateAuthor(Long id, UpdateAuthorDTO updateAuthorDTO) throws Exception {
        Optional<Author> authorOptional = authorRepository.findById(id);

        if (authorOptional.isEmpty()) {
            throw new Exception("Não existe um autor com o id " + id.toString());
        }

        Author existingAuthor = authorOptional.get();
        existingAuthor.setName(updateAuthorDTO.name());
        authorRepository.update(existingAuthor);
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    
    public Author findAuthorById(Long id) throws Exception {
        return authorRepository.findById(id)
            .orElseThrow(() -> new Exception("Autor não encontrado com o ID: " + id));
    }
}
