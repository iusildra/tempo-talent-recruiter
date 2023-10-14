package com.tempotalent.api.models;

import jakarta.persistence.*;

@Entity
@Table(name = "country")
public record Country(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) int id,
    @Column(length = 50) String name) {
  public Country {
    if (name.length() > 50) {
      throw new IllegalArgumentException("name too long");
    }
  }
}
