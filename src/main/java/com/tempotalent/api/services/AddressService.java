package com.tempotalent.api.services;

import java.util.List;

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
}
