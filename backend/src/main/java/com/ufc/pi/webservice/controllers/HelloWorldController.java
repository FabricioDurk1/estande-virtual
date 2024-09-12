package com.ufc.pi.webservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class HelloWorldController {
  @GetMapping
  public String getMethodName() {
    return new String("Hello World");
  }
}
