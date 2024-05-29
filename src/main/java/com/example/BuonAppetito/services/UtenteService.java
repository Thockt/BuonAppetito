package com.example.BuonAppetito.services;

import com.example.BuonAppetito.entities.Utente;
import com.example.BuonAppetito.exceptions.UtenteNotFoundException;
import com.example.BuonAppetito.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;

    public Utente getUtenteById (Long id) throws UtenteNotFoundException {
        Optional<Utente> utenteOptional = utenteRepository.findById(id);
        if (utenteOptional.isEmpty()) throw new UtenteNotFoundException();
        return utenteOptional.get();
    }

    public List<Utente> getAll() {
        return utenteRepository.findAll();
    }

    public Utente create (Utente utente) {
        utente.setInsertTime(LocalDateTime.now());
        return utenteRepository.saveAndFlush(utente);
    }

    public Utente update (Long id, Utente newUtente) throws UtenteNotFoundException {
        getUtenteById(id);
        Utente utente = Utente.builder()
                .id(id)
                .nome(newUtente.getNome())
                .cognome(newUtente.getCognome())
                .dataNascita(newUtente.getDataNascita())
                .città(newUtente.getCittà())
                .indirizzo(newUtente.getIndirizzo())
                .telefono(newUtente.getTelefono())
                .email(newUtente.getEmail())
                .password(newUtente.getPassword())
                .role(newUtente.getRole())
                .insertTime(getUtenteById(id).getInsertTime())
                .lastUpdate(LocalDateTime.now())
                .build();
        return utenteRepository.saveAndFlush(utente);
    }

    public void deleteById (Long id) throws UtenteNotFoundException {
        getUtenteById(id);
        utenteRepository.deleteById(id);
    }

}
