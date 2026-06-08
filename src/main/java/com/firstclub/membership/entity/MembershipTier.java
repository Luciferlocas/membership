package com.firstclub.membership.entity;

import com.firstclub.membership.enums.TierType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.Set;

@Entity
@Table(name = "membership_tiers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MembershipTier {

    @Id
    @GeneratedValue
    private UUID id;

    @Enumerated(EnumType.STRING)
    private TierType tierType;

    private Integer minOrders;

    private BigDecimal minOrderValue;

    @ManyToMany
    @JoinTable(
            name = "tier_benefits",
            joinColumns = @JoinColumn(name = "tier_id"),
            inverseJoinColumns = @JoinColumn(name = "benefit_id")
    )
    private Set<Benefit> benefits;
}