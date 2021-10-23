package com.kodluyoruz.weekfive.service;

import com.kodluyoruz.weekfive.exception.NotFoundException;
import com.kodluyoruz.weekfive.model.dto.BookDto;
import com.kodluyoruz.weekfive.model.entity.Book;
import com.kodluyoruz.weekfive.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository repository;

    public BookDto getBookDto(int id){
        Book book = repository.findById(id).orElseThrow(() -> new NotFoundException("User not found!"));

    }
}
