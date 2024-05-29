package com.example.BuonAppetito.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContoRequest {

    private Long utente;
    private Long ristorante;
    private Long prenotazione;
    private Long menù;
}
