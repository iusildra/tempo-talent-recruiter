package com.tempotalent.api.tierfeature;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TierFeatureRepository extends JpaRepository<TierFeature, TierFeatureKey> {
}
