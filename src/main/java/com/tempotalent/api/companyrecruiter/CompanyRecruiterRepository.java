package com.tempotalent.api.companyrecruiter;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRecruiterRepository extends JpaRepository<CompanyRecruiter, UUID>{
  
}
