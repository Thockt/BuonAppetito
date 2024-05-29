package com.example.BuonAppetito.repositories;

import com.example.BuonAppetito.entities.Conto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContoRepository extends JpaRepository<Conto, Long> {
}
