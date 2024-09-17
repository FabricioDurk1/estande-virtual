package com.ufc.pi.webservice.dtos.input;

import java.time.LocalDate;

public record UpdateUserDTO(
    String name,
    String email,
    String cpf,
    LocalDate birthDate,
    String phoneNumber
){}
