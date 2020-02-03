package com.habeebcycle.springbootmongodbapi.service.template;

import com.habeebcycle.springbootmongodbapi.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookTemplate {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public BookTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    public List<Book> findAllBooks(){
        return mongoTemplate.findAll(Book.class);
    }

    public Book findBook(String id){
        Query query = new Query().addCriteria(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, Book.class);
    }

    public Book saveBook(Book book){
        return mongoTemplate.save(book);
    }

    public List<Book> saveBooks(List<Book> books){
        return (List<Book>)mongoTemplate.insertAll(books);
    }

    public void deleteBook(Book book){
        mongoTemplate.remove(book);
    }

    public void deleteBooks(List<Book> books){
        books.forEach(mongoTemplate::remove);
    }

    public List<Book> findBooksByAuthor(String authorId){
        Query query = new Query().addCriteria(Criteria.where("author").is(authorId));
        return mongoTemplate.find(query, Book.class);
    }
}
