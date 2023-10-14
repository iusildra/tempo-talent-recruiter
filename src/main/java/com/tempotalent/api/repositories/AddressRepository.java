package com.tempotalent.api.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tempotalent.api.models.Address;

public interface AddressRepository extends JpaRepository<Address, UUID> {}