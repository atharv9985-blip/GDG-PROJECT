package com.stocksurge.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "requirements")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Requirement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String buyerName;

    @NotBlank
    @Column(nullable = false)
    private String category;

    @Column(length = 2000)
    private String description;

    @Min(1)
    @Column(nullable = false)
    private Integer quantityRequired;

    private Double minBudgetPerUnit;

    private Double maxBudgetPerUnit;

    private String location;

    @ManyToOne
    @JoinColumn(name = "buyer_id", nullable = false)
    private User buyer;
}