package com.kodluyoruz.weekfive.model.mapper;

import com.kodluyoruz.weekfive.model.dto.BookDto;
import com.kodluyoruz.weekfive.model.dto.PageableBookDto;
import com.kodluyoruz.weekfive.model.dto.UserDto;
import com.kodluyoruz.weekfive.model.entity.Book;
import com.kodluyoruz.weekfive.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookMapper BOOK_MAPPER = Mappers.getMapper(BookMapper.class);

    BookDto toBookDto(Book book);

    List<BookDto> toBookDtoList(List<Book> book);
}
