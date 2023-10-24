package com.tempotalent.api.tier;

import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;

@Controller
public class TierController implements GraphQLMutationResolver, GraphQLQueryResolver {
  private final TierService service;

  public TierController(TierService service) {
    this.service = service;
  }

  @QueryMapping
  public List<Tier> tiers() {
    return service.fetch();
  }

  @QueryMapping
  public Tier tierById(@Argument Integer id) {
    return service.fetchById(id);
  }

  @MutationMapping
  public Tier addTier(@Argument String name, @Argument Double priceY, @Argument Double priceM,
      @Argument Double price2Y) {
    return service.add(name, priceY, priceM, price2Y);
  }

  @MutationMapping
  public Boolean deleteTier(@Argument Integer id) {
    return service.delete(id);
  }
}
