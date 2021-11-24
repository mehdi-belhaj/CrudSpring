package com.example.demo.controllers;

import com.example.demo.dto.CandidateDto;
import com.example.demo.dto.requests.CandidateRequest;
import com.example.demo.dto.responses.CandidateResponse;
import com.example.demo.services.CandidatService;
import com.example.demo.utils.ResponseObject;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("${api.endpoint}/candidat")
public class CandidatController {

    @Autowired
    CandidatService candidateService;

    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject<CandidateResponse>> updateCandidate(@PathVariable Long id,
            @Valid @RequestBody CandidateRequest candidateRequest) {

        CandidateDto candidateDto = new CandidateDto();

        BeanUtils.copyProperties(candidateRequest, candidateDto);

        CandidateDto updateCandidate = candidateService.updateCandidate(id, candidateDto);

        CandidateResponse candidateResponse = new CandidateResponse();

        BeanUtils.copyProperties(updateCandidate, candidateResponse);

        ResponseObject<CandidateResponse> responseObject = new ResponseObject<CandidateResponse>(true,
                "Candidate updated successfully", candidateResponse);
        return new ResponseEntity<ResponseObject<CandidateResponse>>(responseObject, HttpStatus.OK);
    }

}
