package com.example.BuonAppetito.controllers;

import com.example.BuonAppetito.entities.Menù;
import com.example.BuonAppetito.exceptions.MenùNotFoundException;
import com.example.BuonAppetito.exceptions.PietanzaNotFoundException;
import com.example.BuonAppetito.responses.GenericResponse;
import com.example.BuonAppetito.services.MenùService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menù")
public class MenùController {

    @Autowired
    private MenùService menùService;

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getMenùById (@PathVariable Long id) throws MenùNotFoundException {
        return new ResponseEntity<>(menùService.getMenùById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Menù>> getAllMenù () {
        return new ResponseEntity<>(menùService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createMenù (@RequestBody List<Long> cibi) throws PietanzaNotFoundException {
        return new ResponseEntity<>(menùService.create(cibi), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateMenù (@PathVariable Long id, @RequestBody List<Long> cibi) throws MenùNotFoundException, PietanzaNotFoundException {
        return new ResponseEntity<>(menùService.update(id, cibi), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<GenericResponse> deleteMenùById (@PathVariable Long id) throws MenùNotFoundException {
        menùService.deleteById(id);
        return new ResponseEntity<>(new GenericResponse("Menù con id " + id + " eliminato con successo"), HttpStatus.OK);
    }

}
