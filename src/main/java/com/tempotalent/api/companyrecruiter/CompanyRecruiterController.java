package com.tempotalent.api.companyrecruiter;

import java.util.UUID;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;

@Controller
public class CompanyRecruiterController implements GraphQLMutationResolver, GraphQLQueryResolver {
  private CompanyRecruiterService service;

  public CompanyRecruiterController(CompanyRecruiterService service) {
    this.service = service;
  }

  @MutationMapping
  public CompanyRecruiter registerCompanyRecruiter(@Argument UUID recruiterId, @Argument Integer companyId,
      @Argument byte[] proof) {
    return service.registerCompanyRecruiter(recruiterId, companyId, proof);
  }

  @MutationMapping
  public Boolean deleteCompanyRecruiter(@Argument UUID id) {
    return service.deleteCompanyRecruiter(id);
  }

}
