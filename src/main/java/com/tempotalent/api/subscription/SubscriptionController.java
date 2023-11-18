package com.tempotalent.api.subscription;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;

@Controller
public class SubscriptionController implements GraphQLQueryResolver, GraphQLMutationResolver {
  private SubscriptionService service;

  public SubscriptionController(SubscriptionService service) {
    this.service = service;
  }

  @MutationMapping
  public Subscription addSubscriptionToRecruiter(@Argument UUID recruiterId, @Argument Integer tierId,
      @Argument LocalDate startDate, @Argument LocalDate endDate) {
    return service.addSubscriptionToRecruiter(recruiterId, tierId, startDate, endDate);
  }

  @MutationMapping
  public Boolean deleteSubscriptionFromRecruiter(@Argument UUID recruiterId, @Argument Integer tierId) {
    return service.rmSubscriptionFromRecruiter(recruiterId, tierId);
  }
}
