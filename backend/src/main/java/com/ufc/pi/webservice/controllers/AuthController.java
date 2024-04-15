package com.ufc.pi.webservice.controllers;

import com.ufc.pi.webservice.dtos.input.LoginInputDTO;
import com.ufc.pi.webservice.dtos.input.RegisterInputDTO;
import com.ufc.pi.webservice.dtos.output.ActionResultOutputDTO;
import com.ufc.pi.webservice.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
  private final AuthService authService;

  @PostMapping("/register")
  public ResponseEntity<ActionResultOutputDTO> register(
    @RequestBody RegisterInputDTO input
  ){

    try {
      authService.register(input);

      return ResponseEntity.status(HttpStatus.CREATED).body(
        new ActionResultOutputDTO("Usuário criado com sucesso!")
      );
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
        new ActionResultOutputDTO(e.getMessage())
      );
    }

  }

  @PostMapping("/login")
  public ResponseEntity<?> authenticate(
    @RequestBody LoginInputDTO input
  ) {
    try {
      var output = authService.login(input);
      return ResponseEntity.ok(output);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
        new ActionResultOutputDTO(e.getMessage())
      );
    }
  }
}
