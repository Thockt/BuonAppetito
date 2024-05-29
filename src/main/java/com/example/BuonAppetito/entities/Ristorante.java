package com.example.BuonAppetito.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.DialectOverride;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ristorante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String indirizzo;
    @ManyToOne
    private Comune comune;
    @ManyToMany
    private List<Posto> posti;
    @Column(nullable = false)
    @Check(constraints= "nPosti > 0 AND nPosti <= posti.size()")
    private  Integer nPosti;
    @Column(nullable = false)
    private Integer postiDisponibili = nPosti;
    @ManyToOne
    private Utente proprietario;
    @Column(nullable = false)
    private LocalTime orarioApertura;
    @Check(constraints = "orarioApertura !AFTER orarioChiusura")
    @Column(nullable = false)
    @Check(constraints = "orarioChiusura AFTER orarioApertura")
    private LocalTime orarioChiusura;
    @ManyToOne
    private Menù menù;
    @Column(nullable = false)
    private LocalDateTime insertTime;
    @Column
    private LocalDateTime lastUpdate;

}
