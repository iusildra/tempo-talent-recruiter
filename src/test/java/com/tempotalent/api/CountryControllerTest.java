package com.tempotalent.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;

import com.tempotalent.api.country.CountryController;
import com.tempotalent.api.country.Country;

@SpringBootTest
@AutoConfigureGraphQlTester
class CountryControllerTest {
  @Autowired
  private GraphQlTester tester;

  @Autowired
  CountryController controller;

  @Test
  void testCountriesSize() {
    var query = tester.document("query countriesList { countries { id name } }");
    var results = query.execute().path("countries").entityList(Country.class);

    assertEquals(197, results.get().size());
  }

  @Test
  void testCountryById() {
    var query = tester.document("query countryById($id: Int!) { countryById(id: $id) { id name } }").variable("id", 61);
    var results = query.execute().path("countryById").entity(Country.class);

    assertEquals("France", results.get().getName());
  }
}
