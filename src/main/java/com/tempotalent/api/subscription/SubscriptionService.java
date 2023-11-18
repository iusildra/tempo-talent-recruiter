package com.tempotalent.api.subscription;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.tempotalent.api.exceptions.InvalidInput;

@Service
public class SubscriptionService {
  private final SubscriptionRepository repository;

  public SubscriptionService(SubscriptionRepository repository) {
    this.repository = repository;
  }

  public Subscription addSubscriptionToRecruiter(UUID recruiterId, Integer tierId, LocalDate startDate,
      LocalDate endDate) {
    var sub = new Subscription();
    sub.setRecruiterId(recruiterId);
    sub.setTierId(tierId);
    var matcher = ExampleMatcher.matching().withMatcher("recruiter_id", ExampleMatcher.GenericPropertyMatchers.exact()).withIgnorePaths("tier_id");
    var existing = repository.findOne(Example.of(sub, matcher));
    var newSub = new Subscription(recruiterId, tierId, startDate, endDate);

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

  public Boolean rmSubscriptionFromRecruiter(UUID recruiterId, Integer tierId) {
    repository.deleteById(new SubscriptionKey(recruiterId, tierId));
    return true;
  }
}
