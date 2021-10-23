package com.kodluyoruz.weekfive.model.mapper;

import com.kodluyoruz.weekfive.model.dto.RegistrationDto;
import com.kodluyoruz.weekfive.model.entity.Registration;
import com.kodluyoruz.weekfive.model.request.RegistrationRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.time.ZonedDateTime;
import java.util.Date;

@Mapper(componentModel = "spring")
public interface RegistrationMapper {
    RegistrationMapper REGISTRATION_MAPPER = Mappers.getMapper(RegistrationMapper.class);

    @Mapping(source = "registrationDay",target = "expiryDate",qualifiedByName = "expiryDateConversion")
    Registration toRegistration(RegistrationRequest request);

    RegistrationDto toRegistrationDto(Registration registration);

    @Named("expiryDateConversion")
    static Date expiryDateConversion(int registrationDay){
        return Date.from(ZonedDateTime.now().plusDays(registrationDay).toInstant());
    }
}
