package com.habeebcycle.graphqlapiserver.repository;

import com.habeebcycle.graphqlapiserver.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
