package com.ufc.pi.webservice.dtos.output;

import lombok.Builder;

@Builder
public record UserOutputDTO (
  String name,

  String email,
  String role,
  String cpf,
  String phone,
  String birthDate
) {
}
