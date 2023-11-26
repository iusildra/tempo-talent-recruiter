package com.tempotalent.api.tierfeature;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;

public class TierFeatureKey implements Serializable {
  @Column(name="tier_id", nullable = false)
  private Integer tierId;

  @Column(name="feature_id", nullable = false)
  private Integer featureId;

  public TierFeatureKey() {
  }

  public TierFeatureKey(Integer tierId, Integer featureId) {
    this.tierId = tierId;
    this.featureId = featureId;
  }

  public Integer getTier() {
    return tierId;
  }

  public Integer getFeature() {
    return featureId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof TierFeatureKey))
      return false;
    TierFeatureKey that = (TierFeatureKey) o;
    return this.tierId.equals(that.tierId) && this.featureId.equals(that.featureId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(tierId, featureId);
  }

  @Override
  public String toString() {
    System.out.println(super.toString());
    return super.toString();
  }
}
