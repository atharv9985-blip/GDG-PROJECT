package com.stocksurge.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name="matches")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Match {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional=false) private Inventory inventory;
    @ManyToOne(optional=false) private Requirement requirement;
    private Integer matchScore;
    @Column(length=2000) private String reason;
}