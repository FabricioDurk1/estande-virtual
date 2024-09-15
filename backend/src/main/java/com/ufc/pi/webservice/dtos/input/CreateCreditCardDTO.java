package com.ufc.pi.webservice.dtos.input;

import java.time.LocalDate;

public record CreateCreditCardDTO (
    String name,
    String flag,
    String number,
    LocalDate expirationDate,
    String securityCode,
    double creditLimit) {

}

