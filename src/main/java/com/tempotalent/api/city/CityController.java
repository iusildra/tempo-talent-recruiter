package com.tempotalent.api.city;

import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;

@Controller
public class CityController implements GraphQLMutationResolver, GraphQLQueryResolver {
  private final CityService service;

  public CityController(CityService service) {
    this.service = service;
  }

  @QueryMapping
  public List<City> cities() {
    return service.fetch();
  }

  @QueryMapping
  public City cityById(@Argument Integer id) {
    return service.fetchById(id);
  }

  @QueryMapping
  public List<City> citiesByName(@Argument String name, @Argument Integer page, @Argument Integer size) {
    var p = page; // TODO add a graphQL directive to automatically set default values
    var s = size;
    if (page == null)
      p = 0;
    if (size == null)
      s = 10;
    return service.fetchByName(name, p, s);
  }

  @QueryMapping
  public List<City> citiesByCountryId(@Argument Integer countryId, @Argument Integer page, @Argument Integer size) {
    var p = page;
    var s = size;
    if (page == null)
      p = 0;
    if (size == null)
      s = 10;
    return service.fetchByCountryId(countryId, p, s);
  }

  @MutationMapping
  public City registerCity(@Argument String name, @Argument Integer countryId) {
    return service.registerCity(name, countryId);
  }
}
