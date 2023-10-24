package com.tempotalent.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tempotalent.api.models.City;

public interface CityRepository extends JpaRepository<City, Integer> {}
