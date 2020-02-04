package com.habeebcycle.graphqlapiserver.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.habeebcycle.graphqlapiserver.model.Author;
import com.habeebcycle.graphqlapiserver.model.Book;
import com.habeebcycle.graphqlapiserver.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class QueryResolver implements GraphQLQueryResolver {

    private DataService dataService;

    @Autowired
    public QueryResolver(DataService dataService){
        this.dataService = dataService;
    }

    public List<Book> getBooks() {
        return dataService.getBookList();
    }

    public Book getBook(Long id){
        return dataService.getBook(id);
    }

    public List<Author> getAuthors(){
        return dataService.getAuthorList();
    }

    public Author getAuthor(Long id){
        return dataService.getAuthor(id);
    }


}
