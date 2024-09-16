package com.ufc.pi.webservice.dtos.output;

import lombok.Builder;

@Builder
public record AddressOutputDTO (
  String zipCode,
  String street,
  String number,
  String complement,
  String neighborhood,
  String city,
  String state
) {
}
