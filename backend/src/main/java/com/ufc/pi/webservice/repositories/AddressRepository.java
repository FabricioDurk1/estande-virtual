package com.ufc.pi.webservice.repositories;

import com.ufc.pi.webservice.models.Address;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository {
  void update(Address address, long userId);

}
