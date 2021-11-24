package com.example.demo.controllers;

import com.example.demo.dto.CandidateDto;
import com.example.demo.dto.requests.CandidateRequest;
import com.example.demo.dto.responses.CandidateResponse;
import com.example.demo.entities.Candidate;
import com.example.demo.services.CandidatService;
import com.example.demo.services.UserService;
import com.example.demo.utils.ResponseObject;

import com.example.demo.config.services.UserDetailsImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("${api.endpoint}/candidat")
public class CandidatController {

    @Autowired
    CandidatService candidateService;
    @Autowired
    UserService userService;

    @PutMapping("/personalData")
    public ResponseEntity<ResponseObject<CandidateResponse>> updateCandidate(
            @Valid @RequestBody CandidateRequest candidateRequest) {

        Object userPrincipal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String username = ((UserDetailsImpl) userPrincipal).getUsername();

        Candidate user = (Candidate) this.userService.getUtilisateur(username);

        CandidateDto candidateDto = new CandidateDto();

        BeanUtils.copyProperties(candidateRequest, candidateDto);

        CandidateDto updateCandidate = candidateService.updateCandidate(user.getId(), candidateDto);

        CandidateResponse candidateResponse = new CandidateResponse();

        BeanUtils.copyProperties(updateCandidate, candidateResponse);

        ResponseObject<CandidateResponse> responseObject = new ResponseObject<CandidateResponse>(true,
                "Candidate updated successfully", candidateResponse);
        return new ResponseEntity<ResponseObject<CandidateResponse>>(responseObject, HttpStatus.OK);
    }

}
