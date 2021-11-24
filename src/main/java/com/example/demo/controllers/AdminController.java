package com.example.demo.controllers;

import javax.validation.Valid;

import com.example.demo.dto.AdminDto;
import com.example.demo.dto.CandidateDto;
import com.example.demo.dto.requests.AdminRequest;
import com.example.demo.dto.requests.CandidateRequest;
import com.example.demo.dto.responses.AdminResponse;
import com.example.demo.dto.responses.CandidateResponse;
import com.example.demo.entities.Admin;
import com.example.demo.entities.Candidate;
import com.example.demo.services.AdminService;
import com.example.demo.services.CandidatService;
import com.example.demo.services.UserService;
import com.example.demo.utils.ResponseObject;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.context.SecurityContextHolder;
import com.example.demo.config.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("${api.endpoint}/admin")

public class AdminController {
        @Autowired
        AdminService adminService;
        @Autowired
        CandidatService candidatService;
        @Autowired
        UserService userService;

        @PostMapping("/candidate")
        public ResponseEntity<ResponseObject<CandidateResponse>> createCandidate(
                        @RequestBody @Valid CandidateRequest candidateRequest) {

                CandidateDto candidateDto = new CandidateDto();

                CandidateResponse candidateResponse = new CandidateResponse();

                BeanUtils.copyProperties(candidateRequest, candidateDto);

                CandidateDto candidateDto2 = candidatService.createCandidate(candidateDto);

                BeanUtils.copyProperties(candidateDto2, candidateResponse);
                ResponseObject<CandidateResponse> responseObject = new ResponseObject<CandidateResponse>(true,
                                "Candidate created successfully", candidateResponse);
                return new ResponseEntity<ResponseObject<CandidateResponse>>(responseObject, HttpStatus.CREATED);

        }

        @GetMapping("/candidate/{id}")
        public ResponseEntity<ResponseObject<CandidateResponse>> getCandidate(@PathVariable Long id) {

                CandidateDto candidateDto = candidatService.getCandidateById(id);

                CandidateResponse candidateResponse = new CandidateResponse();

                BeanUtils.copyProperties(candidateDto, candidateResponse);

                ResponseObject<CandidateResponse> responseObject = new ResponseObject<CandidateResponse>(true,
                                "Candidate data", candidateResponse);
                return new ResponseEntity<ResponseObject<CandidateResponse>>(responseObject, HttpStatus.OK);
        }

        @GetMapping("/candidate")
        public ResponseEntity<ResponseObject<Page<Candidate>>> getAllCandidates(
                        @RequestParam(name = "page", defaultValue = "0") int page,
                        @RequestParam(name = "size", defaultValue = "10") int size) {
                ResponseObject<Page<Candidate>> responseObject = new ResponseObject<Page<Candidate>>(true,
                                "All Candidate data", candidatService.findAllCandidates(PageRequest.of(page, size)));
                return new ResponseEntity<ResponseObject<Page<Candidate>>>(responseObject, HttpStatus.OK);
        }

        @DeleteMapping("/candidate/{id}")
        public ResponseEntity<ResponseObject<String>> DeleteCandidate(@PathVariable Long id) {
                candidatService.deleteCandidate(id);
                ResponseObject<String> responseObject = new ResponseObject<String>(true,
                                "Candidate deleted successfully", null);
                return new ResponseEntity<ResponseObject<String>>(responseObject, HttpStatus.OK);
        }

        @PutMapping("/candidate/{id}")
        public ResponseEntity<ResponseObject<CandidateResponse>> updateCandidate(@PathVariable Long id,
                        @Valid @RequestBody CandidateRequest candidateRequest) {

                CandidateDto candidateDto = new CandidateDto();

                BeanUtils.copyProperties(candidateRequest, candidateDto);

                CandidateDto updateCandidate = candidatService.updateCandidate(id, candidateDto);

                CandidateResponse candidateResponse = new CandidateResponse();

                BeanUtils.copyProperties(updateCandidate, candidateResponse);

                ResponseObject<CandidateResponse> responseObject = new ResponseObject<CandidateResponse>(true,
                                "Candidate updated successfully", candidateResponse);
                return new ResponseEntity<ResponseObject<CandidateResponse>>(responseObject, HttpStatus.OK);
        }

        @PutMapping("/personalData")
        public ResponseEntity<ResponseObject<AdminResponse>> updateCandidate(
                        @Valid @RequestBody AdminRequest adminRequest) {

                Object userPrincipal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

                String username = ((UserDetailsImpl) userPrincipal).getUsername();

                Admin user = (Admin) this.userService.getUtilisateur(username);

                AdminDto adminDto = new AdminDto();

                BeanUtils.copyProperties(adminRequest, adminDto);

                AdminDto adminDto2 = adminService.updateAdmin(user.getId(), adminDto);

                AdminResponse adminResponse = new AdminResponse();

                BeanUtils.copyProperties(adminDto2, adminResponse);

                ResponseObject<AdminResponse> responseObject = new ResponseObject<AdminResponse>(true,
                                "Admin updated successfully", adminResponse);
                return new ResponseEntity<ResponseObject<AdminResponse>>(responseObject, HttpStatus.OK);
        }
}
