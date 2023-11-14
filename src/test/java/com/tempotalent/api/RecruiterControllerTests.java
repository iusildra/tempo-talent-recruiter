package com.tempotalent.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.tempotalent.AbstractTest;
import com.tempotalent.api.recruiter.Recruiter;

import jakarta.transaction.Transactional;

class RecruiterControllerTests extends AbstractTest{

  @Test
  @Transactional
  void testRecruiterById() {
    var query = tester.document(
        "query { recruiterById(id: \"a1b2c3d4-1234-5678-9012-abcdef123456\") { id firstName lastName phone email } }");
    var result = query.execute().path("recruiterById").entity(Recruiter.class).get();

    assertEquals(UUID.fromString("a1b2c3d4-1234-5678-9012-abcdef123456"), result.getId());
  }

  @Test
  @Transactional
  void testRecruiterByEmail() {
    var query = tester.document(
        "query { recruiterByEmail(email: \"john\") { id firstName lastName phone email } }");
    var result = query.execute().path("recruiterByEmail").entityList(Recruiter.class).get();

    assertTrue(result.stream().anyMatch(r -> r.getEmail().equals("john@doe.com")));
  }

  private final String createQuery = "mutation registerRecruiter($firstName: String!, $lastName: String!, $phone: String!, $email: String!, $password: String!) { registerRecruiter(firstName: $firstName, lastName: $lastName, phone: $phone, email: $email, password: $password) { id firstName lastName phone email } }";

  @Test
  void testRegisterThenDeleteRecruiter() {
    var query = tester.document(createQuery);
    var result = query
        .variable("firstName", "Test").variable("lastName", "Recruiter").variable("phone", "+33 0123456789")
        .variable("email", "test@test.com").variable("password", "testtest").execute().path("registerRecruiter").entity(Recruiter.class).get();
    assertNotNull(result.getId());
    query = tester.document("mutation deleteRecruiter($id: ID!) { deleteRecruiter(id: $id) }");
    var deleted = query.variable("id", result.getId()).execute().path("deleteRecruiter").entity(Boolean.class).get();
    assertTrue(deleted);
  }

  @Test
  void failRegisterRecruiterWithEmptyFirstName() {
    var query = tester.document(createQuery);
    var result = query
        .variable("firstName", "").variable("lastName", "Recruiter").variable("phone", "0123456789")
        .variable("email", "test@test.com").variable("password", "testtest");
    assertThrows(AssertionError.class, () -> result.execute().path("registerRecruiter"));
  }

  @Test
  void failRegisterRecruiterWithMoreThan50CharsFirstName() {
    var query = tester.document(createQuery);
    var result = query
        .variable("firstName", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa").variable("lastName", "Recruiter").variable("phone", "0123456789")
        .variable("email", "test@test.com").variable("password", "testtest");
    assertThrows(AssertionError.class, () -> result.execute().path("registerRecruiter"));
  }

  @Test
  void failRegisterRecruiterWithEmptyLastName() {
    var query = tester.document(createQuery);
    var result = query
        .variable("firstName", "john").variable("lastName", "").variable("phone", "0123456789")
        .variable("email", "test@test.com").variable("password", "testtest");
    assertThrows(AssertionError.class, () -> result.execute().path("registerRecruiter"));
  }

  @Test
  void failRegisterRecruiterWithMoreThan50CharsLastName() {
    var query = tester.document(createQuery);
    var result = query
        .variable("firstName", "john").variable("lastName", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa").variable("phone", "0123456789")
        .variable("email", "test@test.com").variable("password", "testtest");
    assertThrows(AssertionError.class, () -> result.execute().path("registerRecruiter"));
  }

  @Test
  void failRegisterRecruiterWithInvalidPhone() {
    var query = tester.document(createQuery);
    var result = query
        .variable("firstName", "john").variable("lastName", "doe").variable("phone", "0123456789")
        .variable("email", "test@test.com").variable("password", "testtest");
    assertThrows(AssertionError.class, () -> result.execute().path("registerRecruiter"));
  }

  @Test
  void failRegisterRecruiterWithEmptyEmail() {
    var query = tester.document(createQuery);
    var result = query
        .variable("firstName", "john").variable("lastName", "doe").variable("phone", "0123456789")
        .variable("email", "").variable("password", "testtest");
    assertThrows(AssertionError.class, () -> result.execute().path("registerRecruiter"));
  }

  @Test
  void failRegisterRecruiterWithInvalidEmail1() {
    var query = tester.document(createQuery);
    var result = query
        .variable("firstName", "john").variable("lastName", "doe").variable("phone", "0123456789")
        .variable("email", "test").variable("password", "testtest");
    assertThrows(AssertionError.class, () -> result.execute().path("registerRecruiter"));
  }
  @Test
  void failRegisterRecruiterWithInvalidEmail2() {
    var query = tester.document(createQuery);
    var result = query
        .variable("firstName", "john").variable("lastName", "doe").variable("phone", "0123456789")
        .variable("email", "test@test").variable("password", "testtest");
    assertThrows(AssertionError.class, () -> result.execute().path("registerRecruiter"));
  }

  @Test
  void failRegisterRecruiterWithLessThan8CharactersPassword() {
    var query = tester.document(createQuery);
    var result = query
        .variable("firstName", "john").variable("lastName", "doe").variable("phone", "0123456789")
        .variable("email", "test@test.com").variable("password", "test");
    assertThrows(AssertionError.class, () -> result.execute().path("registerRecruiter"));
  }

  @Test
  void failRegisterRecruiterWithMoreThan128CharactersPassword() {
    var query = tester.document(createQuery);
    var result = query
        .variable("firstName", "john").variable("lastName", "doe").variable("phone", "0123456789")
        .variable("email", "test@test.com").variable("password", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    assertThrows(AssertionError.class, () -> result.execute().path("registerRecruiter"));
  }
}
