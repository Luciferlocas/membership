package com.firstclub.membership.repository;

import com.firstclub.membership.entity.MembershipPlan;
import com.firstclub.membership.enums.PlanType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MembershipPlanRepository extends JpaRepository<MembershipPlan, UUID> {

    Optional<MembershipPlan> findByPlanType(
            PlanType planType
    );
}