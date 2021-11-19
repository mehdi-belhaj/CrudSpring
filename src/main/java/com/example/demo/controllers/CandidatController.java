package com.example.demo.controllers;

import com.example.demo.entities.Candidate;
import com.example.demo.services.CandidatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CandidatController {


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

}


