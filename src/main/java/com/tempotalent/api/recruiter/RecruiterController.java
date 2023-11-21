package com.tempotalent.api.recruiter;

import java.util.List;
import java.util.UUID;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;

@Controller
public class RecruiterController implements GraphQLQueryResolver, GraphQLMutationResolver {
  private final RecruiterService service;

  public RecruiterController(RecruiterService service) {
    this.service = service;
  }

  @QueryMapping
  public Recruiter recruiterById(@Argument UUID id) {
    return service.getRecruiter(id);
  }

  @QueryMapping
  public List<Recruiter> recruiterByEmail(@Argument String email) {
    return service.getRecruiterByEmail(email);
  }

  @MutationMapping
  public Recruiter registerRecruiter(@Argument String firstName, @Argument String lastName, @Argument String phone,
      @Argument String email, @Argument String password) {
    return service.registerRecruiter(firstName, lastName, phone, email, password);
  }

  @MutationMapping
  public Boolean deleteRecruiter(@Argument UUID id) {
    return service.removeRecruiter(id);
  }

  @MutationMapping
  public Boolean addReviewToCandidate(@Argument String title, @Argument Integer rating, @Argument String comment,
      @Argument UUID candidateId, @Argument UUID recruiterId) {
    try {
      return service.addReviewToCandidate(title, rating, comment, candidateId, recruiterId);
    } catch (Exception e) {
      return false;
    }
  }
}
