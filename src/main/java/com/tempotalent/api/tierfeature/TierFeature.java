package com.tempotalent.api.tierfeature;

import com.tempotalent.api.feature.Feature;
import com.tempotalent.api.tier.Tier;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tier_feature")
@IdClass(TierFeatureKey.class)
public class TierFeature {
  @Id
  @Column(nullable = false)
  private Integer tierId;

  @Id
  @Column(nullable = false)
  private Integer featureId;

  @Column
  private String value;

  @ManyToOne
  @JoinColumn(name = "tier_id", referencedColumnName = "id", insertable = false)
  private Tier tier;

  @ManyToOne
  @JoinColumn(name = "feature_id", referencedColumnName = "id", insertable = false)
  private Feature feature;


  public TierFeature() {
  }
  
  public TierFeature(Integer tierId, Integer featureId, String value) {
    this.tierId = tierId;
    this.featureId = featureId;
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public Tier getTier() {
    return tier;
  }

  public void setTier(Tier tier) {
    this.tier = tier;
  }

  public Feature getFeature() {
    return feature;
  }

  public void setFeature(Feature feature) {
    this.feature = feature;
  }
}
