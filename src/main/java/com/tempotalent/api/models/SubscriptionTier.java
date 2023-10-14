package com.tempotalent.api.models;

import jakarta.persistence.*;

@Entity
public class SubscriptionTier {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public final int id;
  private String level;
  @Column(name = "price_biyearly")
  private int priceBiyearly;
  @Column(name = "price_yearly")
  private int priceYearly;
  @Column(name = "price_monthly")
  private int priceMonthly;

  public SubscriptionTier(int id, String level, int priceBiyearly, int priceYearly, int priceMonthly) {
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

  public int getPriceBiyearly() {
    return priceBiyearly;
  }

  public void setPriceBiyearly(int priceBiyearly) {
    this.priceBiyearly = priceBiyearly;
  }

  public int getPriceYearly() {
    return priceYearly;
  }

  public void setPriceYearly(int priceYearly) {
    this.priceYearly = priceYearly;
  }

  public int getPriceMonthly() {
    return priceMonthly;
  }

  public void setPriceMonthly(int priceMonthly) {
    this.priceMonthly = priceMonthly;
  }
}
