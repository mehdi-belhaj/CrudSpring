package com.example.demo.dao;

import com.example.demo.entities.Admin;
import com.example.demo.entities.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository <Admin, Long >{
    public Admin findByUsername (String username);
    public Admin findByEmail(String email);

}