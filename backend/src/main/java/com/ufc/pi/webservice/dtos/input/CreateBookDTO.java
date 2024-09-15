package com.ufc.pi.webservice.dtos.input;

import lombok.Data;

@Data
public class CreateBookDTO {
    private String title;
    private String description;
    private double price;
    private int quantity;
    private Long publisherId;
    private Long authorId;
}
