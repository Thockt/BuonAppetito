package com.example.BuonAppetito.repositories;

import com.example.BuonAppetito.entities.Pietanza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PietanzaRepository extends JpaRepository<Pietanza, Long> {
}
