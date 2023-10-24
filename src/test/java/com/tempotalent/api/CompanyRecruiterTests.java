package com.tempotalent.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;

import com.tempotalent.api.companyrecruiter.CompanyRecruiter;

import jakarta.transaction.Transactional;

@SpringBootTest
@AutoConfigureGraphQlTester
class CompanyRecruiterTests {
  @Autowired
  private GraphQlTester tester;

  @Test
  @Transactional
  void testRegisterDelete() {
    var query = tester.document("mutation { registerCompanyRecruiter(recruiterId: \"a1b2c3d4-1234-5678-9012-abcdef123456\", companyId: 836293720) { id companyId recruiterId } }");
    var result = query.execute().path("registerCompanyRecruiter").entity(CompanyRecruiter.class).get();
    
    query = tester.document(
        "mutation { deleteCompanyRecruiter(id: \""+result.getId()+"\") }");
    var deleted = query.execute().path("deleteCompanyRecruiter").entity(Boolean.class).get();

    assertTrue(deleted);
    assertEquals(836293720, result.getCompanyId());
  }
}
