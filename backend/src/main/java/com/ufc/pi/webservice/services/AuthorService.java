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
//Nome da classe
public class AuthorService{
   //TipoDaPropriedade NomeDaPropriedade
  private final AuthorRespository authorRepository;


  ///Public tipoRetornoDoMetodo (NomeDoMetodo NomeParametro1)             Tipo e valor Tipo e nome
  public void createAuthor(CreateAuthorDTO createAuthorDTO){
    var author = new Author();
    author.setName(createAuthorDTO.name());

    authorRepository.create(author);
  }

  // Método para atualizar um autor
  public void  updateAuthor(Long id, UpdateAuthorDTO updateAuthorDTO) throws Exception{

    Optional<Author> authorOptional = authorRepository.findById(id);

    if(authorOptional.isEmpty()){
      throw new Exception("Não existe um autor com o id" + id.toString());
    }

    Author existingAuthor = authorOptional.get();
    existingAuthor.setName(updateAuthorDTO.name());

    authorRepository.update(existingAuthor);
  }

  public List<Author> getAllAuthors() {
    return authorRepository.findAll();  // Chama o método findAll no repositório
  }

}