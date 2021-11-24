package com.example.demo.services;

import java.util.List;

import com.example.demo.dto.CandidateDto;

public interface CandidatService {

    CandidateDto createCandidate(CandidateDto candidateDto);

    CandidateDto getCandidate(String email);

    CandidateDto getCandidateByUsername(String username);

    CandidateDto updateCandidate(Long id, CandidateDto candidateDto);

    void deleteCandidate(String username);

    List<CandidateDto> getAllCandidates();

}
