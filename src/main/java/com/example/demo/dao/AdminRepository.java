package com.example.demo.dao;

import java.util.Optional;

import com.example.demo.entities.Admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    public Optional<Admin> findByUsername(String username);

    public Optional<Admin> findByEmail(String email);

}
