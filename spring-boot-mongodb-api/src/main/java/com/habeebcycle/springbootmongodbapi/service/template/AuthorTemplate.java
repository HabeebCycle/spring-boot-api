package com.habeebcycle.springbootmongodbapi.service.template;

import com.habeebcycle.springbootmongodbapi.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorTemplate  {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public AuthorTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<Author> findAllAuthors(){
        return mongoTemplate.findAll(Author.class);
    }

    public Author findAuthor(String id){
        Query query = new Query().addCriteria(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, Author.class);
    }

    public Author saveAuthor(Author author){
        return mongoTemplate.save(author);
    }

    public List<Author> saveAuthors(List<Author> authors){
        return (List<Author>)mongoTemplate.insertAll(authors);
    }

    public void deleteAuthor(Author author){
        mongoTemplate.remove(author);
    }

    public Author findAuthorById(String authorId){
        Query query = new Query().addCriteria(Criteria.where("id").is(authorId));
        return mongoTemplate.findOne(query, Author.class);
    }

}
