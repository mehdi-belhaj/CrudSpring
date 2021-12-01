package com.example.demo.services;

import com.example.demo.dto.CandidateDto;
import com.example.demo.entities.Candidate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CandidatService {

    CandidateDto createCandidate(CandidateDto candidateDto);

    CandidateDto getCandidate(String email);

    CandidateDto getCandidateById(Long id);

    CandidateDto getCandidateByUsername(String username);

    CandidateDto updateCandidate(Long id, CandidateDto candidateDto);

    void deleteCandidate(Long id);

    List<CandidateDto> getAllCandidates();

    Page<Candidate> findAllCandidates(Pageable pageable);

    Page<Candidate> searchAllCandidates(String param, Pageable pageable);

    Page<Candidate> filterAllCandidates(String param, Pageable pageable);

    Boolean existUsername(String username);

    Boolean existEmail(String email);

}
