package com.sweetspotrecipies.sweetspot.api.mapper;

import com.sweetspotrecipies.sweetspot.api.model.BookDTO;
import com.sweetspotrecipies.sweetspot.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {
    @Mappings({
            @Mapping(target="id", source="entity.id"),
            @Mapping(target="name", source="entity.name")
    })
    BookDTO bookToBookDTO(Book entity);
    @Mappings({
            @Mapping(target="id", source="dto.id"),
            @Mapping(target="name", source="dto.name")
    })
    Book bookDTOToBook(BookDTO dto);
    List<BookDTO> map(Iterable<Book> books);
}
