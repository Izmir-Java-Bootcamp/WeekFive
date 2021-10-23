package com.kodluyoruz.weekfive.service;

import com.kodluyoruz.weekfive.exception.BusinessException;
import com.kodluyoruz.weekfive.model.dto.BookDto;
import com.kodluyoruz.weekfive.model.dto.RegistrationDto;
import com.kodluyoruz.weekfive.model.dto.UserDto;
import com.kodluyoruz.weekfive.model.entity.Registration;
import com.kodluyoruz.weekfive.model.request.RegistrationRequest;
import com.kodluyoruz.weekfive.repository.RegistrationRepository;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RegistrationServiceTest {
    @InjectMocks
    private RegistrationService sut;

    @Mock
    private RegistrationRepository repository;

    @Mock
    private UserService userService;

    @Mock
    private BookService bookService;

    @Captor
    private ArgumentCaptor<Registration> registrationArgumentCaptor;

    private RegistrationRequest request;
    private UserDto userDto;
    private BookDto bookDto;

    @BeforeEach
    void setUp() {
        request = RegistrationRequest.builder()
                .userId(1)
                .bookId(1)
                .registrationDay(2)
                .build();

        userDto = UserDto.builder()
                .id(1)
                .registeredBookCount(0)
                .build();
        bookDto = BookDto.builder()
                .id(1)
                .available(true)
                .build();
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 4, 6})
    void it_should_throw_exception_when_user_has_more_than_3_book(int bookCount) {
        //Given
        UserDto customUserDto = userDto.toBuilder()
                .registeredBookCount(bookCount)
                .build();
        when(userService.getUserDto(eq(1))).thenReturn(customUserDto);

        //When
        //Then
        Assertions.assertThrows(BusinessException.class, () -> sut.register(request));
    }

    @Test
    void it_should_throw_exception_when_book_is_not_available() {
        //Given
        bookDto.setAvailable(false);
        when(bookService.getBookDto(eq(1))).thenReturn(bookDto);
        when(userService.getUserDto(eq(1))).thenReturn(userDto);
        //When
        //Then
        Assertions.assertThrows(BusinessException.class,
                () -> sut.register(request));
    }

    @Test
    void it_should_return_dto_when_everything_ok() {
        //Given
        Date now = Date.from(ZonedDateTime.now().toInstant());
        Date expectedExpiryDate = Date.from(ZonedDateTime.now().plusDays(request.getRegistrationDay()).toInstant());

        when(bookService.getBookDto(anyInt())).thenReturn(bookDto);
        when(userService.getUserDto(anyInt())).thenReturn(userDto);
        when(repository.save(registrationArgumentCaptor.capture())).thenReturn(Registration.builder()
                .id(1)
                .bookId(1)
                .userId(1)
                .expiryDate(now)
                .build());
        //When
        RegistrationDto registrationDto = sut.register(request);
        //Then
        Assertions.assertEquals(1, registrationDto.getBookId());
        Assertions.assertEquals(1, registrationDto.getId());
        Assertions.assertEquals(1, registrationDto.getUserId());
        Assertions.assertEquals(now, registrationDto.getExpiryDate());

        Registration captorValue = registrationArgumentCaptor.getValue();
        Assertions.assertEquals(1, captorValue.getUserId());
        Assertions.assertEquals(1, captorValue.getBookId());
        Assertions.assertNull(captorValue.getId());
//        Assertions.assertEquals(expectedExpiryDate, captorValue.getExpiryDate());
        Assertions.assertEquals(DateUtils.round(expectedExpiryDate, Calendar.SECOND),
                DateUtils.round(captorValue.getExpiryDate(), Calendar.SECOND));
    }
}
