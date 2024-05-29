package com.example.BuonAppetito.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrenotazioneRequest {

    private Long utente;
    private Long ristorante;
    private LocalDateTime dataPrenotazione;
    private Integer postiPrenotati;
}
