package com.example.BuonAppetito.controllers;

import com.example.BuonAppetito.entities.Posto;
import com.example.BuonAppetito.exceptions.PostoNotFoundException;
import com.example.BuonAppetito.responses.GenericResponse;
import com.example.BuonAppetito.services.PostoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posto")
public class PostoController {

    @Autowired
    private PostoService postoService;

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getPostoById(@PathVariable Long id) throws PostoNotFoundException {
        return new ResponseEntity<>(postoService.getPostoById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Posto>> getAllPosti () {
        return new ResponseEntity<>(postoService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createPosto (@RequestBody Posto posto) {
        return new ResponseEntity<>(postoService.create(posto), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updatePosto (@PathVariable Long id, @RequestBody Posto posto) throws PostoNotFoundException {
        return new ResponseEntity<>(postoService.update(id, posto), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<GenericResponse> deletePostoById (@PathVariable Long id) throws PostoNotFoundException {
        postoService.deleteById(id);
        return new ResponseEntity<>(new GenericResponse("Posto con id " + id + " eliminato con successo"), HttpStatus.OK);
    }

}
