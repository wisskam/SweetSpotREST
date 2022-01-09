package com.sweetspotrecipies.sweetspot.test;

import com.sweetspotrecipies.sweetspot.model.Book;
import com.sweetspotrecipies.sweetspot.repository.BookRepository;
import com.sweetspotrecipies.sweetspot.service.BookService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;
import java.util.stream.StreamSupport;
import static org.mockito.Mockito.doReturn;
@SpringBootTest
public class BookServiceTest {
    @Autowired
    private BookService service;
    @MockBean
    private BookRepository repository;
    @Test
    void listAll_whenFound_thenReturnResult() {
        // Setup our mock repository
        Book book = new Book(
                new Date(),
                new Date(),
                "Nowe przepisy 2021",
                "Najlepsze w 2021",
                null,
                null,
                null
        );
        Book book2 = new Book(
                new Date(),
                new Date(),
                "Nowe przepisy 2022",
                "Najlepsze w 2022",
                null,
                null,
                null
        );
        doReturn(
                Arrays.asList(book, book2)
        ).when(
                repository
        )
        .findAll();

        Iterable<Book> books = service.listAll(1);
        // Assert the response
        Assertions.assertEquals(
                2,
                StreamSupport.stream(books.spliterator(), false).count()
        );
    }
    @Test
    void find_whenFound_thenReturnResult() {
        Book book = new Book(
                new Date(),
                new Date(),
                "Nowe przepisy 2021",
                "Najlepsze w 2021",
                null,
                null,
                null
        );
        doReturn(Optional.of(book)).when(repository).findById(1);
        Book result = service.find(1);
        Assertions.assertTrue((result != null) );
        Assertions.assertSame(result, book);
    }
    @Test
    void find_whenNotFound_thenReturnNull() {
        doReturn(Optional.empty()).when(repository).findById(1);
        Book result = service.find(1);
        Assertions.assertTrue((result == null) );
    }
}