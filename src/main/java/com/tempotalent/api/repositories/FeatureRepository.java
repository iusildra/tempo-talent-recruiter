package com.tempotalent.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tempotalent.api.models.Feature;

public interface FeatureRepository extends JpaRepository<Feature, Integer> {}
