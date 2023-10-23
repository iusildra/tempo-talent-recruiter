package com.tempotalent.api;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;

import com.tempotalent.api.models.Address;

@SpringBootTest
@AutoConfigureGraphQlTester
class AddressControllerTests {
  @Autowired
  private GraphQlTester tester;

  private final String baseQuery = "query searchAddresses($street: String!, $zipCode: Int, $cityId: Int, $page: Int!, $size: Int!) { searchAddresses(street: $street, zipCode: $zipCode, cityId: $cityId, page: $page, size: $size) { id street zipCode city { id name } } }";

  @Test
  void matchCorrectInput() {
    var query = tester.document(baseQuery)
        .variable("page", 0).variable("size", 10);
    var results = query
        .variable("street", "Rue").variable("zipCode", 34000).variable("cityId", 61).execute().path("searchAddresses")
        .entityList(Address.class);

    assertTrue(results.get().size() > 0);
  }

  @Test
  void matchWithoutCityId() {
    var query = tester.document(baseQuery)
        .variable("page", 0).variable("size", 10);
    var results = query
        .variable("street", "Rue").variable("zipCode", 34000).execute().path("searchAddresses")
        .entityList(Address.class);

    assertTrue(results.get().size() > 0);
  }

  @Test
  void matchWithoutZipCode() {
    var query = tester.document(baseQuery)
        .variable("page", 0).variable("size", 10);
    var results = query
        .variable("street", "Rue").variable("cityId", 61).execute().path("searchAddresses")
        .entityList(Address.class);

    assertTrue(results.get().size() > 0);
  }

  @Test
  void matchWithoutCityIdAndZipCode() {
    var query = tester.document(baseQuery)
        .variable("page", 0).variable("size", 10);
    var results = query
        .variable("street", "Rue").execute().path("searchAddresses")
        .entityList(Address.class);

    assertTrue(results.get().size() > 0);
  }
}
