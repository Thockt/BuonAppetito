package com.example.BuonAppetito.requests;

import com.example.BuonAppetito.entities.Comune;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegistrationRequest {

    private String nome;
    private String cognome;
    private LocalDateTime dataNascita;
    private Comune città;
    private String indirizzo;
    private String telefono;
    private String email;
    private String password;
}
