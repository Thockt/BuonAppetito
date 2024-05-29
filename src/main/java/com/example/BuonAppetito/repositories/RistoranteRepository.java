package com.example.BuonAppetito.repositories;

import com.example.BuonAppetito.entities.Ristorante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RistoranteRepository extends JpaRepository<Ristorante, Long> {
}
