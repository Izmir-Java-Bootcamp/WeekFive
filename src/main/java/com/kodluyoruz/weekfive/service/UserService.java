package com.kodluyoruz.weekfive.service;

import com.kodluyoruz.weekfive.exception.NotFoundException;
import com.kodluyoruz.weekfive.model.dto.UserDto;
import com.kodluyoruz.weekfive.model.entity.User;
import com.kodluyoruz.weekfive.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.kodluyoruz.weekfive.model.mapper.UserMapper.USER_MAPPER;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public UserDto getUserDto(int id) {
        User user = getUser(id);
        return USER_MAPPER.toUserDto(user);
    }

    private User getUser(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found!"));
    }
}
