package com.ufc.pi.webservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customers")
@PreAuthorize("hasRole('CUSTOMER')")
public class CustomerController {
  @RequestMapping
  public String test(){
    return "Usuário autorizado como cliente";
  }
}
