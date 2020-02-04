package com.habeebcycle.graphqlapiserver.service;

import com.habeebcycle.graphqlapiserver.exception.ResourceNotFoundException;
import com.habeebcycle.graphqlapiserver.model.Author;
import com.habeebcycle.graphqlapiserver.model.Book;
import com.habeebcycle.graphqlapiserver.repository.AuthorRepository;
import com.habeebcycle.graphqlapiserver.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataService {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;

    @Autowired
    public DataService(AuthorRepository authorRepository, BookRepository bookRepository){
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public List<Author> getAuthorList(){
        return authorRepository.findAll();
    }

    public List<Book> getBookList() {
        return bookRepository.findAll();
    }

    public Book getBook(Long id){
        return bookRepository.findById(id).
                orElseThrow(() ->
                        new ResourceNotFoundException("Books", "Id", id)
                );
    }

    public Author getAuthor(Long id){
        return authorRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Authors", "Id", id));
    }

    public Book addBook(String name, String genre, Long author){
        Author bookAuthor = getAuthor(author);

        Book book = new Book();
        book.setName(name);
        book.setGenre(genre);
        book.setAuthor(bookAuthor);
        return bookRepository.save(book);
    }

    public Author addAuthor(String name, int age){
        Author author = new Author();
        author.setName(name);
        author.setAge(age);
        return authorRepository.save(author);
    }

    public Book updateBook(Long id, String name, String genre, Long author){
        Author bookAuthor = getAuthor(author);
        Book book = getBook(id);
        book.setName(name);
        book.setGenre(genre);
        book.setAuthor(bookAuthor);
        return bookRepository.save(book);
    }

    public Author updateAuthor(Long id, String name, int age){
        Author author = getAuthor(id);
        author.setName(name);
        author.setAge(age);
        return authorRepository.save(author);
    }

    public Book deleteBook(Long id){
        Book book = getBook(id);
        bookRepository.delete(book);
        return book;
    }

    public Author deleteAuthor(Long id){
        Author author = getAuthor(id);
        List<Book> books = author.getBooks();
        //bookRepository.deleteAll(books);
        authorRepository.delete(author);
        return author;
    }
}
