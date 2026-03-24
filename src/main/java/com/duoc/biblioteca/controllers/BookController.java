package com.duoc.biblioteca.controllers;

import com.duoc.biblioteca.models.Book;
import com.duoc.biblioteca.services.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/books")
@Validated
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<List<Book>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(
                this.bookService.getAll()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(
                this.bookService.getById(id)
        );
    }

    @PostMapping
    public ResponseEntity<Book> save(@Valid @RequestBody Book book){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                this.bookService.save(book)
        );
    }

    @GetMapping("/total")
    public ResponseEntity<Map<String, Integer>> getTotal(){
        return ResponseEntity.status(HttpStatus.OK).body(this.bookService.count());
    }

    @GetMapping("/author/{author}")
    public ResponseEntity<List<Map<String,String>>>
    getByAuthor(@PathVariable String author){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.bookService.getBookByAuthor(author));
    }

}
