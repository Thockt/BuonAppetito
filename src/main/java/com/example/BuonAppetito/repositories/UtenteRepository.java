package com.example.BuonAppetito.repositories;

import com.example.BuonAppetito.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Long> {

    Utente findUtenteByEmail (String email);
}
