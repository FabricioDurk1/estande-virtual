package com.ufc.pi.webservice.dtos.output;

import lombok.Builder;

@Builder
public record LoginOutputDTO (
  UserOutputDTO user,
  AddressOutputDTO address,
  String accessToken
) {
}
