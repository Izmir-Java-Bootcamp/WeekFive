package com.kodluyoruz.weekfive;

import com.kodluyoruz.weekfive.model.entity.Author;
import com.kodluyoruz.weekfive.model.entity.Book;
import com.kodluyoruz.weekfive.model.entity.User;
import com.kodluyoruz.weekfive.repository.AuthorRepository;
import com.kodluyoruz.weekfive.repository.BookRepository;
import com.kodluyoruz.weekfive.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
@RequiredArgsConstructor
public class Initializer implements CommandLineRunner {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final AuthorRepository authorRepository;

    @Override
    public void run(String... args) throws Exception {
//        Author author = authorRepository.save(Author.builder()
//                .name("test")
//                .surname("test")
//                .build());
        for (int i = 0; i < 100; i++) {
            bookRepository.save(Book.builder()
                    .title("initialize book " + i)
                    .authorId(1)
                    .build());
        }


//        userRepository.save(User.builder()
//                .username("deneme")
//                .password("12345")
//                .build());
    }
}
