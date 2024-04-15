package com.ufc.pi.webservice.dtos.input;

import java.time.LocalDate;

public record RegisterInputDTO(
  String name,
  String email,
  String password,
  String cpf,
  LocalDate birthDate,
  String phoneNumber
) {
}
