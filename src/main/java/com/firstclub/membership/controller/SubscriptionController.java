package com.firstclub.membership.controller;

import com.firstclub.membership.dto.SubscribeRequest;
import com.firstclub.membership.dto.SubscriptionResponse;
import com.firstclub.membership.service.SubscriptionService;
import com.firstclub.membership.dto.UpdateTierRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/api/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @PostMapping
    public SubscriptionResponse subscribe(
            @Valid @RequestBody SubscribeRequest request
    ) {
        return subscriptionService.subscribe(request);
    }

    @GetMapping("/current/{userId}")
    public SubscriptionResponse getCurrentSubscription(
            @PathVariable UUID userId
    ) {
        return subscriptionService.getCurrentSubscription(userId);
    }

    @PutMapping("/tier")
    public SubscriptionResponse updateTier(
            @Valid @RequestBody
            UpdateTierRequest request
    ) {
        return subscriptionService.updateTier(
                request
        );
    }

    @PutMapping("/cancel/{subscriptionId}")
    public SubscriptionResponse cancelSubscription(
            @PathVariable UUID subscriptionId
    ) {
        return subscriptionService
                .cancelSubscription(
                        subscriptionId
                );
    }
}