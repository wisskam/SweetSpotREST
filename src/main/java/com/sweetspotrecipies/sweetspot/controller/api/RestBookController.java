package com.sweetspotrecipies.sweetspot.controller.api;

import com.sweetspotrecipies.sweetspot.api.mapper.BookMapper;
import com.sweetspotrecipies.sweetspot.api.mapper.RecipeMapper;
import com.sweetspotrecipies.sweetspot.api.model.BookDTO;
import com.sweetspotrecipies.sweetspot.model.Book;
import com.sweetspotrecipies.sweetspot.model.User;
import com.sweetspotrecipies.sweetspot.security.BookUserDetails;
import com.sweetspotrecipies.sweetspot.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = { "http://localhost:3000" })
@RestController
@RequestMapping("${url.prefix.api.v1}/book")
public class RestBookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private RecipeMapper recipeMapper;

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


    @GetMapping("/")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Iterable<BookDTO>> index() {
        try {
            bookService.listAll().forEach(book -> {
                System.out.println(book.toString());
            });

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
    @GetMapping("/{id}/recipes")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<BookDTO> getRecipes(@PathVariable("id") Integer id) {
        try {

            return new ResponseEntity(
                    recipeMapper.map(
                            bookService.find(id).getRecipes()
                    ),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
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
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Void> createBook(@RequestBody BookDTO bookDTO, Authentication authentication) {
        try {
            BookUserDetails bookUserDetails = (BookUserDetails) authentication.getPrincipal();
            bookDTO.setUserId(bookUserDetails.getId());

            bookService.save(
                    bookMapper.bookDTOToBook(bookDTO)
            );

            return new ResponseEntity<>(null, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
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
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
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