package com.duoc.biblioteca.services;

import com.duoc.biblioteca.models.Book;
import com.duoc.biblioteca.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAll(){
        return this.bookRepository.findAll();
    }

    public Book getById(Long id){
        return this.bookRepository.findById(id);
    }

    public Book getByIsbn(String isbn){
        return this.bookRepository.findByIsbn(isbn);
    }

    public Book save(Book book){
        return this.bookRepository.save(book);
    }

    public Book update(Book book){
        return this.bookRepository.update(book);
    }

    public void delete(Long id){
        this.bookRepository.deleteById(id);
    }

    public Map<String,Integer> count(){
        List<Book> list = this.bookRepository.findAll();
        Map<String, Integer> response = new HashMap<>();
        response.put("cantidadLibros", list.size());
        return response;
    }

    public List<Map<String,String>> getBookByAuthor(String author){
        List<Book> filterAuthor = this.bookRepository.findByAuthor(author);
        List<Map<String,String>> response = new ArrayList<>();
        Map<String,String> bookDict = new HashMap<>();
        for (Book book: filterAuthor){
            bookDict.put("title", book.getTitle());
            bookDict.put("author", book.getAuthor());
            bookDict.put("editorial",book.getEditorial());
            response.add(bookDict);
            //bookDict.clear();
        }
        return response;
    }

}
