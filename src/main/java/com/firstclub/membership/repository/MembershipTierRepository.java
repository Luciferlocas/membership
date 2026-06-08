package com.firstclub.membership.repository;

import com.firstclub.membership.entity.MembershipTier;
import com.firstclub.membership.enums.TierType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MembershipTierRepository extends JpaRepository<MembershipTier, UUID> {

    Optional<MembershipTier> findByTierType(TierType tierType);
}