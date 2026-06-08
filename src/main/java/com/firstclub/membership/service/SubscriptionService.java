package com.firstclub.membership.service;

import com.firstclub.membership.dto.SubscribeRequest;
import com.firstclub.membership.dto.SubscriptionResponse;
import com.firstclub.membership.dto.UpdateTierRequest;
import com.firstclub.membership.entity.*;
import com.firstclub.membership.enums.SubscriptionStatus;
import com.firstclub.membership.exception.BadRequestException;
import com.firstclub.membership.exception.ResourceNotFoundException;
import com.firstclub.membership.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;
    private final MembershipPlanRepository membershipPlanRepository;
    private final MembershipTierRepository membershipTierRepository;

    @Transactional
    public SubscriptionResponse subscribe(
            SubscribeRequest request
    ) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        subscriptionRepository
                .findByUserAndStatus(
                        user,
                        SubscriptionStatus.ACTIVE
                )
                .ifPresent(subscription -> {
                    throw new BadRequestException(
                            "User already has an active subscription"
                    );
                });

        MembershipPlan plan = membershipPlanRepository
                .findByPlanType(request.getPlanType())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Plan not found"));

        MembershipTier tier = membershipTierRepository
                .findByTierType(request.getTierType())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Tier not found"));

        Subscription subscription = Subscription.builder()
                .user(user)
                .plan(plan)
                .tier(tier)
                .startDate(LocalDate.now())
                .expiryDate(
                        LocalDate.now()
                                .plusDays(plan.getDurationInDays())
                )
                .status(SubscriptionStatus.ACTIVE)
                .build();

        return mapToResponse(
                subscriptionRepository.save(subscription)
        );
    }

    public SubscriptionResponse getCurrentSubscription(
            UUID userId
    ) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        return mapToResponse(subscriptionRepository
                .findByUserAndStatus(
                        user,
                        SubscriptionStatus.ACTIVE
                )
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No active subscription found"
                        ))
        );
    }

    @Transactional
    public SubscriptionResponse updateTier(
            UpdateTierRequest request
    ) {

        Subscription subscription =
                subscriptionRepository.findById(
                        request.getSubscriptionId()
                ).orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Subscription not found"
                        ));

        MembershipTier tier =
                membershipTierRepository.findByTierType(
                        request.getTierType()
                ).orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Tier not found"
                        ));

        subscription.setTier(tier);

        return mapToResponse(
                subscriptionRepository.save(subscription)
        );
    }

    @Transactional
    public SubscriptionResponse cancelSubscription(
            UUID subscriptionId
    ) {

        Subscription subscription =
                subscriptionRepository.findById(
                        subscriptionId
                ).orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Subscription not found"
                        ));

        subscription.setStatus(
                SubscriptionStatus.CANCELLED
        );

        return mapToResponse(
                subscriptionRepository.save(subscription)
        );
    }

    private SubscriptionResponse mapToResponse(
            Subscription subscription
    ) {

        return SubscriptionResponse.builder()
                .subscriptionId(
                        subscription.getId()
                )
                .userId(
                        subscription.getUser().getId()
                )
                .planType(
                        subscription.getPlan().getPlanType()
                )
                .tierType(
                        subscription.getTier().getTierType()
                )
                .benefits(
                        subscription.getTier()
                                .getBenefits()
                                .stream()
                                .map(Benefit::getName)
                                .sorted()
                                .collect(Collectors.toList())
                )
                .startDate(
                        subscription.getStartDate()
                )
                .expiryDate(
                        subscription.getExpiryDate()
                )
                .status(
                        subscription.getStatus()
                )
                .build();
    }
}