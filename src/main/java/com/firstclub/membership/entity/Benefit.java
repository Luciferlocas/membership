package com.firstclub.membership.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "benefits")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Benefit {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    private String value;
}