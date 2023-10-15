package com.tempotalent.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.tempotalent.api.models.City;
import com.tempotalent.api.repositories.CityRepository;

import io.micrometer.common.lang.Nullable;

@Controller
public class CityController {
  @Autowired
  private final CityRepository repository;

  public CityController(CityRepository repository) {
    this.repository = repository;
  }

  @QueryMapping
  public List<City> cities() {
    return repository.findAll();
  }

  @Nullable
  @QueryMapping
  public City cityById(@Argument Integer id) {
    return repository.findById(id).orElse(null);
  }

  @QueryMapping
  public List<City> citiesByCountryId(@Argument Integer countryId, @Argument Integer page, @Argument Integer size) {
    return repository.findByCountryId(countryId, page, size);
  }
}
