package com.tempotalent.api.subscription;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscrRepository extends JpaRepository<Subscr, UUID> {
}
