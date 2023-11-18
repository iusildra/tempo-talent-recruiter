package com.tempotalent.api.tier;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TierRepository extends JpaRepository<Tier, Integer> { 
}
