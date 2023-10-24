package com.tempotalent.api.models;

import jakarta.persistence.*;

@Entity
@Table(name = "company")
public class Company {
  @Id
  private Integer siret;
  private String name;
  private String address;

  public Company() {
  }

  public Company(Integer siret, String name, String address) {
    this.siret = siret;
    this.name = name;
    this.address = address;
  }

  public Integer getSiret() {
    return siret;
  }

  public void setSiret(Integer siret) {
    this.siret = siret;
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
