package com.tempotalent.api;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.tempotalent.AbstractTest;
import com.tempotalent.api.address.Address;

class AddressControllerTests extends AbstractTest {

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

  private final String createQuery = "mutation registerAddress($num: Int, $street: String!, $complement: String, $zipCode: Int!, $cityId: Int!) { registerAddress(num: $num, street: $street, complement: $complement, zipCode: $zipCode, cityId: $cityId) { id street zipCode city { id name } } }";
  private final String deleteQuery = "mutation deleteAddress($id: ID!) { deleteAddress(id: $id) }";

  @Test
  void registerAddress() {
    var query = tester.document(createQuery);
    var results = query
        .variable("num", 1).variable("street", "Rue de Scala").variable("zipCode", 34000)
        .variable("complement", "Dotty").variable("cityId", 1).execute().path("registerAddress")
        .entity(Address.class);

    assertNotNull(results.get().getId());

    var deleteResults = tester.document(deleteQuery)
        .variable("id", results.get().getId()).execute().path("deleteAddress")
        .entity(Boolean.class);
    assertTrue(deleteResults.get());
  }

  @Test
  void registerAndDeleteAddressWithoutNum() {
    var query = tester.document(createQuery);
    var results = query
        .variable("street", "Rue de Scala").variable("zipCode", 34000).variable("cityId", 1).execute()
        .path("registerAddress")
        .entity(Address.class);

    assertNotNull(results.get().getId());

    var deleteResults = tester.document(deleteQuery)
        .variable("id", results.get().getId()).execute().path("deleteAddress")
        .entity(Boolean.class);
    assertTrue(deleteResults.get());
  }

  @Test
  void registerAndDeleteAddressWithoutComplement() {
    var query = tester.document(createQuery);
    var results = query
        .variable("num", 1).variable("street", "Rue de Scala").variable("zipCode", 34000).variable("cityId", 1)
        .execute()
        .path("registerAddress")
        .entity(Address.class);

    assertNotNull(results.get().getId());

    var deleteResults = tester.document(deleteQuery)
        .variable("id", results.get().getId()).execute().path("deleteAddress")
        .entity(Boolean.class);
    assertTrue(deleteResults.get());
  }

  @Test
  void failToRegisterAddressWithNegativeNumber() {
    var query = tester.document(createQuery);
    var result = query
        .variable("num", -1).variable("street", "Rue de Scala").variable("zipCode", 34000).variable("cityId", 1);
    assertThrows(AssertionError.class, () -> result.execute().path("registerAddress"));
  }

  @Test
  void failToRegisterAddressWithNegativeZip() {
    var query = tester.document(createQuery);
    var result = query
        .variable("num", 1).variable("street", "Rue de Scala").variable("zipCode", -34000).variable("cityId", 1);
    assertThrows(AssertionError.class, () -> result.execute().path("registerAddress"));
  }
}
