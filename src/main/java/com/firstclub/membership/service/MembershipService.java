package com.firstclub.membership.service;

import com.firstclub.membership.entity.MembershipPlan;
import com.firstclub.membership.repository.MembershipPlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MembershipService {

    private final MembershipPlanRepository membershipPlanRepository;

    public List<MembershipPlan> getAllPlans() {
        return membershipPlanRepository.findAll();
    }
}