package com.habeebcycle.springbootmongodbapi.repository;

import com.habeebcycle.springbootmongodbapi.model.Author;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends MongoRepository<Author, String> {
    Author findAuthorById(String author);
}
