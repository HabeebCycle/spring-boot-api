package com.habeebcycle.springbootmongodbapi.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Author")
public class Author {
    
    @Id
    private String id;
    private String name;
    private int age;

    public Author(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Author() {
    }

    public String getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public int getAge(){
        return age;
    }

    public void setId(String id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setAge(int age){
        this.age = age;
    }

}