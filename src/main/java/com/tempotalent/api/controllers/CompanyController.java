package com.tempotalent.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.tempotalent.api.models.Company;
import com.tempotalent.api.services.CompanyService;

import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;

@Controller
public class CompanyController implements GraphQLMutationResolver, GraphQLQueryResolver {
  @Autowired
  private final CompanyService service;

  public CompanyController(CompanyService service) {
    this.service = service;
  }

  @QueryMapping
  public List<Company> companies() {
    return service.fetchCompanies();
  }

  @QueryMapping
  public Company companyBySiret(@Argument Integer siret) {
    return service.fetchBySiret(siret);
  }

  @QueryMapping
  public List<Company> companiesByName(@Argument String name, @Argument Integer page, @Argument Integer size) {
    var p = page;
    var s = size;
    if (page == null)
      p = 0;
    if (size == null)
      s = 10;
    return service.fetchByName(name, p, s);
  }

  @MutationMapping
  public Company registerCompany(@Argument Integer siret, @Argument String name, @Argument String address) {
    return service.registerCompany(siret, name, address);
  }

  @MutationMapping
  public Company updateCompany(@Argument Integer siret, @Argument String name, @Argument String address) {
    return service.updateCompany(siret, name, address);
  }

  @MutationMapping
  public Boolean deleteCompany(@Argument Integer siret) {
    return service.deleteCompany(siret);
  }

}
