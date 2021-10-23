package com.kodluyoruz.weekfive.controller;

import com.kodluyoruz.weekfive.model.dto.RegistrationDto;
import com.kodluyoruz.weekfive.model.request.RegistrationRequest;
import com.kodluyoruz.weekfive.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("registrations")
@RequiredArgsConstructor
public class RegistrationController {
    private final RegistrationService service;

    @PostMapping
    public RegistrationDto register(@RequestBody RegistrationRequest request){
        return service.register(request);
    }
}
