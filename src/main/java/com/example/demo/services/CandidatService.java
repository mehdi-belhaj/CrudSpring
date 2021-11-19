package com.example.demo.services;

import com.example.demo.entities.Candidate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CandidatService {

    public Candidate saveCandidat(Candidate candidate);
    public Optional<Candidate> findCandidatById(Long id);
    public Optional<Candidate> findCandidatByUsername(String username);
    public Optional<Candidate> findCandidatByEmail(String email);
    public void deleteCandidat(Long id);
    public List<Candidate> getCandidates();


}
