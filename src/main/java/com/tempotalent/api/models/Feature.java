package com.tempotalent.api.models;

import jakarta.persistence.*;

@Entity
public class Feature {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public final int id;

  private String name;

  public Feature(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
