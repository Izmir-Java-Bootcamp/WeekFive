package com.kodluyoruz.weekfive.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDto {
    private int id;
    private int userId;
    private int bookId;
    private Date expiryDate;
}
