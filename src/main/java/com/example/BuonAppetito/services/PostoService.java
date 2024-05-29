package com.example.BuonAppetito.services;

import com.example.BuonAppetito.entities.Posto;
import com.example.BuonAppetito.exceptions.PostoNotFoundException;
import com.example.BuonAppetito.repositories.PostoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostoService {

    @Autowired
    private PostoRepository postoRepository;

    public Posto getPostoById (Long id) throws PostoNotFoundException {
        Optional<Posto> optionalPosto = postoRepository.findById(id);
        if (optionalPosto.isEmpty()) throw new PostoNotFoundException();
        return optionalPosto.get();
    }

    public List<Posto> getAll() {
        return postoRepository.findAll();
    }

    public Posto create (Posto posto) {
        posto.setInsertTime(LocalDateTime.now());
        return postoRepository.saveAndFlush(posto);
    }

    public Posto update (Long id, Posto newPosto) throws PostoNotFoundException {
        getPostoById(id);
        Posto posto = Posto.builder()
                .id(id)
                .nPosto(newPosto.getNPosto())
                .insertTime(getPostoById(id).getInsertTime())
                .lastUpdate(LocalDateTime.now())
                .build();
        return postoRepository.saveAndFlush(posto);
    }

    public void deleteById (Long id) throws PostoNotFoundException {
        getPostoById(id);
        postoRepository.deleteById(id);
    }

}
