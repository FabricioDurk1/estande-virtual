package com.ufc.pi.webservice.models;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class CreditCard {
    private long id;
    private String name;
    private String flag;
    private String number;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate expirationDate;
    private String securityCode;
    private double creditLimit;
    private long userId;
}
