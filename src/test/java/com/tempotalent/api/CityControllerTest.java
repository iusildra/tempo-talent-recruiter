package com.tempotalent.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.graphql.test.tester.HttpGraphQlTester;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.tempotalent.api.config.CityConfig;
import com.tempotalent.api.controllers.CityController;
import com.tempotalent.api.models.City;

@SpringBootTest
class CityControllerTest {
  ApplicationContext context = new AnnotationConfigApplicationContext(CityConfig.class);

  WebTestClient client = WebTestClient.bindToApplicationContext(context).configureClient().baseUrl("/").build();

  HttpGraphQlTester tester = HttpGraphQlTester.create(client);

  @Autowired
  CityController controller;

  @Test
  void testCityListing() {
    var results = tester.document("query { cities { id name } }").execute().path("cities").entityList(City.class);
    ;

    assertNotNull(results);
    var cities = results.get();
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

  @Test
  void createCity() {
    var newCity = new City("Test City", 61);
    var city = controller.registerCity(newCity.getName(), newCity.getCountry().getId());
    assertNotNull(city);
    assertNotNull(city.getId());
    assertEquals(newCity.getName(), city.getName());
    assertEquals(newCity.getCountry().getId(), city.getCountry().getId());
  }
}
