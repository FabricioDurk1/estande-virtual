package com.ufc.pi.webservice.dtos.input;

public record UpdateAddressDTO(
    String street,
    String number,
    String complement,
    String neighborhood,
    String city,
    String state,
    String zipCode
){}
