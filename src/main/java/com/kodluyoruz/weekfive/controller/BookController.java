package com.kodluyoruz.weekfive.controller;

import com.kodluyoruz.weekfive.model.dto.BookDto;
import com.kodluyoruz.weekfive.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("books")
@RequiredArgsConstructor
public class BookController {
    private final BookService service;


    @DeleteMapping("{id}")
    public void removeBook(@PathVariable int id) {
        service.removeBook(id);
    }

    @GetMapping("{id}")
    public BookDto getBook(@PathVariable int id) {
        return service.getBookDto(id);
    }
}
