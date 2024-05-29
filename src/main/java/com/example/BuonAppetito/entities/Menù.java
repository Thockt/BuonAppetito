package com.example.BuonAppetito.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Check;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Menù {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany
    private List<Pietanza> cibo;
    @Column(nullable = false)
    @Check(constraints = "prezzo > 0")
    private Double prezzo;
    @Column(nullable = false)
    private LocalDateTime insertTime;
    @Column
    private LocalDateTime lastUpdate;


}
