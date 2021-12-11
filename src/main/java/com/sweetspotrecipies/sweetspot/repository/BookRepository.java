package com.sweetspotrecipies.sweetspot.repository;

import com.sweetspotrecipies.sweetspot.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Integer> {
}

