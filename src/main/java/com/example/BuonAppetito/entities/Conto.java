package com.example.BuonAppetito.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Check;

import java.time.LocalDateTime;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Conto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Utente utente;
    @ManyToOne
    private Ristorante ristorante;
    @ManyToOne
    private Prenotazione prenotazione;
    @ManyToOne
    private Menù menù;
    @Column(nullable = false)
    @Check(constraints = "totale > 0")
    private Double totale;
    @Column (nullable = false)
    private Boolean isPagato;
    @Column(nullable = false)
    private LocalDateTime insertTime;
    @Column
    private LocalDateTime lastUpdate;

}
