package com.tempotalent.api.recruiter;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

import com.tempotalent.api.companyrecruiter.CompanyRecruiter;
import com.tempotalent.api.subscription.Subscription;

@Entity
@Table(name = "recruiter")
public class Recruiter {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;
  private String email;
  private String phone;

  private String password;

  @OneToOne(mappedBy = "recruiter")
  private Subscription subscription;

  @OneToMany(mappedBy = "recruiter")
  private List<CompanyRecruiter> companies;

  public Recruiter() {
    super();
  }

  public Recruiter(UUID id, String firstName, String lastName, String phone, String email, String password) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phone = phone;
    this.password = password;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
