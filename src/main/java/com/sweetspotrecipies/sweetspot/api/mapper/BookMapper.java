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
            @Mapping(target="name", source="entity.name"),
            @Mapping(target="description", source="entity.description"),
            @Mapping(target="subtitle", source="entity.subtitle"),
            @Mapping(target="imageUrl", source="entity.imageUrl"),
    })
    BookDTO bookToBookDTO(Book entity);
    @Mappings({
            @Mapping(target="id", source="dto.id"),
            @Mapping(target="name", source="dto.name"),
            @Mapping(target="description", source="dto.description"),
            @Mapping(target="subtitle", source="dto.subtitle"),
            @Mapping(target="imageUrl", source="dto.imageUrl"),
    })
    Book bookDTOToBook(BookDTO dto);
    List<BookDTO> map(Iterable<Book> books);
}
