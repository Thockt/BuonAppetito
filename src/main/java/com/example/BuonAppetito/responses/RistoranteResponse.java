package com.example.BuonAppetito.responses;

import com.example.BuonAppetito.entities.Posto;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RistoranteResponse {

    private Long id;
    private String nome;
    private String indirizzo;
    private Long comune;
    private List<Posto> posti;
    private Integer nPosti;
    private Integer postiDisponibili = nPosti;
    private Long proprietario;
    private LocalTime orarioApertura;
    private LocalTime orarioChiusura;
    private Long menù;
    private LocalDateTime insertTime;
    private LocalDateTime lastUpdate;
}
