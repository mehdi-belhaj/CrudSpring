package com.example.demo.services;

import java.io.IOException;
import java.util.List;

import com.example.demo.dto.CandidateDto;
import com.example.demo.entities.Candidate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface CandidatService {
    List<Candidate> getAllCandidate();

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

    void uploadCandidates(MultipartFile file) throws IOException;

}
