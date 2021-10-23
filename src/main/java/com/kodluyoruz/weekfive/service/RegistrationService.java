package com.kodluyoruz.weekfive.service;

import com.kodluyoruz.weekfive.exception.BusinessException;
import com.kodluyoruz.weekfive.model.dto.BookDto;
import com.kodluyoruz.weekfive.model.dto.RegistrationDto;
import com.kodluyoruz.weekfive.model.dto.UserDto;
import com.kodluyoruz.weekfive.model.entity.Registration;
import com.kodluyoruz.weekfive.model.request.RegistrationRequest;
import com.kodluyoruz.weekfive.repository.RegistrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static com.kodluyoruz.weekfive.model.mapper.RegistrationMapper.REGISTRATION_MAPPER;

@Service
@RequiredArgsConstructor
public class RegistrationService {
    private final RegistrationRepository repository;
    private final UserService userService;
    private final BookService bookService;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public RegistrationDto register(RegistrationRequest request) {
        validateUser(request);
        validateBook(request);

        Registration registration = REGISTRATION_MAPPER.toRegistration(request);
        Registration savedRegistration = repository.save(registration);

        bookService.updateAvailability(request.getBookId(), false);
        userService.incrementRegistrationsCount(request.getUserId());

        return REGISTRATION_MAPPER.toRegistrationDto(savedRegistration);
    }

    private void validateBook(RegistrationRequest request) {
        BookDto bookDto = bookService.getBookDto(request.getBookId());
        if (!bookDto.isAvailable()) {
            throw new BusinessException("Book all ready registered!");
        }
    }

    private void validateUser(RegistrationRequest request) {
        UserDto userDto = userService.getUserDto(request.getUserId());
        if (userDto.getRegisteredBookCount() >= 3) {
            throw new BusinessException("User registration limit is reached!");
        }
    }
}
