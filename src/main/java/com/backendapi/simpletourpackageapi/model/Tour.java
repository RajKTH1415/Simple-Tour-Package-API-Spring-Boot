package com.backendapi.simpletourpackageapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
@Entity
@Table(name = "tours")
public class Tour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 160)
    private String title;

    @Column(nullable = false, length = 500)
    private String description;

    @Column(nullable = false, length = 512)
    private String imageUrl;

    @Column(nullable = false)
    private Integer durationDays;

    @Column(nullable = false)
    private Integer durationNights;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal actualPrice;

    @Column(nullable = false)
    private Integer discountPercent;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal discountedPrice;

    @Column(nullable = false, length = 3)
    private String currency;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @PrePersist
    public void prePersist() {
        if (createdAt == null) createdAt = Instant.now();
    }
}
