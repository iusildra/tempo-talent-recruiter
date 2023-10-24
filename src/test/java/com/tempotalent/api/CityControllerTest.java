package com.tempotalent.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;

import com.tempotalent.api.models.City;

@SpringBootTest
@AutoConfigureGraphQlTester
class CityControllerTest {
  @Autowired
  private GraphQlTester tester;

  @Test
  void testCityListing() {
    var results = tester.document("query citiesList { cities { id name } }").execute().path("cities")
        .entityList(City.class);
    ;

    assertTrue(results.get().size() > 0);
  }

  @Test
  void testCityById() {
    var results = tester.document("query cityById($id: Int!) { cityById(id: $id) { id name } }").variable("id", 1)
        .execute().path("cityById").entity(City.class);

    assertNotNull(results.get());
  }

  @Test
  void testCitiesByCountryId() {
    var results = tester.document(
        "query citiesByCountryId($countryId: Int!, $page: Int!, $size: Int!) { citiesByCountryId(countryId: $countryId, page: $page, size: $size) { id name } }")
        .variable("countryId", 61).variable("page", 0).variable("size", 10).execute().path("citiesByCountryId")
        .entityList(City.class);
    assertTrue(results.get().size() > 0);
  }

  @Test
  void testCitiesByName() {
    var query = tester.document(
        "query citiesByName($name: String!, $page: Int!, $size: Int!) { citiesByName(name: $name, page: $page, size: $size) { id name } }")
        .variable("page", 0).variable("size", 10);
    var name = "Montpellier";
    var results = query
        .variable("name", name).execute().path("citiesByName")
        .entityList(City.class);

    assertTrue(results.get().size() > 0);

    name = "Mont";
    results = query
        .variable("name", name).execute().path("citiesByName")
        .entityList(City.class);

    assertTrue(results.get().size() > 0);
  }

  @Test
  void createCity() {
    var query = tester.document(
        "mutation registerCity($name: String!, $countryId: Int!) { registerCity(name: $name, countryId: $countryId) { id name country { id }} }");
    var name = "Test City";
    var countryId = 61;
    var resultsExact = query
        .variable("name", name).variable("countryId", countryId).execute().path("registerCity").entity(City.class);

    var city = resultsExact.get();
    assertNotNull(city);
    assertNotNull(city.getId());
    assertEquals(name, city.getName());
    assertEquals(countryId, city.getCountry().getId());
  }
}
