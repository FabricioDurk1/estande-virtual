package com.ufc.pi.webservice.models;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CreditCard {
    private long id;
    private String name;
    private String flag;
    private String number;
    private LocalDate expirationDate;
    private String securityCode;
    private double creditLimit;
    private long userId;
}
