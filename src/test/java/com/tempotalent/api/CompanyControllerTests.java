package com.tempotalent.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import com.tempotalent.AbstractTest;
import com.tempotalent.api.company.Company;

class CompanyControllerTests extends AbstractTest {

  @Test
  void fetchCompanies() {
    var query = tester.document("query { companies { siret name } }");
    var results = query.execute().path("companies").entityList(Company.class);

    assertTrue(results.get().size() > 0);
  }

  @Test
  void testCompanyBySiret() {
    var query = tester.document("query { companyBySiret(siret: 123456789) { siret name } }");
    var results = query.execute().path("companyBySiret").entity(Company.class);

    assertEquals(123456789, results.get().getSiret());
  }

  @Test
  void testCompaniesByName() {
    var query = tester.document("query { companiesByName(name: \"Comp\", page: 0, size: 10) { siret name } }");
    var results = query.execute().path("companiesByName").entityList(Company.class);

    assertEquals(3, results.get().size());

    query = tester.document("query { companiesByName(name: \"Tempo\", page: 0, size: 1) { siret name } }");
    results = query.execute().path("companiesByName").entityList(Company.class);

    assertEquals(1, results.get().size());
  }

  private Company registerCompany() {
    var query = tester.document(createQuery);
    return query
        .variable("siret", 111111111).variable("name", "Test Company input")
        .variable("addressId", "c3d4e526-1234-5678-9012-abcdef123456").execute().path("registerCompany")
        .entity(Company.class).get();
  }

  private final String createQuery = "mutation registerCompany($siret: Int!, $name: String!, $addressId: ID!) { registerCompany(siret: $siret, name: $name, addressId: $addressId) { siret name address { id } } }";
  private final String updateQuery = "mutation updateCompany($siret: Int!, $name: String, $addressId: ID) { updateCompany(siret: $siret, name: $name, addressId: $addressId) { name address { id } } }";

  private Company updateCompany(String name, UUID addressId, int siret) {
    return tester.document(updateQuery).variable("siret", siret).variable("name", name).variable("addressId", addressId)
        .execute()
        .path("updateCompany").entity(Company.class).get();
  }

  private final String deleteQuery = "mutation deleteCompany($siret: ID!) {deleteCompany(siret: $siret)}";

  private Boolean deleteCompany(int siret) {
    return tester.document(deleteQuery).variable("siret", siret).execute().path("deleteCompany").entity(Boolean.class)
        .get();
  }

  @Test
  @Transactional
  void testRegisterDeleteCompany() {
    var company = registerCompany();
    var deleted = deleteCompany(company.getSiret());

    assertEquals(111111111, company.getSiret());
    assertTrue(deleted);
  }

  @Test
  @Transactional
  void testUpdateCompany() {
    var company = registerCompany();
    var newName = "Test Company input updated";
    var updated = updateCompany(newName, null, company.getSiret());
    var deleted = deleteCompany(company.getSiret());

    assertEquals(newName, updated.getName());
    assertTrue(deleted);
  }

  @Test
  @Transactional
  void failToRegisterCompanyWithNegativeSiret() {
    var result = tester.document(createQuery)
        .variable("siret", -1)
        .variable("name", "hhh")
        .variable("addressId", "c3d4e526-1234-5678-9012-abcdef123456");
    assertThrows(AssertionError.class, () -> result.execute().path("registerCompany").entity(Company.class).get());
  }

  @Test
  @Transactional
  void failToRegisterCompanyWithEmptyName() {
    var result = tester.document(createQuery).variable("siret", 171717).variable("name", "").variable("addressId",
        "c3d4e526-1234-5678-9012-abcdef123456");
    assertThrows(AssertionError.class, () -> result.execute().path("registerCompany"));
  }

  @Test
  @Transactional
  void failToRegisterCompanyWithNameWithMoreThan50Chars() {
    var result = tester.document(createQuery).variable("siret", 171717)
        .variable("name", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa").variable("addressId",
            "c3d4e526-1234-5678-9012-abcdef123456");
    assertThrows(AssertionError.class, () -> result.execute().path("registerCompany"));
  }

}