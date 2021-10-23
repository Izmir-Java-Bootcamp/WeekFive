package com.kodluyoruz.weekfive.model.mapper;

import com.kodluyoruz.weekfive.model.dto.UserDto;
import com.kodluyoruz.weekfive.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);

    UserDto toUserDto(User user);
}
