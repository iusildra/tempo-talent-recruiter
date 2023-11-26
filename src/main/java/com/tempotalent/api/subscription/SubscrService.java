package com.tempotalent.api.subscription;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.tempotalent.api.exceptions.InvalidInput;

@Service
public class SubscrService {
  private final SubscrRepository repository;

  public SubscrService(SubscrRepository repository) {
    this.repository = repository;
  }

  public List<Subscr> getSubscrs() {
    return repository.findAll();
  }

  public Optional<Subscr> getSubscr(UUID id) {
    return repository.findById(id);
  }

  public Subscr addSubscrToRecruiter(UUID recruiterId, Integer tierId, LocalDate startDate,
      LocalDate endDate) {
    var sub = new Subscr();
    sub.setRecruiterId(recruiterId);
    sub.setStartDate(startDate);
    var matcher = ExampleMatcher.matchingAll()
        .withMatcher("recruiter_id", ExampleMatcher.GenericPropertyMatchers.exact())
        .withMatcher("start_date", ExampleMatcher.GenericPropertyMatchers.exact())
        .withIgnorePaths("tier_id", "id", "end_date");
    var existing = repository.findOne(Example.of(sub, matcher));
    var newSub = new Subscr(recruiterId, tierId, startDate, endDate);

    System.out.println("No existing ? " + existing.isEmpty());
    System.out.println("No overlaps ? " + existing
        .filter(s -> s.getEndDate().isBefore(startDate) || s.getEndDate().equals(startDate)).isEmpty());

    return existing.isEmpty()
        ? repository.save(newSub)
        : existing
            .filter(s -> s.getEndDate().isBefore(startDate) || s.getEndDate().equals(startDate))
            .map(c -> repository.save(newSub))
            .orElseThrow(() -> new InvalidInput("Existing valid subscription"));
  }

  public Boolean rmSubscrFromRecruiter(UUID id) {
    repository.deleteById(id);
    return true;
  }
}
