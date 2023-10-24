package com.tempotalent.api.address;

import java.util.List;
import java.util.UUID;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;

@Controller
public class AddressController implements GraphQLMutationResolver, GraphQLQueryResolver {
  private final AddressService addressService;

  public AddressController(AddressService addressService) {
    this.addressService = addressService;
  }

  @QueryMapping
  public Address addressById(@Argument UUID id) {
    return addressService.addressById(id).orElse(null);
  }

  @QueryMapping
  public List<Address> searchAddresses(@Argument String street,
      @Argument Integer zipCode, @Argument Integer cityId, @Argument Integer page, @Argument Integer size) {
    var p = page;
    var s = size;
    if (page == null)
      p = 0;
    if (size == null)
      s = 10;
    return addressService.searchAddresses(street, zipCode, cityId, p, s);
  }

  @MutationMapping
  public Address registerAddress(@Argument Integer num, @Argument String street, @Argument String complement,
      @Argument Integer zipCode, @Argument Integer cityId) {
    return addressService.registerAddress(num, street, complement, zipCode, cityId);
  }

  @MutationMapping
  public Boolean deleteAddress(@Argument UUID id) {
    return addressService.deleteAddress(id);
  }

}
