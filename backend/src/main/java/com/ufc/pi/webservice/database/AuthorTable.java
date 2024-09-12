package com.ufc.pi.webservice.database;

public record AuthorTable() {
  public static final String TABLE_NAME = "authors";
  
  public static final String ID_COLUMN = "id";
  public static final String NAME_COLUMN = "name";
}
