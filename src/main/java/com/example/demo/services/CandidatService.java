package com.example.demo.services;

import com.example.demo.dto.CandidateDto;
import com.example.demo.entities.Candidate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface CandidatService {

    CandidateDto createCandidate(CandidateDto candidateDto);

    CandidateDto getCandidate(String email);

    CandidateDto getCandidateByUsername(String username) ;

    CandidateDto updateCandidate(String username, CandidateDto candidateDto);

    void deleteCandidate(String username);


    List<CandidateDto> getAllCandidates();



}
