package com.example.BuonAppetito.controllers;

import com.example.BuonAppetito.entities.Conto;
import com.example.BuonAppetito.exceptions.*;
import com.example.BuonAppetito.requests.ContoRequest;
import com.example.BuonAppetito.responses.ContoResponse;
import com.example.BuonAppetito.responses.GenericResponse;
import com.example.BuonAppetito.services.ContoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/conto")
public class ContoController {

    @Autowired
    private ContoService contoService;

    @GetMapping("/get/{id}")
    public ResponseEntity<ContoResponse> getContoById (@PathVariable Long id) throws ContoNotFoundException {
        return new ResponseEntity<>(contoService.getContoById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllConti () {
        return new ResponseEntity<>(contoService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ContoResponse> createConto (@RequestBody ContoRequest request) throws PrenotazioneNotFoundException, UtenteNotFoundException, RistoranteNotFoundException, MenùNotFoundException {
        return new ResponseEntity<>(contoService.create(request), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ContoResponse> updateConto (@PathVariable Long id, @RequestBody Conto conto) throws ContoNotFoundException {
        return new ResponseEntity<>(contoService.update(id, conto), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<GenericResponse> deleteConto (@PathVariable Long id) throws ContoNotFoundException {
        contoService.delete(id);
        return new ResponseEntity<>(new GenericResponse("Conto con id " + id + " eliminato con successo"), HttpStatus.OK);
    }

}
