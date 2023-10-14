package com.tempotalent.api.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.tempotalent.api.models.Address;
import com.tempotalent.api.repositories.AddressRepository;

public class AddressController {
  private final AddressRepository addressRepository;

  public AddressController(AddressRepository addressRepository) {
    this.addressRepository = addressRepository;
  }

  @GetMapping
  public List<Address> index() {
    return addressRepository.findAll();
  }
  
  @GetMapping("/{uuid}")
  public Optional<Address> get(@PathVariable UUID id) {
    return addressRepository.findById(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Address create(Address address) {
    return addressRepository.save(address);
  }

  @PutMapping("/{uuid}")
  public Address update(@PathVariable UUID id, Address address) {
    return addressRepository.save(address);
  }

  @DeleteMapping("/{uuid}")
  public void delete(@PathVariable UUID id) {
    addressRepository.deleteById(id);
  }
}
