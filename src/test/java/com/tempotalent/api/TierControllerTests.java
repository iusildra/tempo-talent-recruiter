package com.tempotalent.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import com.tempotalent.AbstractTest;
import com.tempotalent.api.tier.Tier;

class TierControllerTests extends AbstractTest {

  @Test
  void fetchTiers() {
    var query = tester.document("query { tiers { id level } }");
    var results = query.execute().path("tiers").entityList(Tier.class);

    assertTrue(results.get().size() > 0);
  }

  @Test
  void testTierById() {
    var query = tester.document("query { tierById(id: 1) { id level } }");
    var results = query.execute().path("tierById").entity(Tier.class);

    assertEquals(1, results.get().getId());
  }

  private final String createQuery = "mutation addTier($name: String!, $price2Y: Float!, $price1Y: Float!, $priceM: Float!) { addTier(name: $name, price2Y: $price2Y, price1Y: $price1Y, priceM: $priceM) { id level } }";

  private Tier addTier() {
    var query = tester.document(createQuery);
    return query
        .variable("name", "Test tier").variable("price2Y", 350).variable("price1Y", 200.0).variable("priceM", 20.0)
        .execute().path("addTier")
        .entity(Tier.class).get();
  }

  private Boolean deleteTier(int id) {
    var query = tester.document("mutation deleteTier($id: Int!) { deleteTier(id: $id) }");
    return query.variable("id", id).execute().path("deleteTier").entity(Boolean.class).get();
  }

  @Test
  @Transactional
  void testRegisterDeleteTier() {
    var tier = addTier();
    var deleted = deleteTier(tier.getId());

    assertEquals("Test tier", tier.getLevel());
    assertTrue(deleted);
  }

  @Test
  @Transactional
  void testTierContainingGivenFeature() {
    var query = tester.document("query { tierById(id: 2) { id level tierFeatures { feature { id } value } } }");
    var result = query.execute().path("tierById").entity(Tier.class).get();

    result.getTierFeatures().forEach(tierFeature -> {
      assertNotNull(tierFeature.getValue());
      // assertNotNull(tierFeature.getFeature().getId()); // TODO: not null values with graphiql
      // assertNotNull(tierFeature.getTier().getId()); // TODO: not null values with graphiql
    });
    assertTrue(result.getTierFeatures().size() > 0);
  }

  // @Test
  // @Transactional
}