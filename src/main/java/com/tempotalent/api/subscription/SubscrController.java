package com.tempotalent.api.subscription;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;

@Controller
public class SubscrController implements GraphQLQueryResolver, GraphQLMutationResolver {
  private final SubscrService service;

  public SubscrController(SubscrService service) {
    this.service = service;
  }

  @QueryMapping
  public List<Subscr> subscriptions() {
    return service.getSubscrs();
  }

  @QueryMapping
  public Subscr subscriptionById(@Argument UUID id) {
    return service.getSubscr(id).orElse(null);
  }

  @MutationMapping
  public Subscr addSubscrToRecruiter(@Argument UUID recruiterId, @Argument Integer tierId,
      @Argument LocalDate startDate, @Argument LocalDate endDate) {
    return service.addSubscrToRecruiter(recruiterId, tierId, startDate, endDate);
  }

  @MutationMapping
  public Boolean deleteSubscrFromRecruiter(@Argument UUID id) {
    return service.rmSubscrFromRecruiter(id);
  }
}
