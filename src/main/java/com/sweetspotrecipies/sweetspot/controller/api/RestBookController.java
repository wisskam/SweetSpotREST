package com.sweetspotrecipies.sweetspot.controller.api;

import com.sweetspotrecipies.sweetspot.api.mapper.BookMapper;
import com.sweetspotrecipies.sweetspot.api.model.BookDTO;
import com.sweetspotrecipies.sweetspot.model.Book;
import com.sweetspotrecipies.sweetspot.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = { "http://localhost:8081" })
@RestController
@RequestMapping("${url.prefix.api.v1}/book")
public class RestBookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private BookMapper bookMapper;
    @GetMapping("/")
    public ResponseEntity<Iterable<BookDTO>> index() {
        try {
            return new ResponseEntity<>(
                    bookMapper.map(
                            bookService.listAll()
                    ),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBook(@PathVariable("id") Integer id) {
        try {
            Book bookEntity = bookService.find(id);
            return new ResponseEntity(
                    bookMapper.bookToBookDTO(
                            bookEntity
                    ),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/")
    public ResponseEntity<Void> createBook(@RequestBody BookDTO bookDTO) {
        try {
            bookService.save(
                    bookMapper.bookDTOToBook(
                            bookDTO
                    )
            );
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable("id") Integer id,
                                                @RequestBody BookDTO bookDTO) {
        try {
            Book bookEntity = bookService.find(id);
            if (bookEntity == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            bookService.save(
                    bookMapper.bookDTOToBook(
                            bookDTO
                    )
            );
            return new ResponseEntity<>(
                    bookMapper.bookToBookDTO(
                            bookEntity
                    ),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") Integer id) {
        try {
            Book bookEntity = bookService.find(id);
            if (bookEntity == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            bookService.delete(id);
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}