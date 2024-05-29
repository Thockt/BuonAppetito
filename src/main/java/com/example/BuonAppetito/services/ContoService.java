package com.example.BuonAppetito.services;

import com.example.BuonAppetito.entities.Conto;
import com.example.BuonAppetito.exceptions.*;
import com.example.BuonAppetito.repositories.ContoRepository;
import com.example.BuonAppetito.requests.ContoRequest;
import com.example.BuonAppetito.responses.ContoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ContoService {

    @Autowired
    private ContoRepository contoRepository;
    @Autowired
    private UtenteService utenteService;
    @Autowired
    private RistoranteService ristoranteService;
    @Autowired
    private PrenotazioneService prenotazioneService;
    @Autowired
    private MenùService menùService;

    public ContoResponse getContoById (Long id) throws ContoNotFoundException {
        Optional<Conto> contoOptional = contoRepository.findById(id);
        if (contoOptional.isEmpty()) throw new ContoNotFoundException();
        return convertFromEntity(contoOptional.get());
    }

    public List<Conto> getAll () {
        return contoRepository.findAll();
    }

    public ContoResponse create (ContoRequest request) throws PrenotazioneNotFoundException, UtenteNotFoundException, RistoranteNotFoundException, MenùNotFoundException {
        Conto conto = convertFromDTO(request);
        conto.setTotale(menùService.getMenùById(request.getMenù()).getPrezzo());
        conto.setInsertTime(LocalDateTime.now());
        contoRepository.saveAndFlush(conto);
        return convertFromEntity(conto);
    }

    public ContoResponse update (Long id, Conto newConto) throws ContoNotFoundException{
        getContoById(id);
        Conto conto = Conto.builder()
                .id(id)
                .utente(newConto.getUtente())
                .ristorante(newConto.getRistorante())
                .prenotazione(newConto.getPrenotazione())
                .totale(newConto.getTotale())
                .isPagato(newConto.getIsPagato())
                .insertTime(contoRepository.getReferenceById(id).getInsertTime())
                .lastUpdate(LocalDateTime.now())
                .build();
        contoRepository.saveAndFlush(conto);
        return convertFromEntity(conto);
    }

    public void delete (Long id) throws ContoNotFoundException {
        getContoById(id);
        contoRepository.deleteById(id);
    }

    private Conto convertFromDTO (ContoRequest request) throws UtenteNotFoundException, RistoranteNotFoundException, PrenotazioneNotFoundException, MenùNotFoundException {
        Conto conto = Conto.builder()
                .utente(utenteService.getUtenteById(request.getUtente()))
                .ristorante(ristoranteService.getRistoranteByIdStandard(request.getRistorante()))
                .prenotazione(prenotazioneService.getPrenotazioneByIdStandard(request.getPrenotazione()))
                .menù(menùService.getMenùById(request.getMenù()))
                .build();
        conto.setIsPagato(false);
        return conto;
    }

    private ContoResponse convertFromEntity (Conto conto) {
        return ContoResponse.builder()
                .id(conto.getId())
                .utente(conto.getUtente().getId())
                .ristorante(conto.getRistorante().getId())
                .prenotazione(conto.getPrenotazione().getId())
                .totale(conto.getTotale())
                .isPagato(conto.getIsPagato())
                .build();
    }
}
