package com.example.manurul.Service;

import org.springframework.stereotype.Service;

import com.example.manurul.Repository.AdminRepo;
import com.example.manurul.entity.Admin;


@Service
public class AdminService {

    private final AdminRepo adminRepo;

    public AdminService (AdminRepo adminRepo){
    this.adminRepo = adminRepo;
    }

    public Admin authenticate(String username, String password){
        return adminRepo.findByUsernameAndPassword(username, password).orElse(null);
    }
}