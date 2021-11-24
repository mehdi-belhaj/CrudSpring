package com.example.demo.services.impl;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.dao.CandidateRepository;
import com.example.demo.dto.CandidateDto;
import com.example.demo.entities.Candidate;
import com.example.demo.exceptions.EntityNotFoundException;
import com.example.demo.services.CandidatService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CandidatServiceImpl implements CandidatService {

    @Autowired
    CandidateRepository candidateRepository;

    @Override
    public CandidateDto createCandidate(CandidateDto candidateDto) {
        Candidate checkCandidate = candidateRepository.findByUsername(candidateDto.getUsername())
                .orElseThrow(() -> new EntityNotFoundException(" User not found !"));

        if (checkCandidate != null) {
            throw new RuntimeException(String.valueOf(HttpStatus.FOUND.value()));
        }
        Candidate candidateEntity = new Candidate();
        BeanUtils.copyProperties(candidateDto, candidateEntity);
        Candidate newCandidate = candidateRepository.save(candidateEntity);
        CandidateDto candidateDto1 = new CandidateDto();
        BeanUtils.copyProperties(newCandidate, candidateDto1);
        return candidateDto1;
    }

    @Override
    public CandidateDto getCandidate(String email) {
        Candidate candidate = candidateRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(" User not found !"));
        CandidateDto candidateDto = new CandidateDto();
        BeanUtils.copyProperties(candidate, candidateDto);
        return candidateDto;
    }

    @Override
    public CandidateDto getCandidateByUsername(String username) {
        Candidate candidateEntity = candidateRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(" User not found !"));

        if (candidateEntity == null) {
            throw new UsernameNotFoundException(username);
        }
        CandidateDto candidateDto = new CandidateDto();
        BeanUtils.copyProperties(candidateEntity, candidateDto);
        return candidateDto;
    }

    @Override
    public CandidateDto updateCandidate(Long id, CandidateDto candidateDto) {
        Candidate candidateEntity = candidateRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(" User not found !"));

        if (candidateEntity == null)
            throw new UsernameNotFoundException("user not found");

        if (candidateDto.getFirstname() != null)
            candidateEntity.setFirstname(candidateDto.getFirstname());
        if (candidateDto.getLastname() != null)
            candidateEntity.setLastname(candidateDto.getLastname());
        if (candidateDto.getEmail() != null)
            candidateEntity.setEmail(candidateDto.getEmail());
        if (candidateDto.getAddress() != null)
            candidateEntity.setAddress(candidateDto.getAddress());
        if (candidateDto.getDateOfBirth() != null)
            candidateEntity.setDateOfBirth(candidateDto.getDateOfBirth());
        if (candidateDto.getGender() != null)
            candidateEntity.setGender(candidateDto.getGender());
        if (candidateDto.getPhone() != null)
            candidateEntity.setPhone(candidateDto.getPhone());
        if (candidateDto.getPicture() != null)
            candidateEntity.setPicture(candidateDto.getPicture());
        if (candidateDto.getPoste() != null)
            candidateEntity.setPoste(candidateDto.getPoste());
        if (candidateDto.getActivityArea() != null)
            candidateEntity.setActivityArea(candidateDto.getActivityArea());

        Candidate candidateUpdated = candidateRepository.save(candidateEntity);

        CandidateDto candidateDto1 = new CandidateDto();

        BeanUtils.copyProperties(candidateUpdated, candidateDto1);

        return candidateDto1;
    }

    @Override
    public void deleteCandidate(String username) {
        Candidate candidateEntity = candidateRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(" User not found !"));

        if (candidateEntity == null) {
            throw new EntityNotFoundException("This account does not exist");
        }

        candidateRepository.delete(candidateEntity);
    }

    @Override
    public List<CandidateDto> getAllCandidates() {
        List<Candidate> candidates = candidateRepository.findAll();

        List<CandidateDto> candidateDtoList = new ArrayList<>();

        for (Candidate c : candidates) {

            CandidateDto candidateDto = new CandidateDto();

            BeanUtils.copyProperties(c, candidateDto);

            candidateDtoList.add(candidateDto);
        }

        return candidateDtoList;
    }

    /*
     * @Override public Candidate saveCandidat(Candidate candidate) { return
     * candidateRepository.save(candidate); }
     * 
     * @Override public Optional<Candidate> findCandidatById(Long id) { return
     * candidateRepository.findById(id); }
     * 
     * @Override public Optional<Candidate> findCandidatByUsername(String username)
     * { return candidateRepository.findByUsername(username); }
     * 
     * @Override public Optional<Candidate> findCandidatByEmail(String email) {
     * return candidateRepository.findByEmail(email); }
     * 
     * @Override public void deleteCandidat(Long id) {
     * 
     * candidateRepository.deleteById(id);
     * 
     * }
     * 
     * @Override public List<Candidate> getCandidates() { return
     * candidateRepository.findAll(); }
     */
}
