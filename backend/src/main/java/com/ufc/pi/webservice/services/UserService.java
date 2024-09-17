package com.ufc.pi.webservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufc.pi.webservice.data.structures.DoublyLinkedList;
import com.ufc.pi.webservice.dtos.input.UpdateAddressDTO;
import com.ufc.pi.webservice.dtos.input.UpdateUserDTO;
import com.ufc.pi.webservice.models.Address;
import com.ufc.pi.webservice.models.User;
import com.ufc.pi.webservice.repositories.UserRepository;

@Service
public class UserService {
 
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private AuthService authService;

  public DoublyLinkedList<User> findByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  public void createUser(User user) {
    userRepository.create(user);
  }

  public void update(UpdateUserDTO input) throws Exception {
    var user = authService.getSessionUser();

    var userWithCPF = userRepository.findByCPFAndNotId(input.cpf(), user.getId());

    if (userWithCPF.getHead() != null) {
      throw new Exception("Já existe um usuário com esse CPF");
    }

    user.setName(input.name());
    user.setCpf(input.cpf());
    user.setPhoneNumber(input.phoneNumber());
    user.setBirthDate(input.birthDate());
    
    userRepository.update(user);
  }

  public void updateAddress(UpdateAddressDTO address) throws Exception {
    var user = authService.getSessionUser();

    Address newAddress = new Address();
    newAddress.setStreet(address.street());
    newAddress.setNumber(address.number());
    newAddress.setComplement(address.complement());
    newAddress.setNeighborhood(address.neighborhood());
    newAddress.setCity(address.city());
    newAddress.setState(address.state());
    newAddress.setZipCode(address.zipCode());
    
    userRepository.updateAddress(newAddress, user.getId());
  }
}
