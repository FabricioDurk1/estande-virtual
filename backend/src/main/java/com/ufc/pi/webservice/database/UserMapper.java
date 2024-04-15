package com.ufc.pi.webservice.database;

import com.ufc.pi.webservice.enums.UserRole;
import com.ufc.pi.webservice.models.Address;
import com.ufc.pi.webservice.models.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

@Component
public class UserMapper implements RowMapper<User> {

  @Override
  public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
    Long id = resultSet.getLong(UserTable.ID_COLUMN);
    String name = resultSet.getString(UserTable.NAME_COLUMN);
    String email = resultSet.getString(UserTable.EMAIL_COLUMN);
    String password = resultSet.getString(UserTable.PASSWORD_COLUMN);
    String cpf = resultSet.getString(UserTable.CPF_COLUMN);
    LocalDate birthDate = LocalDate.parse(resultSet.getString(UserTable.BIRTH_DATE_COLUMN));
    UserRole role = UserRole.valueOf(resultSet.getString(UserTable.ROLE_COLUMN));
    String phoneNumber = resultSet.getString(UserTable.PHONE_NUMBER_COLUMN);

    Address address = mapAddress(resultSet);

    User user = User.builder()
      .id(id)
      .name(name)
      .email(email)
      .password(password)
      .cpf(cpf)
      .birthDate(birthDate)
      .role(role)
      .phoneNumber(phoneNumber)
      .address(address)
      .build();

    return user;
  }

  private Address mapAddress(ResultSet resultSet) throws SQLException {
    Address address = new Address();

    address.setZipCode(resultSet.getString(UserTable.ADDRESS_ZIP_CODE_COLUMN));
    address.setStreet(resultSet.getString(UserTable.ADDRESS_STREET_COLUMN));
    address.setNumber(resultSet.getString(UserTable.ADDRESS_NUMBER_COLUMN));
    address.setNeighborhood(resultSet.getString(UserTable.ADDRESS_NEIGHBORHOOD_COLUMN));
    address.setComplement(resultSet.getString(UserTable.ADDRESS_COMPLEMENT_COLUMN));
    address.setCity(resultSet.getString(UserTable.ADDRESS_CITY_COLUMN));
    address.setState(resultSet.getString(UserTable.ADDRESS_STATE_COLUMN));

    return address;
  }
}
