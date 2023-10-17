package com.tempotalent.api.controllers;

import java.util.List;

import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import com.tempotalent.api.models.City;
import com.tempotalent.api.services.CityService;

@Controller
public class CityController implements GraphQLMutationResolver, GraphQLQueryResolver {

  @Autowired
  private final CityService service;

  public CityController(CityService service) {
    this.service = service;
  }

  @QueryMapping
  public List<City> cities() {
    return service.fetchCities();
  }

  @QueryMapping
  public City cityById(@Argument Integer id) {
    return service.fetchCityById(id);
  }

  @QueryMapping
  public List<City> citiesByCountryId(@Argument Integer countryId, @Argument Integer page, @Argument Integer size) {
    return service.fetchCitiesByCountryId(countryId, page, size);
  }

  @MutationMapping
  public City registerCity(@Argument String name, @Argument Integer countryId) {
    return service.registerCity(name, countryId);
  }
}
