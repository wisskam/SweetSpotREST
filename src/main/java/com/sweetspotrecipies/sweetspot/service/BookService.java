package com.sweetspotrecipies.sweetspot.service;

import com.sweetspotrecipies.sweetspot.model.Book;
import com.sweetspotrecipies.sweetspot.model.Recipe;
import com.sweetspotrecipies.sweetspot.repository.BookRepository;
import com.sweetspotrecipies.sweetspot.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Iterable<Book> listAll(int userID) {
        Iterable<Book> books = bookRepository.findAll();
        List<Book> result = new ArrayList<Book>();
        books.forEach(book -> {
            if(book.getUser().getId() == userID) {
                System.out.println("UserID" + userID);
                result.add(book);
            }
        });
        return result;
    }

    public void save(Book book) {
        if (book.getModified() == null) {
            book.setModified(new Date());
        }
        if(book.getId() == null){
            book.setCreated(new Date());
        }
        else{
            Book existingBook = this.find(book.getId());
            book.setCreated(existingBook.getCreated());
        }
        bookRepository.save(book);
    }

    public Book saveAndGet(Book book) {
        save(book);
        return book;
    }


    public Book find(Integer id) {
        return bookRepository.findById(id).orElse(null);
    }


    public void delete(Integer id) {
        bookRepository.deleteById(id);
    }
}