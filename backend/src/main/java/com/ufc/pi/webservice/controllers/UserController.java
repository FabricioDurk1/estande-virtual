package com.ufc.pi.webservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufc.pi.webservice.dtos.input.UpdateAddressDTO;
import com.ufc.pi.webservice.dtos.input.UpdateUserDTO;
import com.ufc.pi.webservice.dtos.output.ActionResultOutputDTO;
import com.ufc.pi.webservice.services.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/v1/users")
public class UserController {
  
  @Autowired
  private UserService userService;

  @PutMapping
  public ResponseEntity<?> updateUser(@RequestBody UpdateUserDTO updateUserDTO) {
      
      try {
        userService.update(updateUserDTO);

        return ResponseEntity.status(HttpStatus.OK).body(new ActionResultOutputDTO("Usuário atualizado com sucesso"));
      } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ActionResultOutputDTO(e.getMessage()));
      }
  }

  @PutMapping("/address")
  public ResponseEntity<?> updateUserAddress(@RequestBody UpdateAddressDTO updateAddressDTO) {

    try {
      userService.updateAddress(updateAddressDTO);
      return ResponseEntity.status(HttpStatus.OK).body(new ActionResultOutputDTO("Endereço atualizado com sucesso"));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ActionResultOutputDTO(e.getMessage()));
    }
  }
}
