package com.tempotalent.api.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.tempotalent.api.models.Address;
import com.tempotalent.api.models.City;
import com.tempotalent.api.repositories.AddressRepository;

@Service
public class AddressService {
  @Autowired
  private AddressRepository repository;

  public AddressService(AddressRepository repository) {
    this.repository = repository;
  }

  public List<Address> searchAddresses(String street, Integer zipCode, Integer cityId,
      Integer page, Integer size) {
    var address = new Address();
    address.setStreet(street);
    address.setZipCode(zipCode);
    address.setCity(new City());
    address.getCity().setId(cityId);

    ExampleMatcher matcher = ExampleMatcher.matching().withIgnorePaths("id", "num", "complement", "zipCode", "city")
        .withMatcher("street", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
        .withMatcher("zipCode", ExampleMatcher.GenericPropertyMatchers.exact())
        .withMatcher("city", ExampleMatcher.GenericPropertyMatchers.exact());

    var example = Example.of(address, matcher);
    var pageable = PageRequest.of(page, size);

    return repository.findAll(example, pageable).getContent();
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
