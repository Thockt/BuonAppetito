package com.example.BuonAppetito.controllers;

import com.example.BuonAppetito.entities.Comune;
import com.example.BuonAppetito.exceptions.ComuneNotFoundException;
import com.example.BuonAppetito.requests.ComuneRequest;
import com.example.BuonAppetito.responses.GenericResponse;
import com.example.BuonAppetito.services.ComuneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comune")
public class ComuneController {

    @Autowired
    private ComuneService comuneService;

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getComuneById (@PathVariable Long id) throws ComuneNotFoundException {
        return new ResponseEntity<>(comuneService.getComuneById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllComuni () {
        return new ResponseEntity<>(comuneService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createComune (@RequestBody ComuneRequest request) {
        return new ResponseEntity<>(comuneService.create(request), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateComune (@PathVariable Long id, @RequestBody Comune comune) throws ComuneNotFoundException {
        return new ResponseEntity<>(comuneService.update(id, comune), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<GenericResponse> deleteComune (@PathVariable Long id) throws ComuneNotFoundException{
        comuneService.delete(id);
        return new ResponseEntity<>(new GenericResponse("Comune con id " +id+ " eliminato con successo"),  HttpStatus.OK);
    }

}
