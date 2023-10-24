package com.tempotalent.api.feature;

import java.util.Set;

import com.tempotalent.api.tierfeature.TierFeature;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "feature")
public class Feature {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(length = 50)
  private String name;

  @OneToMany(mappedBy = "feature")
  private Set<TierFeature> tierFeatures;

  public Feature() {}

  public Feature(Integer id, String name) {
    this.id = id;
    this.name = name;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<TierFeature> getTierFeatures() {
    return tierFeatures;
  }
}