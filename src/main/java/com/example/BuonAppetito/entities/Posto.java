package com.example.BuonAppetito.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Posto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Integer nPosto;
    @Column(nullable = false)
    private LocalDateTime insertTime;
    @Column
    private LocalDateTime lastUpdate;

}
