package com.example.demo.dao;

import com.example.demo.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Utilisateur, Long> {

    Utilisateur findUserByEmail(String email);

    Optional<Utilisateur> findByUsernameOrEmail(String username, String email);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Optional<Utilisateur> findByUsername(String username);

}
