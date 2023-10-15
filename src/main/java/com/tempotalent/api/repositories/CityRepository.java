package com.tempotalent.api.repositories;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tempotalent.api.models.City;
import com.tempotalent.api.models.Country;

public interface CityRepository extends JpaRepository<City, Integer> {
  default List<City> findByCountryId(Integer countryId, Integer page, Integer size) {
    Country country = new Country();
    country.setId(countryId);

    ExampleMatcher matcher = ExampleMatcher.matching()
        .withIgnorePaths("id", "name", "country.name");

    var city = new City();
    city.setCountry(country);

    Example<City> example = Example.of(city, matcher);

    Pageable pageable = PageRequest.of(page, size);

    return findAll(example, pageable).getContent();
  }
}
