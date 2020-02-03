package com.habeebcycle.springbootmongodbapi.payload;

import com.habeebcycle.springbootmongodbapi.model.Book;

import java.util.List;

public class AuthorResponse {

    private String id;
    private String name;
    private int age;
    private List<Book> books;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
