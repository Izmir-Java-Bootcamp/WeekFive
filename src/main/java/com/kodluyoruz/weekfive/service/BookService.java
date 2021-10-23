package com.kodluyoruz.weekfive.service;

import com.kodluyoruz.weekfive.exception.NotFoundException;
import com.kodluyoruz.weekfive.model.dto.BookDto;
import com.kodluyoruz.weekfive.model.entity.Book;
import com.kodluyoruz.weekfive.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.kodluyoruz.weekfive.model.mapper.BookMapper.BOOK_MAPPER;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository repository;

    public BookDto getBookDto(int id) {
        Book book = getBook(id);
        return BOOK_MAPPER.toBookDto(book);
    }

    private Book getBook(int id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Book not found!"));
    }

    protected void updateAvailability(int id, boolean availability) {
        repository.setAvailability(id, availability);
    }

    public void removeBook(int id) {
        repository.deleteById(id);
    }
}
