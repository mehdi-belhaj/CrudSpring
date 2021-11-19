package com.example.demo.services.impl;

import com.example.demo.dao.CandidateRepository;
import com.example.demo.entities.Candidate;
import com.example.demo.services.CandidatService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class CandidatServiceImpl implements CandidatService {

    @Autowired
    CandidateRepository candidateRepository;

    @Override
    public Candidate saveCandidat(Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    @Override
    public Optional<Candidate> findCandidatById(Long id) {
        return candidateRepository.findById(id);
    }

    @Override
    public Optional<Candidate> findCandidatByUsername(String username) {
        return candidateRepository.findByUsername(username);
    }

    @Override
    public Optional<Candidate> findCandidatByEmail(String email) {
        return candidateRepository.findByEmail(email);
    }

    @Override
    public void deleteCandidat(Long id) {

        candidateRepository.deleteById(id);

    }

    @Override
    public List<Candidate> getCandidates() {
        return candidateRepository.findAll();
    }
}
