package com.example.BuonAppetito.services;

import com.example.BuonAppetito.entities.Ristorante;
import com.example.BuonAppetito.exceptions.ComuneNotFoundException;
import com.example.BuonAppetito.exceptions.MenùNotFoundException;
import com.example.BuonAppetito.exceptions.RistoranteNotFoundException;
import com.example.BuonAppetito.exceptions.UtenteNotFoundException;
import com.example.BuonAppetito.repositories.RistoranteRepository;
import com.example.BuonAppetito.requests.RistoranteRequest;
import com.example.BuonAppetito.responses.RistoranteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RistoranteService {

    @Autowired
    private RistoranteRepository ristoranteRepository;
    @Autowired
    private ComuneService comuneService;
    @Autowired
    private UtenteService utenteService;
    @Autowired
    private MenùService menùService;

    public Ristorante getRistoranteByIdStandard (Long id) throws RistoranteNotFoundException {
        Optional<Ristorante> ristoranteOptional = ristoranteRepository.findById(id);
        if (ristoranteOptional.isEmpty()) throw new RistoranteNotFoundException();
        return ristoranteOptional.get();
    }

    public RistoranteResponse getRistoranteById (Long id) throws RistoranteNotFoundException {
        Optional<Ristorante> ristoranteOptional = ristoranteRepository.findById(id);
        if (ristoranteOptional.isEmpty()) throw new RistoranteNotFoundException();
        return convertFromEntity(ristoranteOptional.get());
    }

    public List<Ristorante> getAll () {
        return ristoranteRepository.findAll();
    }

    public RistoranteResponse create (RistoranteRequest request) throws MenùNotFoundException, UtenteNotFoundException, ComuneNotFoundException {
        Ristorante ristorante = convertFromDTO(request);
        ristorante.setInsertTime(LocalDateTime.now());
        return convertFromEntity(ristoranteRepository.saveAndFlush(ristorante));
    }

    public RistoranteResponse update (Long id, Ristorante newRistorante) throws RistoranteNotFoundException {
        getRistoranteById(id);
        Ristorante ristorante = Ristorante.builder()
                .id(id)
                .nome(newRistorante.getNome())
                .indirizzo(newRistorante.getIndirizzo())
                .comune(newRistorante.getComune())
                .posti(newRistorante.getPosti())
                .nPosti(newRistorante.getNPosti())
                .postiDisponibili(newRistorante.getPostiDisponibili())
                .proprietario(newRistorante.getProprietario())
                .orarioApertura(newRistorante.getOrarioApertura())
                .orarioChiusura(newRistorante.getOrarioChiusura())
                .menù(newRistorante.getMenù())
                .insertTime(getRistoranteByIdStandard(id).getInsertTime())
                .lastUpdate(LocalDateTime.now())
                .build();
        return convertFromEntity(ristoranteRepository.saveAndFlush(ristorante));
    }

    public void deleteById (Long id) throws RistoranteNotFoundException {
        getRistoranteById(id);
        ristoranteRepository.deleteById(id);
    }

    private Ristorante convertFromDTO (RistoranteRequest request) throws ComuneNotFoundException, UtenteNotFoundException, MenùNotFoundException {
        return Ristorante.builder()
                .nome(request.getNome())
                .indirizzo(request.getIndirizzo())
                .comune(comuneService.getComuneById(request.getComune()))
                .nPosti(request.getNPosti())
                .postiDisponibili(request.getPostiDisponibili())
                .proprietario(utenteService.getUtenteById(request.getProprietario()))
                .orarioApertura(request.getOrarioApertura())
                .orarioChiusura(request.getOrarioChiusura())
                .menù(menùService.getMenùById(request.getMenù()))
                .build();
    }

    private RistoranteResponse convertFromEntity (Ristorante ristorante) {
        return RistoranteResponse.builder()
                .nome(ristorante.getNome())
                .indirizzo(ristorante.getIndirizzo())
                .comune(ristorante.getComune().getId())
                .posti(ristorante.getPosti())
                .nPosti(ristorante.getNPosti())
                .postiDisponibili(ristorante.getPostiDisponibili())
                .proprietario(ristorante.getProprietario().getId())
                .orarioApertura(ristorante.getOrarioApertura())
                .orarioChiusura(ristorante.getOrarioChiusura())
                .menù(ristorante.getMenù().getId())
                .insertTime(ristorante.getInsertTime())
                .lastUpdate(ristorante.getLastUpdate())
                .build();
    }
}
