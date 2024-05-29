package com.example.BuonAppetito.responses;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrenotazioneResponse {

    private Long id;
    private Long utente;
    private Long ristorante;
    private LocalDateTime dataPrenotazione;
    private Integer postiPrenotati;
}
