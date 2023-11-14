package com.tempotalent.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.tempotalent.AbstractTest;
import com.tempotalent.api.country.Country;

class CountryControllerTest extends AbstractTest {

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
