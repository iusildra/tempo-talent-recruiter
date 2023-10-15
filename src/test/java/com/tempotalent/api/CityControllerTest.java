package com.tempotalent.api;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tempotalent.api.controllers.CityController;

@SpringBootTest
class CityControllerTest {
  @Autowired
  CityController controller;

  @Test
  void testCityListing() {
    var cities = controller.cities();
    assertNotNull(cities);
    assertTrue(cities.size() > 0);
  }

  @Test
  void testCityById() {
    var city = controller.cityById(1);
    assertNotNull(city);
  }

  @Test
  void testCitiesByCountryId() {
    var cities = controller.citiesByCountryId(61, 0, 10);
    assertNotNull(cities);
    assertTrue(cities.size() > 0);
  }
}
