package com.habeebcycle.springbootmongodbapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Book")
public class Book {
    
    @Id
    private String id;
    private String name;
    private int pages;
    private String genre;
    private String isbn;
    private String year;
    private String author;

    public Book(String id, String name, int pages, String genre, String isbn, String year) {
        this.id = id;
        this.name = name;
        this.pages = pages;
        this.genre = genre;
        this.isbn = isbn;
        this.year = year;
    }

    public Book() {
    }

    public String getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public int getPages(){
        return pages;
    }

    public String getGenre(){
        return genre;
    }

    public String getIsbn(){
        return isbn;
    }

    public String getYear(){
        return year;
    }

    public void setId(String id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPages(int pages){
        this.pages = pages;
    }

    public void setGenre(String genre){
        this.genre = genre;
    }

    public void setIsbn(String isbn){
        this.isbn = isbn;
    }

    public void setYear(String year){
        this.year = year;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}