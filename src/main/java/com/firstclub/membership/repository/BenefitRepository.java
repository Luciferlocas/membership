package com.firstclub.membership.repository;

import com.firstclub.membership.entity.Benefit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BenefitRepository extends JpaRepository<Benefit, UUID> {
}