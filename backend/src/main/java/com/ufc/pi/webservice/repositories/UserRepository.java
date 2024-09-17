package com.ufc.pi.webservice.repositories;

import com.ufc.pi.webservice.data.structures.DoublyLinkedList;
import com.ufc.pi.webservice.models.Address;
import com.ufc.pi.webservice.models.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {
  DoublyLinkedList<User> findByEmail(String email);
  DoublyLinkedList<User> findById(long id);
  DoublyLinkedList<User> findByCPFAndNotId(String cpf, long id);
  void create(User user);
  void updateAddress(Address address, long userId);
  void update(User user);
}
