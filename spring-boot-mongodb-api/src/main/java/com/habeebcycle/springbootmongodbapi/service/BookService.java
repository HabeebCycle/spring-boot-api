package com.habeebcycle.springbootmongodbapi.service;

import com.habeebcycle.springbootmongodbapi.model.Book;
import com.habeebcycle.springbootmongodbapi.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private BookRepository bookRepository;

    @Autowired
    private BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public List<Book> findAllBooks(){
        return bookRepository.findAll();
    }

    public Optional<Book> findBook(String id){
        return bookRepository.findById(id);
    }

    public Book saveBook(Book book){
        return bookRepository.save(book);
    }

    public List<Book> saveBooks(List<Book> books){
        return bookRepository.saveAll(books);
    }

    public void deleteBook(Book book){
        bookRepository.delete(book);
    }

    public void deleteBooks(List<Book> books){
        bookRepository.deleteAll(books);
    }

    public List<Book> findBooksByAuthor(String authorId){
        return bookRepository.findBooksByAuthor(authorId);
    }
}
