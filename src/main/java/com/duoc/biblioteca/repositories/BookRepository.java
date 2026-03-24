package com.duoc.biblioteca.repositories;

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
        this.bookList.add(new Book(
                1l,
                "abc",
                "libro 1",
                "libro 1",
                "springer",
                1,
                "persona 1"
                )
        );
        this.bookList.add(new Book(
                        2l,
                        "qwe",
                        "libro 1",
                        "libro 1",
                        "springer",
                        1,
                        "persona 2"
                )
        );
        this.bookList.add(new Book(
                        3l,
                        "zxc",
                        "libro 1",
                        "libro 1",
                        "springer",
                        1,
                        "persona 1"
                )
        );
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
                throw new RuntimeException("Book with isbn"+newBook.getIsbn()+" already exists");
            }
        }else{
            throw new RuntimeException("Book with id "+newBook.getId()+" already exists");
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
        List<Book> filterAuthor = new ArrayList<>();
        for (Book book : this.bookList){
            if(book.getAuthor().equals(author)){
                filterAuthor.add(book);
            }
        }
        return filterAuthor;
    }


}
