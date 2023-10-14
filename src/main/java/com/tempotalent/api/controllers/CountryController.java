package com.tempotalent.api.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tempotalent.api.models.Country;
import com.tempotalent.api.repositories.CountryRepository;

import io.micrometer.common.lang.Nullable;

@RestController
@RequestMapping("/api/v1/countries")
public class CountryController {
  @Autowired
  private final CountryRepository countryRepository;

  public CountryController(CountryRepository countryRepository) {
    this.countryRepository = countryRepository;
  }

  @GetMapping
  public List<Country> index() {
    // return countryRepository.findAll();
    return Arrays.asList(new Country(1, "Australia"), new Country(2, "New Zealand"));
  }

  @Nullable
  @GetMapping("{id}")
  public Country get(@PathVariable int id) {
    return countryRepository.findById(id).orElse(null);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Country create(Country country) {
    return countryRepository.save(country);
  }

  @Nullable
  @PutMapping("{id}")
  public Country update(@PathVariable int id, Country country) {
    if (countryRepository.findById(id).isEmpty() || country.id() != id)
      return null;
    else
      return countryRepository.save(country);
  }

  @DeleteMapping("{id}")
  public void delete(@PathVariable int id) {
    countryRepository.deleteById(id);
  }
}
