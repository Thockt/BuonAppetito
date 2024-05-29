package com.example.BuonAppetito.services;

import com.example.BuonAppetito.entities.Prenotazione;
import com.example.BuonAppetito.exceptions.PrenotazioneNotFoundException;
import com.example.BuonAppetito.exceptions.RistoranteNotFoundException;
import com.example.BuonAppetito.exceptions.UtenteNotFoundException;
import com.example.BuonAppetito.repositories.PrenotazioneRepository;
import com.example.BuonAppetito.requests.PrenotazioneRequest;
import com.example.BuonAppetito.responses.PrenotazioneResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;
    @Autowired
    private UtenteService utenteService;
    @Autowired
    private RistoranteService ristoranteService;


    public Prenotazione getPrenotazioneByIdStandard (Long id) throws PrenotazioneNotFoundException {
        Optional<Prenotazione> prenotazioneOptional = prenotazioneRepository.findById(id);
        if (prenotazioneOptional.isEmpty()) throw new PrenotazioneNotFoundException();
        return prenotazioneOptional.get();
    }

    public PrenotazioneResponse getPrenotazioneById (Long id) throws PrenotazioneNotFoundException {
        Optional<Prenotazione> prenotazioneOptional = prenotazioneRepository.findById(id);
        if (prenotazioneOptional.isEmpty()) throw new PrenotazioneNotFoundException();
        return convertFromEntity(prenotazioneOptional.get());
    }

    public List<Prenotazione> getAll () {
        return prenotazioneRepository.findAll();
    }

    public PrenotazioneResponse create (PrenotazioneRequest request) throws UtenteNotFoundException, RistoranteNotFoundException {
        Prenotazione prenotazione = convertFromDTO(request);
        prenotazione.setInsertTime(LocalDateTime.now());
        prenotazioneRepository.saveAndFlush(prenotazione);
        return convertFromEntity(prenotazione);
    }

    public PrenotazioneResponse update (Long id, Prenotazione newPrenotazione) throws PrenotazioneNotFoundException {
        getPrenotazioneById(id);
        Prenotazione prenotazione = Prenotazione.builder()
                .id(id)
                .utente(newPrenotazione.getUtente())
                .ristorante(newPrenotazione.getRistorante())
                .dataPrenotazione(newPrenotazione.getDataPrenotazione())
                .postiPrenotati(newPrenotazione.getPostiPrenotati())
                .insertTime(prenotazioneRepository.getReferenceById(id).getInsertTime())
                .lastUpdate(LocalDateTime.now())
                .build();
        return convertFromEntity(prenotazioneRepository.saveAndFlush(prenotazione));
    }

    public void deleteById (Long id) throws PrenotazioneNotFoundException {
        getPrenotazioneById(id);
        prenotazioneRepository.deleteById(id);
    }

    private Prenotazione convertFromDTO (PrenotazioneRequest request) throws UtenteNotFoundException, RistoranteNotFoundException {
        return Prenotazione.builder()
                .utente(utenteService.getUtenteById(request.getUtente()))
                .ristorante(ristoranteService.getRistoranteByIdStandard(request.getRistorante()))
                .dataPrenotazione(request.getDataPrenotazione())
                .postiPrenotati(request.getPostiPrenotati())
                .build();
    }

    private PrenotazioneResponse convertFromEntity (Prenotazione prenotazione) {
        return PrenotazioneResponse.builder()
                .id(prenotazione.getId())
                .utente(prenotazione.getUtente().getId())
                .ristorante(prenotazione.getRistorante().getId())
                .dataPrenotazione(prenotazione.getDataPrenotazione())
                .postiPrenotati(prenotazione.getPostiPrenotati())
                .build();
    }
}
