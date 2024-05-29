package com.example.BuonAppetito.services;

import com.example.BuonAppetito.entities.Comune;
import com.example.BuonAppetito.exceptions.ComuneNotFoundException;
import com.example.BuonAppetito.repositories.ComuneRepository;
import com.example.BuonAppetito.requests.ComuneRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ComuneService {

    @Autowired
    private ComuneRepository comuneRepository;

    public Comune getComuneById (Long id) throws ComuneNotFoundException {
        Optional<Comune> comuneOptional = comuneRepository.findById(id);
        if (comuneOptional.isEmpty()) throw new ComuneNotFoundException();
        return comuneOptional.get();
    }

    public List<Comune> getAll () {
        return comuneRepository.findAll();
    }

    public Comune create (ComuneRequest request) {
        Comune comune = Comune.builder()
                .nome(request.getNome())
                .regione(request.getRegione())
                .insertTime(LocalDateTime.now())
                .build();
        return comuneRepository.saveAndFlush(comune);
    }

    public Comune update (Long id, Comune newComune) throws ComuneNotFoundException{
        getComuneById(id);
        Comune comune = Comune
                .builder()
                .id(id)
                .nome(newComune.getNome())
                .regione(newComune.getRegione())
                .insertTime(getComuneById(id).getInsertTime())
                .lastUpdate(LocalDateTime.now())
                .build();
        return comuneRepository.saveAndFlush(comune);
    }

    public void delete (Long id) throws ComuneNotFoundException {
        getComuneById(id);
        comuneRepository.deleteById(id);
    }


}
