package com.tempotalent.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;

import com.tempotalent.api.tierfeature.TierFeature;

@SpringBootTest
@AutoConfigureGraphQlTester
class TierFeatureControllerTests {
  @Autowired
  private GraphQlTester tester;

  @Test
  void testFeatureAddingDeletingToTier() {
    var query = tester.document("mutation delete { deleteFeatureFromTier(tierId: 1, featureId: 1) }");
    var deleted = query.execute().path("deleteFeatureFromTier").entity(Boolean.class).get();

    query = tester.document(
        "mutation { addFeatureToTier(tierId: 1, featureId: 1, value: \"Test value\") { tier { id } feature { id } value } }");
    var results = query.execute().path("addFeatureToTier").entity(TierFeature.class).get();

    assertTrue(deleted);
    assertEquals(1, results.getFeature().getId());
    assertEquals(1, results.getTier().getId());
    assertEquals("Test value", results.getValue());
  }

  @Test
  void failToAddFeatureToTestIfEmptyValue() {
    var query = tester.document(
        "mutation { addFeatureToTier(tierId: 1, featureId: 1, value: \"\") { tier { id } feature { id } value } }");
    assertThrows(AssertionError.class, () -> query.execute().path("addFeatureToTier"));
  }

  @Test
  void failToAddFeatureToTestIfValueHasMoreThan16Chars() {
    var query = tester.document(
        "mutation { addFeatureToTier(tierId: 1, featureId: 1, value: \"aaaaaaaaaaaaaaaaa\") { tier { id } feature { id } value } }");
    assertThrows(AssertionError.class, () -> query.execute().path("addFeatureToTier"));
  }
}
