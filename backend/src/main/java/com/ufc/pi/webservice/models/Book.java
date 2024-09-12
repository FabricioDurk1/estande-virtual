package com.ufc.pi.webservice.models;

import lombok.Data;

@Data
public class Book {
    private long id;
    private String title;
    private String description;
    private double price;
    private int quantity;
    
    private Publisher publisher;
    private Author author;
}
