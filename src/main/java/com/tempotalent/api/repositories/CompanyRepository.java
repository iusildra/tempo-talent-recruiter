package com.tempotalent.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tempotalent.api.models.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> { }
