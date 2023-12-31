package com.tempotalent.api.company;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import io.micrometer.common.lang.NonNull;
import io.micrometer.common.lang.Nullable;

@Service
public class CompanyService {
  private CompanyRepository repository;

  public CompanyService(CompanyRepository repository) {
    this.repository = repository;
  }

  public List<Company> fetchCompanies() {
    return repository.findAll();
  }

  public Company fetchBySiret(Integer siret) {
    var company = new Company();
    company.setSiret(siret);

    var matcher = ExampleMatcher.matching()
        .withMatcher("siret", ExampleMatcher.GenericPropertyMatchers.exact());

    var example = Example.of(company, matcher);

    return repository.findOne(example).orElse(null);
  }

  public List<Company> fetchByName(String name, Integer page, Integer size) {
    var matcher = ExampleMatcher.matching()
        .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.startsWith().ignoreCase());

    var company = new Company();
    company.setName(name);

    var example = Example.of(company, matcher);
    var pageable = PageRequest.of(page, size);

    return repository.findAll(example, pageable).getContent();
  }

  public Company registerCompany(Integer siret, String name, UUID addressId) {
    var company = new Company(siret, name, addressId);
    return repository.save(company);
  }

  private Company setFields(Company company, String name, UUID addressId) {
    if (name != null)
      company.setName(name);
    if (addressId != null)
      company.setAddressId(addressId);
    return company;
  }

  @Nullable
  public Company updateCompany(@NonNull Integer siret, @Nullable String name, @Nullable UUID address) {
    return repository.findById(siret).map(c -> repository.save(setFields(c, name, address))).orElse(null);
  }

  public Boolean deleteCompany(Integer siret) {
    try {
      repository.deleteById(siret);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
