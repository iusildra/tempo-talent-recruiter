package com.tempotalent.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tempotalent.api.models.City;
import com.tempotalent.api.models.Country;
import com.tempotalent.api.repositories.CityRepository;

import io.micrometer.common.lang.Nullable;

@Service
public class CityService {
  @Autowired
  private CityRepository repository;

  public CityService(CityRepository repository) {
    this.repository = repository;
  }

  public List<City> fetch() {
    return repository.findAll();
  }

  @Nullable
  public City fetchById(Integer id) {
    return repository.findById(id).orElse(null);
  }

  public List<City> fetchByName(String name, Integer page, Integer size) {
    ExampleMatcher matcher = ExampleMatcher.matching()
        .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.startsWith().ignoreCase());

    var city = new City();
    city.setName(name);

    Example<City> example = Example.of(city, matcher);
    Pageable pageable = PageRequest.of(page, size);

    return repository.findAll(example, pageable).getContent();
  }

  public List<City> fetchByCountryId(Integer countryId, Integer page, Integer size) {
    Country country = new Country();
    country.setId(countryId);

    ExampleMatcher matcher = ExampleMatcher.matching()
        .withMatcher("id", ExampleMatcher.GenericPropertyMatchers.exact());

    var city = new City();
    city.setCountry(country);

    Example<City> example = Example.of(city, matcher);
    Pageable pageable = PageRequest.of(page, size);

    return repository.findAll(example, pageable).getContent();
  }

  public City registerCity(String name, Integer countryId) {
    var city = new City(name, countryId);
    return repository.save(city);
  }

}
