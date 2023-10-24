package com.tempotalent.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;

import com.tempotalent.api.models.Company;

@SpringBootTest
@AutoConfigureGraphQlTester
class CompanyControllerTests {
  @Autowired
  private GraphQlTester tester;

  @Test
  void fetchCompanies() {
    var query = tester.document("query { companies { siret name } }");
    var results = query.execute().path("companies").entityList(Company.class);

    assertTrue(results.get().size() > 0);
  }

  @Test
  void fetchBySiret() {
    var query = tester.document("query { companyBySiret(siret: 123456789) { siret name } }");
    var results = query.execute().path("companyBySiret").entity(Company.class);

    assertEquals(123456789, results.get().getSiret());
  }

  @Test
  void fetchByName() {
    var query = tester.document("query { companiesByName(name: \"Comp\", page: 0, size: 10) { siret name } }");
    var results = query.execute().path("companiesByName").entityList(Company.class);

    assertEquals(3, results.get().size());

    query = tester.document("query { companiesByName(name: \"Tempo\", page: 0, size: 1) { siret name } }");
    results = query.execute().path("companiesByName").entityList(Company.class);

    assertEquals(1, results.get().size());
  }
  
}
