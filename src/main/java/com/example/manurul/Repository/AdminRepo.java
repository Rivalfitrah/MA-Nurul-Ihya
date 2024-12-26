package com.example.manurul.Repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.manurul.entity.Admin;

public interface AdminRepo extends JpaRepository <Admin, String> {
    Optional<Admin> findByUsernameAndPassword(String username, String password);
    
    Optional<Admin> findFirstByUsername(String username);
}