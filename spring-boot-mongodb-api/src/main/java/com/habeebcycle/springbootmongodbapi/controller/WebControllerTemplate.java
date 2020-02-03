package com.habeebcycle.springbootmongodbapi.controller;

import com.habeebcycle.springbootmongodbapi.exception.ResourceNotFoundException;
import com.habeebcycle.springbootmongodbapi.model.Author;
import com.habeebcycle.springbootmongodbapi.model.Book;
import com.habeebcycle.springbootmongodbapi.payload.AuthorRequest;
import com.habeebcycle.springbootmongodbapi.payload.AuthorResponse;
import com.habeebcycle.springbootmongodbapi.payload.BookRequest;
import com.habeebcycle.springbootmongodbapi.payload.BookResponse;
import com.habeebcycle.springbootmongodbapi.service.template.AuthorTemplate;
import com.habeebcycle.springbootmongodbapi.service.template.BookTemplate;
import com.habeebcycle.springbootmongodbapi.util.RequestUtil;
import com.habeebcycle.springbootmongodbapi.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v2")
public class WebControllerTemplate {

    private BookTemplate bookTemplate;
    private AuthorTemplate authorTemplate;

    @Autowired
    public WebControllerTemplate(BookTemplate bookTemplate, AuthorTemplate authorTemplate){
        this.bookTemplate = bookTemplate;
        this.authorTemplate = authorTemplate;
    }

    @GetMapping("/books")
    public List<BookResponse> getAllBooks(){
        List<BookResponse> bookResponseList = new ArrayList<>();
        bookTemplate.findAllBooks()
                .forEach(book -> bookResponseList.add(ResponseUtil.parseBookResponse(book,
                        authorTemplate.findAuthorById(book.getAuthor())
                )));
        return bookResponseList;
    }

