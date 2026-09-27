package com.stocksurge.entity;
import jakarta.persistence.*;
import lombok.*;


@Entity @Table(name="inventory")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Inventory {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false) private String productName;
    @Column(nullable=false) private String category;
    @Column(length=2000) private String description;
    @Column(nullable=false) private Integer quantity;
    private String conditionStatus;
    private Double pricePerUnit;
    private String location;
    private String imageUrl;
    @Enumerated(EnumType.STRING) private InventoryStatus status;
    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    private User seller;
}