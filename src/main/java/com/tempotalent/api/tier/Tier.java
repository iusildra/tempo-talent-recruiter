package com.tempotalent.api.tier;

import java.util.Set;

import com.tempotalent.api.subscription.Subscr;
import com.tempotalent.api.tierfeature.TierFeature;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tier")
public class Tier {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(length = 50)
  private String level;

  @Column(name = "price_biyearly")
  private Double price2Y;
  
  @Column(name = "price_yearly")
  private Double price1Y;

  @Column(name = "price_monthly")
  private Double priceM;

  @OneToMany(mappedBy = "tier")
  private Set<TierFeature> tierFeatures;

  @OneToMany(mappedBy = "tier")
  private Set<Subscr> subscriptions;

  public Tier() {}

  public Tier(Integer id, String level, Double yearlyPrice, Double monthlyPrice, Double biYearlyPrice) {
    this.id = id;
    this.level = level;
    this.price1Y = yearlyPrice;
    this.priceM = monthlyPrice;
    this.price2Y = biYearlyPrice;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getLevel() {
    return level;
  }

  public void setLevel(String level) {
    this.level = level;
  }

  public Double getPrice1Y() {
    return price1Y;
  }

  public void setPrice1Y(Double yearlyPrice) {
    this.price1Y = yearlyPrice;
  }

  public Double getPriceM() {
    return priceM;
  }

  public void setPriceM(Double monthlyPrice) {
    this.priceM = monthlyPrice;
  }

  public Double getPrice2Y() {
    return price2Y;
  }

  public void setPrice2Y(Double biYearlyPrice) {
    this.price2Y = biYearlyPrice;
  }

  public Set<TierFeature> getTierFeatures() {
    return tierFeatures;
  }
}