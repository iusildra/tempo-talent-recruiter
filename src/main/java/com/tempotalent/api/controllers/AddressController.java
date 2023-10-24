package com.tempotalent.api.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.tempotalent.api.models.Address;
import com.tempotalent.api.services.AddressService;

import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;

@Controller
public class AddressController implements GraphQLMutationResolver, GraphQLQueryResolver {
  @Autowired
  private final AddressService addressService;

  public AddressController(AddressService addressService) {
    this.addressService = addressService;
  }

  @QueryMapping
  public List<Address> searchAddresses(@Argument String street,
      @Argument Integer zipCode, @Argument Integer cityId, @Argument Integer page, @Argument Integer size) {
    return addressService.searchAddresses(street, zipCode, cityId, page, size);
  }

  @MutationMapping
  public Address registerAddress(@Argument Integer num, @Argument String street, @Argument String complement, @Argument Integer zipCode, @Argument Integer cityId) {
    return addressService.registerAddress(num, street, complement, zipCode, cityId);
  }

  @MutationMapping
  public Boolean deleteAddress(@Argument UUID id) {
    return addressService.deleteAddress(id);
  }

}
