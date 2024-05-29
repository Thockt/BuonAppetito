package com.example.BuonAppetito.controllers;

import com.example.BuonAppetito.entities.Utente;
import com.example.BuonAppetito.exceptions.UtenteNotFoundException;
import com.example.BuonAppetito.responses.GenericResponse;
import com.example.BuonAppetito.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utente")
public class UtenteController {

    @Autowired
    private UtenteService utenteService;

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getUtenteById (@PathVariable Long id) throws UtenteNotFoundException {
        return new ResponseEntity<>(utenteService.getUtenteById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Utente>> getAllUtenti () {
        return new ResponseEntity<>(utenteService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUtente (@RequestBody Utente utente) {
        return new ResponseEntity<>(utenteService.create(utente), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUtente (@PathVariable Long id, Utente utente) throws UtenteNotFoundException {
        return new ResponseEntity<>(utenteService.update(id, utente), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<GenericResponse> deleteUteneteById (@PathVariable Long id) throws UtenteNotFoundException {
        utenteService.deleteById(id);
        return new ResponseEntity<>(new GenericResponse("Utente con id " + id + " eliminato con successo"), HttpStatus.OK);
    }

}
