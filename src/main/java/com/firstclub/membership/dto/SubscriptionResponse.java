package com.firstclub.membership.dto;

import com.firstclub.membership.enums.PlanType;
import com.firstclub.membership.enums.SubscriptionStatus;
import com.firstclub.membership.enums.TierType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class SubscriptionResponse {

    private UUID subscriptionId;
    private UUID userId;

    private PlanType planType;
    private TierType tierType;

    private List<String> benefits;

    private SubscriptionStatus status;

    private LocalDate startDate;
    private LocalDate expiryDate;
}