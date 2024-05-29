package com.example.BuonAppetito.controllers;

import com.example.BuonAppetito.entities.Prenotazione;
import com.example.BuonAppetito.exceptions.PrenotazioneNotFoundException;
import com.example.BuonAppetito.exceptions.RistoranteNotFoundException;
import com.example.BuonAppetito.exceptions.UtenteNotFoundException;
import com.example.BuonAppetito.requests.PrenotazioneRequest;
import com.example.BuonAppetito.responses.GenericResponse;
import com.example.BuonAppetito.responses.PrenotazioneResponse;
import com.example.BuonAppetito.services.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prenotazione")
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService prenotazioneService;

    @GetMapping("/get/{id}")
    public ResponseEntity<PrenotazioneResponse> getPrenotazioneById (@PathVariable Long id) throws PrenotazioneNotFoundException {
        return new ResponseEntity<>(prenotazioneService.getPrenotazioneById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Prenotazione>> getAllPrenotazioni () {
        return new ResponseEntity<>(prenotazioneService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<PrenotazioneResponse> createPrenotazione (@RequestBody PrenotazioneRequest request) throws UtenteNotFoundException, RistoranteNotFoundException {
        return new ResponseEntity<>(prenotazioneService.create(request), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PrenotazioneResponse> updatePrenotazione (@PathVariable Long id, @RequestBody Prenotazione prenotazione) throws PrenotazioneNotFoundException {
        return new ResponseEntity<>(prenotazioneService.update(id, prenotazione), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<GenericResponse> deletePrenotazioneById (@PathVariable Long id) throws PrenotazioneNotFoundException {
        prenotazioneService.deleteById(id);
        return new ResponseEntity<>(new GenericResponse("Prenotazione con id " + id + " eliminata con successo"), HttpStatus.OK);
    }

}
