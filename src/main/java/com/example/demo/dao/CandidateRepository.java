package com.example.demo.dao;

import com.example.demo.entities.Candidate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    public Optional<Candidate> findByEmail(String email);

    public Optional<Candidate> findByUsername(String username);

    public Page<Candidate> findAll(Pageable pageable);

}
