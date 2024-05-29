package com.example.BuonAppetito.requests;

import com.example.BuonAppetito.entities.Posto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RistoranteRequest {

    private String nome;
    private String indirizzo;
    private Long comune;
    private Integer nPosti;
    private Integer postiDisponibili = nPosti;
    private Long proprietario;
    private LocalTime orarioApertura;
    private LocalTime orarioChiusura;
    private Long menù;
    private LocalDateTime insertTime;
    private LocalDateTime lastUpdate;
}
