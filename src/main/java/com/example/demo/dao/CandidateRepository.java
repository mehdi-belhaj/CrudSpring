package com.example.demo.dao;

import com.example.demo.entities.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate,Long> {


    public Candidate findByEmail(String email);
    public Candidate findByUsername(String username);

    /*Optional<Candidate> findByEmail(String email);

    Optional<Candidate> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);*/


}
