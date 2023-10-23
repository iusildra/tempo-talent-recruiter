package com.tempotalent.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Service;

import com.tempotalent.api.models.City;
import com.tempotalent.api.repositories.CityRepository;

import io.micrometer.common.lang.Nullable;

@Service
public class CityService {
  @Autowired
  private CityRepository repository;

  public CityService(CityRepository repository) {
    this.repository = repository;
  }

  public List<City> fetchCities() {
    return repository.findAll();
  }

  @Nullable
  public City fetchCityById(@Argument Integer id) {
    return repository.findById(id).orElse(null);
  }

  public List<City> fetchCitiesByName(@Argument String name, @Argument Integer page, @Argument Integer size) {
    return repository.findByName(name, page, size);
  }

  public List<City> fetchCitiesByCountryId(@Argument Integer countryId, @Argument Integer page, @Argument Integer size) {
    return repository.findByCountryId(countryId, page, size);
  }

  public City registerCity(@Argument String name, @Argument Integer countryId) {
    var city = new City(name, countryId);
    return repository.save(city);
  }

}