    @GetMapping("/authors")
    public List<AuthorResponse> getAllAuthors(){
        List<AuthorResponse> authorResponseList = new ArrayList<>();
        authorTemplate.findAllAuthors()
                .forEach(author -> authorResponseList.add(ResponseUtil.parseAuthorResponse(author,
                        bookTemplate.findBooksByAuthor(author.getId())
                )));
        return authorResponseList;
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<BookResponse> getBook(@PathVariable String bookId) throws ResourceNotFoundException {
        Book book = bookTemplate.findBook(bookId);
        if(book == null) throw
                        new ResourceNotFoundException("No book found with the following Id :: " + bookId);
        BookResponse bookResponse = ResponseUtil.parseBookResponse(book, authorTemplate.findAuthorById(book.getAuthor()));
        return ResponseEntity.ok(bookResponse);
    }

    @GetMapping("/author/{authorId}")
    public ResponseEntity<AuthorResponse> getAuthor(@PathVariable String authorId) throws ResourceNotFoundException {
        Author author = authorTemplate.findAuthor(authorId);
         if(author == null) throw
                        new ResourceNotFoundException("No author found with the following Id :: " + authorId);
        AuthorResponse authorResponse = ResponseUtil.parseAuthorResponse(author, bookTemplate.findBooksByAuthor(author.getId()));
        return ResponseEntity.ok(authorResponse);
    }

    @PostMapping("/book")
    public BookResponse addBook(@Valid @RequestBody BookRequest bookRequest){
        Book bookReq = RequestUtil.parseBookRequest(bookRequest);
        Book book = bookTemplate.saveBook(bookReq);
        return ResponseUtil.parseBookResponse(book, authorTemplate.findAuthorById(book.getAuthor()));
    }

    @PostMapping("/author")
    public AuthorResponse addAuthors(@Valid @RequestBody AuthorRequest authorRequest){
        Author authorReq = RequestUtil.parseAuthorRequest(authorRequest);
        Author author = authorTemplate.saveAuthor(authorReq);
        return ResponseUtil.parseAuthorResponse(author, bookTemplate.findBooksByAuthor(author.getId()));
    }

    @PostMapping("/books")
    public List<BookResponse> addBooks(@Valid @RequestBody List<BookRequest> bookRequestList){
        List<Book> books = new ArrayList<>();
        List<BookResponse> bookResponseList = new ArrayList<>();
        bookRequestList.forEach(bookRequest -> books.add(RequestUtil.parseBookRequest(bookRequest)));
        bookTemplate.saveBooks(books)
                .forEach(book -> bookResponseList.add(ResponseUtil.parseBookResponse(book,
                        authorTemplate.findAuthorById(book.getAuthor())
                )));
        return bookResponseList;
    }

    @PostMapping("/authors")
    public List<AuthorResponse> addAuthors(@Valid @RequestBody List<AuthorRequest> authorRequestList){
        List<Author> authors = new ArrayList<>();
        List<AuthorResponse> authorResponseList = new ArrayList<>();
        authorRequestList.forEach(authorRequest -> authors.add(RequestUtil.parseAuthorRequest(authorRequest)));

        authorTemplate.saveAuthors(authors)
                .forEach(author -> authorResponseList.add(ResponseUtil.parseAuthorResponse(author,
                        bookTemplate.findBooksByAuthor(author.getId())
                )));
        return authorResponseList;
    }

    @PutMapping("/book/{bookId}")
    public BookResponse updateBook(@PathVariable String bookId, @Valid @RequestBody BookRequest bookRequest)
            throws ResourceNotFoundException{
        Book bookReq = bookTemplate.findBook(bookId);
        if(bookReq == null) throw
                        new ResourceNotFoundException("No book found with the following Id :: " + bookId);
        //Book bookReq = RequestUtil.parseBookRequest(bookRequest);
        bookReq.setName(bookRequest.getName());
        bookReq.setGenre(bookRequest.getGenre());
        bookReq.setIsbn(bookRequest.getIsbn());
        bookReq.setPages(bookRequest.getPages());
        bookReq.setYear(bookRequest.getYear());
        bookReq.setAuthor(bookRequest.getAuthor());

        Book book = bookTemplate.saveBook(bookReq);
        return ResponseUtil.parseBookResponse(book, authorTemplate.findAuthorById(book.getAuthor()));
    }

    @PutMapping("/author/{authorId}")
    public AuthorResponse updateAuthor(@PathVariable String authorId, @Valid @RequestBody AuthorRequest authorRequest)
            throws ResourceNotFoundException {
        Author authorReq = authorTemplate.findAuthor(authorId);
        if(authorReq == null) throw
                        new ResourceNotFoundException("No author found with the following Id :: " + authorId);

        authorReq.setName(authorRequest.getName());
        authorReq.setAge(authorRequest.getAge());

        Author author = authorTemplate.saveAuthor(authorReq);
        return ResponseUtil.parseAuthorResponse(author, bookTemplate.findBooksByAuthor(author.getId()));
    }

    @DeleteMapping("/book/{bookId}")
    public Map<String, Boolean> deleteBook(@PathVariable String bookId) throws ResourceNotFoundException{
        Map<String, Boolean> errorMap = new HashMap<>();
        Book book = bookTemplate.findBook(bookId);
        if(book == null) throw new ResourceNotFoundException("Cannot find book with Id :: " + bookId + " to delete");
        bookTemplate.deleteBook(book);
        errorMap.put("Resource Deleted", Boolean.TRUE);
        return errorMap;
    }

    @DeleteMapping("/author/{authorId}")
    public Map<String, Boolean> deleteAuthor(@PathVariable String authorId) throws ResourceNotFoundException{
        Map<String, Boolean> errorMap = new HashMap<>();
        Author author = authorTemplate.findAuthor(authorId);
        if(author == null) throw new ResourceNotFoundException("Cannot find author with Id :: " + authorId + " to delete");

        List<Book> authorBooks = bookTemplate.findBooksByAuthor(authorId);
        bookTemplate.deleteBooks(authorBooks);

        authorTemplate.deleteAuthor(author);
        errorMap.put("Resource Deleted", Boolean.TRUE);
        return errorMap;
    }
    
}
