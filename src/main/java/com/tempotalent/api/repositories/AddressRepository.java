package com.tempotalent.api.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tempotalent.api.models.Address;
import com.tempotalent.api.models.City;

public interface AddressRepository extends JpaRepository<Address, UUID> {
  default List<Address> searchAddresses(String street, Integer zipCode, Integer cityId,
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

    return findAll(example, pageable).getContent();
  }
}