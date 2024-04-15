package com.ufc.pi.webservice.repositories;

import com.ufc.pi.webservice.models.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository {

  Optional<User> findByEmail(String email);
  void create(User user);
}
