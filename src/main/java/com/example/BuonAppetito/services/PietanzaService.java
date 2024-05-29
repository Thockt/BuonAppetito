package com.example.BuonAppetito.services;

import com.example.BuonAppetito.entities.Pietanza;
import com.example.BuonAppetito.exceptions.PietanzaNotFoundException;
import com.example.BuonAppetito.repositories.PietanzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PietanzaService {

    @Autowired
    private PietanzaRepository pietanzaRepository;

    public Pietanza getPietanzaById (Long id) throws PietanzaNotFoundException {
        Optional<Pietanza> optionalPietanza = pietanzaRepository.findById(id);
        if (optionalPietanza.isEmpty()) throw new PietanzaNotFoundException();
        return optionalPietanza.get();
    }

    public List<Pietanza> getAll () {
        return pietanzaRepository.findAll();
    }

    public Pietanza create (Pietanza pietanza) {
        pietanza.setInsertTime(LocalDateTime.now());
        return pietanzaRepository.saveAndFlush(pietanza);
    }

    public Pietanza update (Long id, Pietanza newPietanza) throws PietanzaNotFoundException {
        getPietanzaById(id);
        Pietanza pietanza = Pietanza.builder()
                .id(id)
                .nome(newPietanza.getNome())
                .prezzo(newPietanza.getPrezzo())
                .insertTime(getPietanzaById(id).getInsertTime())
                .lastUpdate(LocalDateTime.now())
                .build();
        return pietanzaRepository.saveAndFlush(pietanza);
    }

    public void deleteById (Long id) throws PietanzaNotFoundException {
        getPietanzaById(id);
        pietanzaRepository.deleteById(id);
    }

}
