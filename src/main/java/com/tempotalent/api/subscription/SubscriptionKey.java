package com.tempotalent.api.subscription;

import java.util.UUID;

import jakarta.persistence.Column;

public class SubscriptionKey {
  @Column(name="recruiter_id", nullable = false)
  private UUID recruiterId;
  @Column(name="tier_id", nullable = false)
  private Integer tierId;

  public SubscriptionKey() {
  }

  public SubscriptionKey(UUID recruiterId, Integer tierId) {
    this.recruiterId = recruiterId;
    this.tierId = tierId;
  }

  public UUID getRecruiterId() {
    return recruiterId;
  }

  public Integer getTierId() {
    return tierId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof SubscriptionKey))
      return false;
    SubscriptionKey that = (SubscriptionKey) o;
    return this.recruiterId.equals(that.recruiterId) && this.tierId.equals(that.tierId);
  }

  @Override
  public int hashCode() {
    return 31 * recruiterId.hashCode() + tierId.hashCode();
  }
}
