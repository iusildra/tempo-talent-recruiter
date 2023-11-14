package com.tempotalent.api.subscription;

import java.time.LocalDate;
import java.util.UUID;

import com.tempotalent.api.recruiter.Recruiter;
import com.tempotalent.api.tier.Tier;

import jakarta.persistence.*;

@Entity
@Table(name = "subscription")
@IdClass(SubscriptionKey.class)
public class Subscription {
  @Id
  @Column(nullable = false)
  private UUID recruiterId;
  @Id
  @Column(nullable = false)
  private Integer tierId;

  @Column(name = "start_date")
  private LocalDate startDate;

  @Column(name = "end_date")
  private LocalDate endDate;

  @OneToOne
  @JoinColumn(name = "recruiter_id", insertable = false, updatable = false)
  private Recruiter recruiter;

  @ManyToOne
  @JoinColumn(name = "tier_id", insertable = false, updatable = false)
  private Tier tier;

  public Subscription() {
  }

  public Subscription(UUID recruiterId, Integer tierId, LocalDate startDate, LocalDate endDate) {
    this.recruiterId = recruiterId;
    this.tierId = tierId;
    this.startDate = startDate;
    this.endDate = endDate;
  }

  public UUID getRecruiterId() {
    return recruiterId;
  }

  public void setRecruiterId(UUID recruiterId) {
    this.recruiterId = recruiterId;
  }

  public Integer getTierId() {
    return tierId;
  }

  public void setTierId(Integer tierId) {
    this.tierId = tierId;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  public Recruiter getRecruiter() {
    return recruiter;
  }

  public void setRecruiter(Recruiter recruiter) {
    this.recruiter = recruiter;
  }

  public Tier getTier() {
    return tier;
  }

  public void setTier(Tier tier) {
    this.tier = tier;
  }
}
