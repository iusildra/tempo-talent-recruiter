package com.tempotalent.api.models;

import jakarta.persistence.*;

@Entity
public class SubscriptionTier {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String level;
  @Column(name = "price_biyearly")
  private Integer priceBiyearly;
  @Column(name = "price_yearly")
  private Integer priceYearly;
  @Column(name = "price_monthly")
  private Integer priceMonthly;

  public SubscriptionTier(Integer id, String level, Integer priceBiyearly, Integer priceYearly, Integer priceMonthly) {
    this.id = id;
    this.level = level;
    this.priceBiyearly = priceBiyearly;
    this.priceYearly = priceYearly;
    this.priceMonthly = priceMonthly;
  }

  public String getLevel() {
    return level;
  }

  public void setLevel(String level) {
    this.level = level;
  }

  public Integer getPriceBiyearly() {
    return priceBiyearly;
  }

  public void setPriceBiyearly(Integer priceBiyearly) {
    this.priceBiyearly = priceBiyearly;
  }

  public Integer getPriceYearly() {
    return priceYearly;
  }

  public void setPriceYearly(Integer priceYearly) {
    this.priceYearly = priceYearly;
  }

  public Integer getPriceMonthly() {
    return priceMonthly;
  }

  public void setPriceMonthly(Integer priceMonthly) {
    this.priceMonthly = priceMonthly;
  }
}
