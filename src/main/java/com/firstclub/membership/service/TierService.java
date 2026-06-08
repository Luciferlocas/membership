package com.firstclub.membership.service;

import com.firstclub.membership.entity.MembershipTier;
import com.firstclub.membership.enums.TierType;
import com.firstclub.membership.repository.MembershipTierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TierService {

    private final MembershipTierRepository membershipTierRepository;

    public MembershipTier getTier(TierType tierType) {
        return membershipTierRepository.findByTierType(tierType)
                .orElseThrow(() -> new RuntimeException("Tier not found"));
    }
}