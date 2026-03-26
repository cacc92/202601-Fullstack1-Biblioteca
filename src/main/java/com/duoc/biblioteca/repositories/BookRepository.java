package com.duoc.biblioteca.repositories;

import com.duoc.biblioteca.exceptions.BookException;
import com.duoc.biblioteca.models.Book;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class BookRepository {

    private List<Book> bookList = new ArrayList<>();

    public List<Book> findAll(){
        return this.bookList;
    }

    public BookRepository(){
        bookList.add(new Book(1L, "9789569646638", "Fuego y Sangre", "Penguin Random House Grupo Editorial", 2018, "George R. R. Martin"));
        bookList.add(new Book(2L, "9789563494150", "Quique Hache: El Mall Embrujado y Otras Historias", "Sm Ediciones", 2014, "Sergio Gomez"));
        bookList.add(new Book(3L, "9781484256251", "Spring Boot Persistence Best Practices", "Apress", 2020, "Anghel Leonard"));
        bookList.add(new Book(4L, "9789566075752", "Harry Potter y la piedra filosofal", "Salamandra", 2024, "J. K. Rowling"));
        bookList.add(new Book(5L, "9780439139601", "Harry Potter y el prisionero de Azkaban", "Scholastic", 1999, "J. K. Rowling"));
        bookList.add(new Book(6L, "9780439136365", "Harry Potter y el cáliz de fuego", "Scholastic", 2000, "J. K. Rowling"));
        bookList.add(new Book(7L, "9780321127426", "Effective Java", "Addison-Wesley", 2008, "Joshua Bloch"));
        bookList.add(new Book(8L, "9780134685991", "Clean Architecture", "Prentice Hall", 2017, "Robert C. Martin"));
        bookList.add(new Book(9L, "9780201633610", "Design Patterns", "Addison-Wesley", 1994, "Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides"));
        bookList.add(new Book(10L, "9780132350884", "Clean Code", "Prentice Hall", 2008, "Robert C. Martin"));
    }

    public Book findById(Long id){
        for(Book book : this.bookList){
            if(Objects.equals(book.getId(), id)){
                return book;
            }
        }
        return null;
    }

    public Book findByIsbn(String isbn){
        for(Book book : this.bookList){
            if(book.getIsbn().equals(isbn)){
                return book;
            }
        }
        return null;
    }

    public Book save(Book newBook){
        Book bookFindId = this.findById(newBook.getId());
        if(bookFindId == null){
            Book bookFindIsbn = this.findByIsbn(newBook.getIsbn());
            if (bookFindIsbn == null){
                this.bookList.add(newBook);
                return newBook;
            }else{
                throw new BookException("Book with isbn"+newBook.getIsbn()+" already exists");
            }
        }else{
            throw new BookException("Book with id "+newBook.getId()+" already exists");
        }
    }

    public Book update(Book updatedBook){
        int position = 0;
        boolean find = false;
        for(int i=0;i<this.bookList.size();i++){
            if(this.bookList.get(i).getId().equals(updatedBook.getId())){
                position = i;
                find = true;
                break;
            }
        }
        if (find){
            this.bookList.set(position,updatedBook);
            return updatedBook;
        }else{
            throw new RuntimeException("");
        }
    }

    public void deleteById(Long id){
        Book book = this.findById(id);
        this.bookList.remove(book);
    }

    public List<Book> findByAuthor(String author){
        List<Book> books = new ArrayList<>();
        for (Book book : this.bookList) {
            if(book.getAuthor().equals(author)){
                books.add(book);
            }
        }
        return books;
    }


}
