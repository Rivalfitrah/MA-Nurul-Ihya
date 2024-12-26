package com.example.manurul.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.manurul.entity.Berita;

public interface BeritaRepository extends JpaRepository<Berita, Integer> {
    
}
