package com.firstclub.membership.dto;

import com.firstclub.membership.enums.PlanType;
import com.firstclub.membership.enums.TierType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class SubscribeRequest {

    @NotNull
    private UUID userId;

    @NotNull
    private PlanType planType;

    @NotNull
    private TierType tierType;
}