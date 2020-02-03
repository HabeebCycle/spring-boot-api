package com.habeebcycle.springbootmongodbapi.service;

import com.habeebcycle.springbootmongodbapi.model.Author;
import com.habeebcycle.springbootmongodbapi.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    private AuthorRepository authorRepository;

    @Autowired
    private AuthorService(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }

    public List<Author> findAllAuthors(){
        return authorRepository.findAll();
    }

    public Optional<Author> findAuthor(String id){
        return authorRepository.findById(id);
    }

    public Author saveAuthor(Author author){
        return authorRepository.save(author);
    }

    public List<Author> saveAuthors(List<Author> authors){
        return authorRepository.saveAll(authors);
    }

    public void deleteAuthor(Author author){
        authorRepository.delete(author);
    }

    public Author findAuthorById(String authorId){
        return authorRepository.findAuthorById(authorId);
    }

}
