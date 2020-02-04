/*
    This class is used to mock data. After it is working,
    the real data are being fetched from database through Spring Data JPA and DataService class
 */

package com.habeebcycle.graphqlapiserver.service;

import com.habeebcycle.graphqlapiserver.exception.ResourceNotFoundException;
import com.habeebcycle.graphqlapiserver.model.Author;
import com.habeebcycle.graphqlapiserver.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataModel {

    private List<Author> authorList;
    private List<Book> bookList;

    public DataModel(){

        authorList = new ArrayList<>();
        bookList = new ArrayList<>();

        Author author1 = new Author();
        author1.setId(1L);
        author1.setAge(20);
        author1.setName("Habeeb 1");
        authorList.add(author1);

        Author author2 = new Author();
        author2.setId(2L);
        author2.setAge(25);
        author2.setName("Habeeb 2");
        authorList.add(author2);

        Author author3 = new Author();
        author3.setId(3L);
        author3.setAge(28);
        author3.setName("Habeeb 3");
        authorList.add(author3);

        Book book1 = new Book();
        book1.setId(1L);
        book1.setName("Audit Finance");
        book1.setGenre("Finance");
        book1.setAuthor(authorList.get(0));
        authorList.get(0).getBooks().add(book1);
        bookList.add(book1);

        Book book2 = new Book();
        book2.setId(2L);
        book2.setName("General Mathematics");
        book2.setGenre("Mathematics");
        book2.setAuthor(authorList.get(0));
        authorList.get(0).getBooks().add(book2);
        bookList.add(book2);

        Book book3 = new Book();
        book3.setId(3L);
        book3.setName("Economics for Senior");
        book3.setGenre("Economics");
        book3.setAuthor(authorList.get(1));
        authorList.get(1).getBooks().add(book3);
        bookList.add(book3);

        Book book4 = new Book();
        book4.setId(4L);
        book4.setName("Technical Science");
        book4.setGenre("Technical");
        book4.setAuthor(authorList.get(2));
        authorList.get(2).getBooks().add(book4);
        bookList.add(book4);

        Book book5 = new Book();
        book5.setId(5L);
        book5.setName("Java for Developers");
        book5.setGenre("Programming");
        book5.setAuthor(authorList.get(2));
        authorList.get(2).getBooks().add(book5);
        bookList.add(book5);

        Book book6 = new Book();
        book6.setId(6L);
        book6.setName("Physical Chemistry");
        book6.setGenre("Science");
        book6.setAuthor(authorList.get(0));
        authorList.get(0).getBooks().add(book6);
        bookList.add(book6);

        Book book7 = new Book();
        book7.setId(7L);
        book7.setName("Bill of the Moment");
        book7.setAuthor(authorList.get(2));
        book7.setGenre("Fantasy");
        authorList.get(2).getBooks().add(book7);
        bookList.add(book7);
    }

    public List<Author> getAuthorList(){
        return authorList;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public Book getBook(Long id){
        return bookList.stream().
                filter(book -> book.getId().equals(id)).
                findFirst().
                orElseThrow(() ->
                        new ResourceNotFoundException("Books", "Id", id)
                );
    }

    public Author getAuthor(Long id){
        return authorList.stream().
                filter(author -> author.getId().equals(id)).
                findFirst().
                orElseThrow(() -> new ResourceNotFoundException("Authors", "Id", id));
    }

    public Book addBook(String name, String genre, Long author){
        Book book = new Book();
        Author bookAuthor = getAuthor(author);
        book.setName(name);
        book.setGenre(genre);
        book.setAuthor(bookAuthor);
        book.setId(System.currentTimeMillis());
        bookAuthor.getBooks().add(book);
        bookList.add(book);
        return book;
    }

    public Author addAuthor(String name, int age){
        Author author = new Author();
        author.setName(name);
        author.setAge(age);
        author.setId(System.currentTimeMillis());
        authorList.add(author);
        return author;
    }
}
