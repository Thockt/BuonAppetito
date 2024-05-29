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
public class Prenotazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Utente utente;
    @ManyToOne
    private Ristorante ristorante;
    @Column(nullable = false)
    private LocalDateTime dataPrenotazione;
    @Column(nullable = false)
    @Check(constraints = "postiPrenotati > 0")
    private Integer postiPrenotati;
    @Column(nullable = false)
    private LocalDateTime insertTime;
    @Column
    private LocalDateTime lastUpdate;

}
