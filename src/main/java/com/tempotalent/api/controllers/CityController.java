package com.tempotalent.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.tempotalent.api.models.City;
import com.tempotalent.api.services.CityService;

import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;

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
  public List<City> citiesByName(@Argument String name, @Argument Integer page, @Argument Integer size) {
    return service.fetchCitiesByName(name, page, size);
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
