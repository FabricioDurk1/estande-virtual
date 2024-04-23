package com.ufc.pi.webservice.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufc.pi.webservice.models.User;
import com.ufc.pi.webservice.repositories.UserRepository;

@Service
public class UserService {
 
  @Autowired
  private UserRepository userRepository;

  public Optional<User> findByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  public void createUser(User user) {
    userRepository.create(user);
  }

}
