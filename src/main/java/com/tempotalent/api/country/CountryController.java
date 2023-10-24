package com.tempotalent.api.country;

import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import io.micrometer.common.lang.Nullable;

@Controller
public class CountryController {
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
  public Country countryById(@Argument Integer id) {
    return repository.findById(id).orElse(null);
  }
}
