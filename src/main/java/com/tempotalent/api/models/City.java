package com.tempotalent.api.models;

import jakarta.persistence.*;

@Entity
@Table(name = "city")
public class City {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public final int id;

  @Column(length = 50)
  private String name;

  public final int country;

  public City(int id, String name, int country) {
    this.id = id;
    this.name = name;
    this.country = country;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
