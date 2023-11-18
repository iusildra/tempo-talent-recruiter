package com.tempotalent.api.recruiter;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.stereotype.Service;

@Service
public class RecruiterService {
  private final RecruiterRepository repository;

  public RecruiterService(RecruiterRepository repository) {
    this.repository = repository;
  }

  public Recruiter registerRecruiter(String firstName, String lastName, String phone, String email, String password) {
    Recruiter recruiter = new Recruiter(UUID.randomUUID(), firstName, lastName, phone, email, password);
    return repository.save(recruiter);
  }

  public Recruiter getRecruiter(UUID id) {
    return repository.findById(id).orElseThrow();
  }

  public List<Recruiter> getRecruiterByEmail(String email) {
    var matcher = ExampleMatcher.matching().withMatcher("email", GenericPropertyMatchers.startsWith().ignoreCase());
    var recruiter = new Recruiter();
    recruiter.setEmail(email);

    var example = Example.of(recruiter, matcher);
    return repository.findAll(example);
  }

  public Boolean removeRecruiter(UUID id) {
    repository.deleteById(id);
    return true;
  }
}
