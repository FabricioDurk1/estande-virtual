package com.ufc.pi.webservice.services;

import com.ufc.pi.webservice.data.structures.DoublyLinkedList;
import com.ufc.pi.webservice.dtos.input.LoginInputDTO;
import com.ufc.pi.webservice.dtos.input.RegisterInputDTO;
import com.ufc.pi.webservice.dtos.output.AddressOutputDTO;
import com.ufc.pi.webservice.dtos.output.LoginOutputDTO;
import com.ufc.pi.webservice.dtos.output.UserOutputDTO;
import com.ufc.pi.webservice.enums.UserRole;
import com.ufc.pi.webservice.repositories.UserRepository;
import com.ufc.pi.webservice.services.security.JwtService;
import com.ufc.pi.webservice.models.User;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
  private final PasswordEncoder passwordEncoder;
  private final UserRepository userRepository;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public void register(RegisterInputDTO input) throws Exception {
    DoublyLinkedList<User> possibleUser = userRepository.findByEmail(input.email());

    if(possibleUser.getHead() != null){
      throw new Exception("Já existe um usuário com esse e-mail");
    }

    String encodedPassword = passwordEncoder.encode(input.password());

    User user = User.builder()
      .name(input.name())
      .email(input.email())
      .password(encodedPassword)
      .cpf(input.cpf())
      .role(UserRole.CUSTOMER)
      .birthDate(input.birthDate())
      .phoneNumber(input.phoneNumber())
      .build();

    userRepository.create(user);
  }

  public LoginOutputDTO login(LoginInputDTO input) throws Exception {
    DoublyLinkedList<User> possibleUser = userRepository.findByEmail(input.email());

    if(possibleUser.getHead() == null){
      throw new Exception("Usuário não encontrado");
    }

    User user = possibleUser.getHead().data;

    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
      input.email(),
      input.password()
    ));

    UserOutputDTO userOutput = UserOutputDTO.builder()
      .name(user.getName())
      .email(user.getEmail())
      .role(user.getRole().name())
      .cpf(user.getCpf())
      .phone(user.getPhoneNumber())
      .birthDate(user.getBirthDate().toString())
      .build();

    AddressOutputDTO addressOutputDTO = null;

    if (user.getAddress() != null) {
      addressOutputDTO = AddressOutputDTO.builder()
      .zipCode(user.getAddress().getZipCode())
      .street(user.getAddress().getStreet())
      .number(user.getAddress().getNumber())
      .complement(user.getAddress().getComplement())
      .neighborhood(user.getAddress().getNeighborhood())
      .city(user.getAddress().getCity())
      .state(user.getAddress().getState())
      .build();
    }

    LoginOutputDTO output = LoginOutputDTO.builder()
      .user(userOutput)
      .address(addressOutputDTO)
      .accessToken(jwtService.generateToken(user))
      .build();

    return output;
  }

  public User getSessionUser(){
    return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
  }
}
