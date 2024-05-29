package com.example.BuonAppetito.controllers;

import com.example.BuonAppetito.entities.Pietanza;
import com.example.BuonAppetito.exceptions.PietanzaNotFoundException;
import com.example.BuonAppetito.responses.GenericResponse;
import com.example.BuonAppetito.services.PietanzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pietanza")
public class PietanzaController {

    @Autowired
    private PietanzaService pietanzaService;

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getPietanzaById (@PathVariable Long id) throws PietanzaNotFoundException {
        return new ResponseEntity<>(pietanzaService.getPietanzaById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Pietanza>> getAllPietanze () {
        return new ResponseEntity<>(pietanzaService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createPietanza (@RequestBody Pietanza pietanza) {
        return new ResponseEntity<>(pietanzaService.create(pietanza), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updatePietanza (@PathVariable Long id, @RequestBody Pietanza pietanza) throws PietanzaNotFoundException {
        return new ResponseEntity<>(pietanzaService.update(id, pietanza), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<GenericResponse> deletePietanzaById (@PathVariable Long id) throws PietanzaNotFoundException {
        pietanzaService.deleteById(id);
        return new ResponseEntity<>(new GenericResponse("Pietanza con id " + id + " eliminata con successo"), HttpStatus.OK);
    }

}
