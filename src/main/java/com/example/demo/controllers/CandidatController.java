package com.example.demo.controllers;

import com.example.demo.dto.CandidateDto;
import com.example.demo.dto.requests.CandidateRequest;
import com.example.demo.dto.responses.CandidateResponse;
import com.example.demo.services.CandidatService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api")
public class CandidatController {



@Autowired
    CandidatService candidateService;

    @PutMapping("/candidates/{id}")
    public ResponseEntity<CandidateResponse> updateCandidate( @PathVariable String id, @Valid @RequestBody CandidateRequest candidateRequest){

        CandidateDto candidateDto = new CandidateDto();

        BeanUtils.copyProperties(candidateRequest, candidateDto);

        CandidateDto updateCandidate = candidateService.updateCandidate(id, candidateDto);

        CandidateResponse candidateResponse = new CandidateResponse();

        BeanUtils.copyProperties(updateCandidate, candidateResponse);

        return new ResponseEntity<CandidateResponse>(candidateResponse, HttpStatus.ACCEPTED);
    }

/*

    @Autowired
    CandidatService candidatService;

    @RequestMapping("/candidates")
    public ResponseEntity<List<Candidate>> getAllCandidats(){
        List<Candidate> candidates = candidatService.getCandidates();
        return new ResponseEntity<>(candidates, HttpStatus.OK);
    }

    @PostMapping("/candidates")
    public ResponseEntity<Candidate> save(@RequestBody Candidate candidate) {
        Candidate newCategory = candidatService.saveCandidat(candidate);
        return new ResponseEntity<>(newCategory, HttpStatus.OK);
    }
*/

}


