package com.tempotalent.api.models;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "city")
public class City {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(length = 50)
  private String name;

  @ManyToOne
  @JoinColumn(name = "country_id")
  private Country country;

  @OneToMany
  private List<Address> addresses;

  public City() {}

  public City(Integer id, String name) {
    this.id = id;
    this.name = name;
  }

  public City(String name, Integer countryId) {
    this.name = name;
    this.country = new Country();
    this.country.setId(countryId);
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

  public Country getCountry() {
    return country;
  }

  public void setCountry(Country country) {
    this.country = country;
  }
}
