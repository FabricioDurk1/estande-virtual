package com.ufc.pi.webservice.database;

public record UserTable() {
  public static final String TABLE_NAME = "users";

  public static final String ID_COLUMN = "id";
  public static final String NAME_COLUMN = "name";
  public static final String EMAIL_COLUMN = "email";
  public static final String PASSWORD_COLUMN = "password";
  public static final String CPF_COLUMN = "cpf";
  public static final String BIRTH_DATE_COLUMN = "birth_date";
  public static final String ROLE_COLUMN = "role";
  public static final String PHONE_NUMBER_COLUMN = "phone_number";

  public static final String ADDRESS_STREET_COLUMN = "address_street";
  public static final String ADDRESS_NUMBER_COLUMN = "address_number";
  public static final String ADDRESS_COMPLEMENT_COLUMN = "address_complement";
  public static final String ADDRESS_NEIGHBORHOOD_COLUMN = "address_neighborhood";
  public static final String ADDRESS_CITY_COLUMN = "address_city";
  public static final String ADDRESS_STATE_COLUMN = "address_state";
  public static final String ADDRESS_ZIP_CODE_COLUMN = "address_zip_code";
}
