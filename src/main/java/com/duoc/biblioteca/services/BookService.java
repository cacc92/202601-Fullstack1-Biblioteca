package com.duoc.biblioteca.services;

import com.duoc.biblioteca.models.Book;
import com.duoc.biblioteca.models.BookDTO;
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

    public Map<String,Integer> getTotal(){
        Map<String,Integer> response = new HashMap<>();
        response.put("cantidadLibros",this.getAll().size());
        return response;
    }

    public List<Map<String, String>> getByAuthorMap(String author){
        List<Map<String,String>> response = new ArrayList<>();
        List<Book> listBook = this.bookRepository.findByAuthor(author);

        for(Book book : listBook){
            Map<String, String> bookMap = new HashMap<>();
            bookMap.put("title", book.getTitle());
            bookMap.put("author", book.getAuthor());
            bookMap.put("editorial", book.getEditorial());
            response.add(bookMap);
        }
        return response;
    }

    public List<BookDTO> getByAuthorDTO(String author){
        List<BookDTO> response = new ArrayList<>();
        List<Book> listBook = this.bookRepository.findByAuthor(author);

        for(Book book : listBook){
            BookDTO bookDTO = new BookDTO(
                    book.getAuthor(), book.getTitle(), book.getEditorial()
            );
            response.add(bookDTO);
        }
        return response;
    }

}
