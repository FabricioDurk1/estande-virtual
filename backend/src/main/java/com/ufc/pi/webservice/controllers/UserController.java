package com.ufc.pi.webservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufc.pi.webservice.models.User;
import com.ufc.pi.webservice.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/v1/users")
public class UserController {
  
  @Autowired
  private UserService userService;

  @GetMapping("/{email}")
  public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
      return userService.findByEmail(email)
      .map(user -> ResponseEntity.ok().body(user))
      .orElse(ResponseEntity.notFound().build());
  }
  
  @PostMapping
  public ResponseEntity<Void> createUser(@RequestBody User user) {
    userService.createUser(user);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

}
