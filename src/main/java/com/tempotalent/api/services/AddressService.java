package com.tempotalent.api.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tempotalent.api.models.Address;
import com.tempotalent.api.repositories.AddressRepository;

@Service
public class AddressService {
  @Autowired
  private AddressRepository repository;

  public AddressService(AddressRepository repository) {
    this.repository = repository;
  }

  public List<Address> searchAddresses(String street,
      Integer zipCode, Integer cityId, Integer page, Integer size) {
    return repository.searchAddresses(street, zipCode, cityId, page, size);
  }

  public Address registerAddress(Integer num, String street, String complement, Integer zipCode, Integer cityId) {
    var address = new Address(num, street, complement, zipCode, cityId);
    System.out.println("\001b[31m " + address.getId() + "\001b[0m");
    return repository.save(address);
  }

  public Boolean deleteAddress(UUID id) {
    try {
      repository.deleteById(id);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
