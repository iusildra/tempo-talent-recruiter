package com.tempotalent.api.country;

import java.util.List;

import com.tempotalent.api.city.City;

import jakarta.persistence.*;

@Entity
@Table(name = "country")
public class Country {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(length = 50)
  private String name;

  @OneToMany
  private List<City> cities;

  public Country() {}

  public Country(Integer id, String name) {
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
}
