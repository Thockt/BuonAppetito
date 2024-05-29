package com.example.BuonAppetito.repositories;

import com.example.BuonAppetito.entities.Menù;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenùRepository extends JpaRepository<Menù, Long> {
}
