package com.example.BuonAppetito.repositories;

import com.example.BuonAppetito.entities.Posto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostoRepository extends JpaRepository<Posto, Long> {
}
