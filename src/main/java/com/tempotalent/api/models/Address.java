package com.tempotalent.api.models;

import java.util.UUID;
import jakarta.persistence.*;

@Entity
@Table(name = "address")
public record Address(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) UUID id,
    int num,
    String street,
    String complement,
    @Column(name = "zip_code") short zipCode,
    int city) {
}