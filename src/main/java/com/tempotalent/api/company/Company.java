package com.tempotalent.api.company;

import java.util.List;
import java.util.UUID;

import com.tempotalent.api.address.Address;
import com.tempotalent.api.companyrecruiter.CompanyRecruiter;

import jakarta.persistence.*;

@Entity
@Table(name = "company")
public class Company {
  @Id
  private Integer siret;
  private String name;

  @Column(name = "address_id")
  private UUID addressId;

  @ManyToOne
  @JoinColumn(name = "address_id", insertable = false, updatable = false)
  private Address address;

  @OneToMany(mappedBy = "company")
  private List<CompanyRecruiter> recruiters;

  public Company() {
  }

  public Company(Integer siret, String name, UUID addressId) {
    this.siret = siret;
    this.name = name;
    this.addressId = addressId;
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

  public UUID getAddressId() {
    return addressId;
  }

  public void setAddressId(UUID addressId) {
    this.addressId = addressId;
  }
}
