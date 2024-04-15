package com.ufc.pi.webservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admins")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
  @RequestMapping
  public String test(){
    return "Usuário autorizado como ADMIN";
  }
}
