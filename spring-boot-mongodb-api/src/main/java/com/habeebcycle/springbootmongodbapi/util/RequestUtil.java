package com.habeebcycle.springbootmongodbapi.util;

import com.habeebcycle.springbootmongodbapi.model.Author;
import com.habeebcycle.springbootmongodbapi.model.Book;
import com.habeebcycle.springbootmongodbapi.payload.AuthorRequest;
import com.habeebcycle.springbootmongodbapi.payload.BookRequest;

public class RequestUtil {

    public static Book parseBookRequest(BookRequest bookRequest){
        Book book = new Book();
        book.setName(bookRequest.getName());
        book.setGenre(bookRequest.getGenre());
        book.setIsbn(bookRequest.getIsbn());
        book.setPages(bookRequest.getPages());
        book.setYear(bookRequest.getYear());
        book.setAuthor(bookRequest.getAuthor());
        return book;
    }

    public static Author parseAuthorRequest(AuthorRequest authorRequest){
        Author author = new Author();
        author.setName(authorRequest.getName());
        author.setAge(authorRequest.getAge());
        return author;
    }
}
