package com.tempotalent.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tempotalent.api.controllers.CountryController;
import com.tempotalent.api.models.Country;

@SpringBootTest
class CountryControllerTest {
  @Autowired
  CountryController controller;

  @Test
  void testCountriesSize() {
    List<Country> countries = controller.countries();
    assertEquals(197, countries.size());
  }

  @Test
  void testCountryById() {
    Country country = controller.countryById(61);
    assertEquals("France", country.getName());
  }
}
