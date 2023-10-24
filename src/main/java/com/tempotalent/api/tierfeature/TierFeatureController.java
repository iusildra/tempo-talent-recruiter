package com.tempotalent.api.tierfeature;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;

@Controller
public class TierFeatureController implements GraphQLMutationResolver, GraphQLQueryResolver {
  private final TierFeatureService tierFeatureService;

  public TierFeatureController(TierFeatureService tierFeatureService) {
    this.tierFeatureService = tierFeatureService;
  }

  @MutationMapping
  public TierFeature addFeatureToTier(@Argument Integer tierId, @Argument Integer featureId, @Argument String value) {
    return tierFeatureService.addFeatureToTier(tierId, featureId, value);
  }

  @MutationMapping
  public Boolean deleteFeatureFromTier(@Argument Integer tierId, @Argument Integer featureId) {
    return tierFeatureService.removeFeatureFromTier(tierId, featureId);
  }
}
