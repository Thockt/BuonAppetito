package com.example.BuonAppetito.controllers;

import com.example.BuonAppetito.entities.Ristorante;
import com.example.BuonAppetito.exceptions.ComuneNotFoundException;
import com.example.BuonAppetito.exceptions.MenùNotFoundException;
import com.example.BuonAppetito.exceptions.RistoranteNotFoundException;
import com.example.BuonAppetito.exceptions.UtenteNotFoundException;
import com.example.BuonAppetito.requests.RistoranteRequest;
import com.example.BuonAppetito.responses.GenericResponse;
import com.example.BuonAppetito.responses.RistoranteResponse;
import com.example.BuonAppetito.services.RistoranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ristorante")
public class RistoranteController {

    @Autowired
    private RistoranteService ristoranteService;

    @GetMapping("/get/{id}")
    public ResponseEntity<RistoranteResponse> getRistoranteById (@PathVariable Long id) throws RistoranteNotFoundException {
        return new ResponseEntity<>(ristoranteService.getRistoranteById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Ristorante>> getAllRistoranti () {
        return new ResponseEntity<>(ristoranteService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<RistoranteResponse> createRistorante (@RequestBody RistoranteRequest request) throws MenùNotFoundException, UtenteNotFoundException, ComuneNotFoundException {
        return new ResponseEntity<>(ristoranteService.create(request), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<RistoranteResponse> updateRistorante (@PathVariable Long id, @RequestBody Ristorante ristorante) throws RistoranteNotFoundException {
        return new ResponseEntity<>(ristoranteService.update(id, ristorante), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<GenericResponse> deleteRistoranteById (@PathVariable Long id) throws RistoranteNotFoundException {
        ristoranteService.deleteById(id);
        return new ResponseEntity<>(new GenericResponse("Ristorante con id " + id + " eliminato con successo"), HttpStatus.OK);
    }

}
