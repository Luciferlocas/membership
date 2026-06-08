package com.firstclub.membership.dto;

import com.firstclub.membership.enums.TierType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class UpdateTierRequest {

    @NotNull
    private UUID subscriptionId;

    @NotNull
    private TierType tierType;
}