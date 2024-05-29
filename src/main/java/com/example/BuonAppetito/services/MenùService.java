package com.example.BuonAppetito.services;

import com.example.BuonAppetito.entities.Menù;
import com.example.BuonAppetito.entities.Pietanza;
import com.example.BuonAppetito.exceptions.MenùNotFoundException;
import com.example.BuonAppetito.exceptions.PietanzaNotFoundException;
import com.example.BuonAppetito.repositories.MenùRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MenùService {

    @Autowired
    private MenùRepository menùRepository;
    @Autowired
    private PietanzaService pietanzaService;

    public Menù getMenùById (Long id) throws MenùNotFoundException {
        Optional<Menù> menùOptional = menùRepository.findById(id);
        if (menùOptional.isEmpty()) throw new MenùNotFoundException();
        return menùOptional.get();
    }

    public List<Menù> getAll () {
       return menùRepository.findAll();
    }

    public Menù create (List<Long> pietanze) throws PietanzaNotFoundException {
        List<Pietanza> c = creaListaCibo(pietanze);
        Menù menù = Menù.builder()
                .cibo(c)
                .prezzo(calcolaPrezzo(c))
                .insertTime(LocalDateTime.now())
                .build();
        return menùRepository.saveAndFlush(menù);
    }

    public Menù update (Long id, List<Long> newPietanze) throws MenùNotFoundException, PietanzaNotFoundException {
        getMenùById(id);
        List<Pietanza> newCibo = creaListaCibo(newPietanze);
        Menù menù = Menù.builder()
                .id(id)
                .cibo(newCibo)
                .prezzo(calcolaPrezzo(newCibo))
                .insertTime(getMenùById(id).getInsertTime())
                .lastUpdate(LocalDateTime.now())
                .build();
        return menùRepository.saveAndFlush(menù);
    }

    public void deleteById (Long id) throws MenùNotFoundException {
        getMenùById(id);
        menùRepository.deleteById(id);
    }

    private List<Pietanza> creaListaCibo (List<Long> ciboL) throws PietanzaNotFoundException {
        List<Pietanza> cibi = new ArrayList<>();
        for (Long cl: ciboL) {
            cibi.add(pietanzaService.getPietanzaById(cl));
        }
        return cibi;
    }

    private Double calcolaPrezzo (List<Pietanza> cibo) throws PietanzaNotFoundException {
        Double tot = 0.0;
        for (Pietanza c: cibo){
            tot = tot + pietanzaService.getPietanzaById(c.getId()).getPrezzo();
        }
        return tot;
    }
}
