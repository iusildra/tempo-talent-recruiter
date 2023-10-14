package com.tempotalent.api.models;

import jakarta.persistence.*;

@Entity
@Table(name = "company")
public class Company {
  @Id
  public final Integer siret;
  private String name;
  private String address;

  public Company(Integer siret, String name, String address) {
    this.siret = siret;
    this.name = name;
    this.address = address;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }
}