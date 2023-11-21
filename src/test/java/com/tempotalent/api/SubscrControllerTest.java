package com.tempotalent.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.tempotalent.AbstractTest;
import com.tempotalent.api.subscription.Subscr;

import jakarta.transaction.Transactional;

class SubscrControllerTest extends AbstractTest {

  @Test
  void testFindAll() {
    var query = tester.document("query { subscriptions { id } }");
    var results = query.execute().path("subscriptions").entityList(Subscr.class).get();

    assertTrue(results.size() > 0);
  }

  @Test
  void testFindById() {
    var query = tester.document("query { subscriptionById(id: \"e094a7b2-5c9f-4b3d-b7c3-55908d9c535e\") { id } }");
    var results = query.execute().path("subscriptionById").entity(Subscr.class).get();

    assertEquals(UUID.fromString("e094a7b2-5c9f-4b3d-b7c3-55908d9c535e"), results.getId());
  }

  @Test
  @Transactional
  void testSubscrAddingDeleting() {
    var query = tester.document(
        "mutation { addSubscrToRecruiter(recruiterId: \"a1b2c3d4-1234-5678-9012-abcdef123456\", tierId: 1, startDate: \"2023-10-31\", endDate: \"2023-11-07\") { id startDate endDate } }");
    var results = query.execute().path("addSubscrToRecruiter").entity(Subscr.class).get();

    query = tester.document(
        "mutation { deleteSubscrFromRecruiter(id: \"" + results.getId() +"\") }");
    var deleted = query.execute().path("deleteSubscrFromRecruiter").entity(Boolean.class).get();

    assertTrue(deleted);
    assertNotNull(results.getId());
  }

  @Test
  @Transactional
  void failSubscrAddingWhenOverlappingWithExisting() {
    var query = tester.document(
        "mutation { addSubscrToRecruiter(recruiterId: \"a1b2c3d4-1234-5678-9012-abcdef123456\", tierId: 1, startDate: \"2021-01-01\", endDate: \"2023-11-07\") { tier { id } recruiter { id } startDate endDate } }");

    assertThrows(AssertionError.class, () -> query.execute().path("addSubscrToRecruiter"));
  }

  // @Test
  // @Transactional
  // void failSubscrAddingWhenStartingBeforeToday() {
  //   var query = tester.document(
  //       "mutation { addSubscrToRecruiter(recruiterId: \"a1b2c3d4-1234-5678-9012-abcdef123456\", tierId: 1, startDate: \"2022-10-31\", endDate: \"2023-11-07\") { tier { id } recruiter { id } startDate endDate } }");

  //   assertThrows(AssertionError.class, () -> query.execute().path("addSubscrToRecruiter"));
  // }
}
