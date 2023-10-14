package com.tempotalent.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tempotalent.api.models.Country;

public interface CountryRepository extends JpaRepository<Country, Integer> {}
