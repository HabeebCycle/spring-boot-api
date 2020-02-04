package com.habeebcycle.graphqlapiserver.repository;

import com.habeebcycle.graphqlapiserver.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
