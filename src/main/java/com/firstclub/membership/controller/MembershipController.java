package com.firstclub.membership.controller;

import com.firstclub.membership.entity.MembershipPlan;
import com.firstclub.membership.service.MembershipService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/memberships")
@RequiredArgsConstructor
public class MembershipController {

    private final MembershipService membershipService;

    @GetMapping("/plans")
    public List<MembershipPlan> getPlans() {
        return membershipService.getAllPlans();
    }
}