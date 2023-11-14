package com.tempotalent.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import com.tempotalent.AbstractTest;
import com.tempotalent.api.feature.Feature;

class FeatureControllerTests extends AbstractTest {

  @Test
  void fetchFeatures() {
    var query = tester.document("query { features { id name } }");
    var results = query.execute().path("features").entityList(Feature.class);

    assertTrue(results.get().size() > 0);
  }

  @Test
  void testFeatureById() {
    var query = tester.document("query { featureById(id: 1) { id name } }");
    var results = query.execute().path("featureById").entity(Feature.class);

    assertEquals(1, results.get().getId());
  }

  private final String createQuery = "mutation addFeature($name: String!) { addFeature(name: $name) { id name } }";

  private Feature addFeature() {
    var query = tester.document(createQuery);
    return query
        .variable("name", "Test feature").execute().path("addFeature")
        .entity(Feature.class).get();
  }

  private Boolean deleteFeature(int id) {
    var query = tester.document("mutation deleteFeature($id: Int!) { deleteFeature(id: $id) }");
    return query.variable("id", id).execute().path("deleteFeature").entity(Boolean.class).get();
  }

  @Test
  @Transactional
  void testRegisterDeleteFeature() {
    var feature = addFeature();
    var deleted = deleteFeature(feature.getId());

    assertEquals("Test feature", feature.getName());
    assertTrue(deleted);
  }

  @Test
  @Transactional
  void testTierContainingGivenFeature() {
    var query = tester
        .document("query { featureById(id: 2) { id name tierFeatures { feature { id } tier { id } value } } }");
    var result = query.execute().path("featureById").entity(Feature.class).get();

    result.getTierFeatures().forEach(tierFeature -> {
      assertNotNull(tierFeature.getFeature().getId());
      assertNotNull(tierFeature.getTier().getId());
      assertNotNull(tierFeature.getValue());
    });
  }

  @Test
  @Transactional
  void failToCreateFeatureWithEmptyName() {
    var result = tester.document(createQuery).variable("name", "");
    assertThrows(AssertionError.class, () -> result.execute().path("addFeature"));
  }

  @Test
  @Transactional
  void failToCreateFeatureWithNameOfMoreThan50Chars() {
    var result = tester.document(createQuery)
        .variable("name", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    assertThrows(AssertionError.class, () -> result.execute().path("addFeature"));
  }
}