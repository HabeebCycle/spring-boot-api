package com.habeebcycle.graphqlapiserver.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.habeebcycle.graphqlapiserver.model.Author;
import com.habeebcycle.graphqlapiserver.model.Book;
import com.habeebcycle.graphqlapiserver.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MutationResolver implements GraphQLMutationResolver {

    private DataService dataService;

    @Autowired
    public MutationResolver(DataService dataService){
        this.dataService = dataService;
    }

    public Book addBook(String name, String genre, Long author){
        return dataService.addBook(name, genre, author);
    }

    public Author addAuthor(String name, int age){
        return dataService.addAuthor(name, age);
    }

    public Book updateBook(Long id, String name, String genre, Long author){
        return dataService.updateBook(id, name, genre, author);
    }

    public Author updateAuthor(Long id, String name, int age){
        return dataService.updateAuthor(id, name, age);
    }

    public Book deleteBook(Long id){
        return dataService.deleteBook(id);
    }

    public Author deleteAuthor(Long id){
        return dataService.deleteAuthor(id);
    }


}
