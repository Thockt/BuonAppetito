package com.example.BuonAppetito.responses;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContoResponse {

    private Long id;
    private Long utente;
    private Long ristorante;
    private Long prenotazione;
    private Long menù;
    private Double totale;
    private Boolean isPagato;
}
