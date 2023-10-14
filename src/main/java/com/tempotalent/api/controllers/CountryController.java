package com.tempotalent.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.tempotalent.api.models.Country;
import com.tempotalent.api.repositories.CountryRepository;

import io.micrometer.common.lang.Nullable;

@Controller
public class CountryController {
  @Autowired
  private final CountryRepository repository;

  public CountryController(CountryRepository repository) {
    this.repository = repository;
  }

  @QueryMapping
  public List<Country> countries() {
    return repository.findAll();
  }

  @Nullable
  @QueryMapping
  public Country countryById(@Argument int id) {
    return repository.findById(id).orElse(null);
  }
}
