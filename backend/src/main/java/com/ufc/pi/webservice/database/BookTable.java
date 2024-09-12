package com.ufc.pi.webservice.database;

public record BookTable() {
  public static final String TABLE_NAME = "books";
  
  public static final String ID_COLUMN = "id";
  public static final String TITLE_COLUMN = "title";
  public static final String DESCRIPTION_COLUMN = "description";
  public static final String PRICE_COLUMN = "price";
  public static final String QUANTITY_COLUMN = "quantity";
  public static final String PUBLISHER_ID_COLUMN = "publisher_id";
  public static final String AUTHOR_ID_COLUMN = "author_id";
}
