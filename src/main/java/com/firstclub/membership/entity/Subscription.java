package com.firstclub.membership.entity;

import com.firstclub.membership.enums.SubscriptionStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "subscriptions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Subscription {

    @Id
    @GeneratedValue
    private UUID id;

    @Version
    private Long version;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "plan_id")
    private MembershipPlan plan;

    @ManyToOne
    @JoinColumn(name = "tier_id")
    private MembershipTier tier;

    private LocalDate startDate;

    private LocalDate expiryDate;

    @Enumerated(EnumType.STRING)
    private SubscriptionStatus status;
}