package com.firstclub.membership.config;

import com.firstclub.membership.entity.MembershipPlan;
import com.firstclub.membership.entity.MembershipTier;
import com.firstclub.membership.enums.PlanType;
import com.firstclub.membership.enums.TierType;
import com.firstclub.membership.repository.MembershipPlanRepository;
import com.firstclub.membership.repository.MembershipTierRepository;
import com.firstclub.membership.entity.Benefit;
import com.firstclub.membership.repository.BenefitRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final MembershipPlanRepository planRepository;
    private final MembershipTierRepository tierRepository;
    private final BenefitRepository benefitRepository;

    @Override
    public void run(String... args) {

        if (planRepository.findAll().isEmpty()) {

            planRepository.save(
                    MembershipPlan.builder()
                            .planType(PlanType.MONTHLY)
                            .price(BigDecimal.valueOf(99))
                            .durationInDays(30)
                            .build()
            );

            planRepository.save(
                    MembershipPlan.builder()
                            .planType(PlanType.QUARTERLY)
                            .price(BigDecimal.valueOf(249))
                            .durationInDays(90)
                            .build()
            );

            planRepository.save(
                    MembershipPlan.builder()
                            .planType(PlanType.YEARLY)
                            .price(BigDecimal.valueOf(899))
                            .durationInDays(365)
                            .build()
            );
        }

        if (benefitRepository.count() == 0) {

            benefitRepository.save(
                    Benefit.builder()
                            .name("FREE_DELIVERY")
                            .value("Unlimited free delivery")
                            .build()
            );

            benefitRepository.save(
                    Benefit.builder()
                            .name("DISCOUNT_10")
                            .value("10% discount on all orders")
                            .build()
            );

            benefitRepository.save(
                    Benefit.builder()
                            .name("DISCOUNT_15")
                            .value("15% discount on all orders")
                            .build()
            );

            benefitRepository.save(
                    Benefit.builder()
                            .name("PRIORITY_SUPPORT")
                            .value("Priority customer support")
                            .build()
            );
        }

        Benefit freeDelivery =
                benefitRepository.findAll().stream()
                        .filter(b -> b.getName().equals("FREE_DELIVERY"))
                        .findFirst()
                        .orElseThrow();

        Benefit discount10 =
                benefitRepository.findAll().stream()
                        .filter(b -> b.getName().equals("DISCOUNT_10"))
                        .findFirst()
                        .orElseThrow();

        Benefit discount15 =
                benefitRepository.findAll().stream()
                        .filter(b -> b.getName().equals("DISCOUNT_15"))
                        .findFirst()
                        .orElseThrow();

        Benefit prioritySupport =
                benefitRepository.findAll().stream()
                        .filter(b -> b.getName().equals("PRIORITY_SUPPORT"))
                        .findFirst()
                        .orElseThrow();

        if (tierRepository.count() == 0) {

            tierRepository.save(
                    MembershipTier.builder()
                            .tierType(TierType.SILVER)
                            .minOrders(0)
                            .minOrderValue(BigDecimal.ZERO)
                            .benefits(Set.of(
                                    freeDelivery
                            ))
                            .build()
            );

            tierRepository.save(
                    MembershipTier.builder()
                            .tierType(TierType.GOLD)
                            .minOrders(10)
                            .minOrderValue(BigDecimal.valueOf(5000))
                            .benefits(Set.of(
                                    freeDelivery,
                                    discount10
                            ))
                            .build()
            );

            tierRepository.save(
                    MembershipTier.builder()
                            .tierType(TierType.PLATINUM)
                            .minOrders(25)
                            .minOrderValue(BigDecimal.valueOf(15000))
                            .benefits(Set.of(
                                    freeDelivery,
                                    discount15,
                                    prioritySupport
                            ))
                            .build()
            );
        }
    }
}