package com.habeebcycle.springbootmongodbapi.util;

import com.habeebcycle.springbootmongodbapi.model.Author;
import com.habeebcycle.springbootmongodbapi.model.Book;
import com.habeebcycle.springbootmongodbapi.payload.AuthorResponse;
import com.habeebcycle.springbootmongodbapi.payload.BookResponse;

import java.util.List;

public class ResponseUtil {

    public static BookResponse parseBookResponse(Book book, Author author){
        BookResponse bookResponse = new BookResponse();
        bookResponse.setId(book.getId());
        bookResponse.setGenre(book.getGenre());
        bookResponse.setName(book.getName());
        bookResponse.setIsbn(book.getIsbn());
        bookResponse.setPages(book.getPages());
        bookResponse.setYear(book.getYear());
        bookResponse.setAuthor(author);
        return bookResponse;
    }

    public static AuthorResponse parseAuthorResponse(Author author, List<Book> books){
        AuthorResponse authorResponse = new AuthorResponse();
        authorResponse.setId(author.getId());
        authorResponse.setName(author.getName());
        authorResponse.setName(author.getName());
        authorResponse.setAge(author.getAge());
        authorResponse.setBooks(books);
        return authorResponse;
    }
}
