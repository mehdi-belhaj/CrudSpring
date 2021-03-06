package com.example.demo.controllers;

import com.example.demo.config.services.UserDetailsImpl;
import com.example.demo.dto.AdminDto;
import com.example.demo.dto.CandidateDto;
import com.example.demo.dto.requests.AdminRequest;
import com.example.demo.dto.requests.CandidateRequest;
import com.example.demo.dto.responses.AdminResponse;
import com.example.demo.dto.responses.CandidateResponse;
import com.example.demo.dto.responses.MessageResponse;
import com.example.demo.entities.Admin;
import com.example.demo.entities.Candidate;
import com.example.demo.entities.Utilisateur;
import com.example.demo.services.AdminService;
import com.example.demo.services.CandidatService;
import com.example.demo.services.UserService;
import com.example.demo.utils.ResponseObject;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import javax.validation.Valid;

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

        /**
         * Create new Candidate
         *
         * @param candidateRequest
         * @return candidateResponse
         */
        @PostMapping("/candidate")
        public ResponseEntity<?> createCandidate(
                        @RequestBody @Valid CandidateRequest candidateRequest) {
                if (candidatService.existUsername(candidateRequest.getUsername())) {

                        return ResponseEntity.badRequest()
                                        .body(new MessageResponse("Error: Username is already taken!"));
                }

                if (candidatService.existEmail(candidateRequest.getEmail())) {
                        return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
                }
                CandidateDto candidateDto = new CandidateDto();

                CandidateResponse candidateResponse = new CandidateResponse();

                BeanUtils.copyProperties(candidateRequest, candidateDto);

                CandidateDto candidateDto2 = candidatService.createCandidate(candidateDto);

                BeanUtils.copyProperties(candidateDto2, candidateResponse);
                ResponseObject<CandidateResponse> responseObject = new ResponseObject<CandidateResponse>(true,
                                "Candidate created successfully", candidateResponse);
                return new ResponseEntity<ResponseObject<CandidateResponse>>(responseObject, HttpStatus.CREATED);

        }

        /**
         * Find a candidate by his id
         *
         * @param id
         * @return candidateResponse
         */
        @GetMapping("/candidate/{id}")
        public ResponseEntity<ResponseObject<CandidateResponse>> getCandidate(@PathVariable Long id) {

                CandidateDto candidateDto = candidatService.getCandidateById(id);

                CandidateResponse candidateResponse = new CandidateResponse();

                BeanUtils.copyProperties(candidateDto, candidateResponse);

                ResponseObject<CandidateResponse> responseObject = new ResponseObject<CandidateResponse>(true,
                                "Candidate data", candidateResponse);
                return new ResponseEntity<ResponseObject<CandidateResponse>>(responseObject, HttpStatus.OK);
        }

        @GetMapping("/candidates")
        public ResponseEntity<ResponseObject<List<Candidate>>> getAllCandidates() {
                ResponseObject<List<Candidate>> responseObject = new ResponseObject<List<Candidate>>(true,
                                "All Candidate data",
                                candidatService.getAllCandidate());
                return new ResponseEntity<ResponseObject<List<Candidate>>>(responseObject, HttpStatus.OK);
        }

        @GetMapping("/admins")
        public ResponseEntity<ResponseObject<List<AdminDto>>> getAllAdmins() {
                ResponseObject<List<AdminDto>> responseObject = new ResponseObject<List<AdminDto>>(true,
                                "All Admins data",
                                adminService.getAllAdmins());
                return new ResponseEntity<ResponseObject<List<AdminDto>>>(responseObject, HttpStatus.OK);
        }

        /**
         * Find All Candidates with search by firstname,lastname,username,email,address
         * and filter by the post or activityArea
         *
         * @param page
         * @param size
         * @param search
         * @param filter
         * @return Page<Candidate>
         */
        @GetMapping("/candidate")
        public ResponseEntity<ResponseObject<Page<Candidate>>> getAllCandidates(
                        @RequestParam(name = "page", defaultValue = "0") int page,
                        @RequestParam(name = "size", defaultValue = "10") int size,
                        @RequestParam(required = false) String search, @RequestParam(required = false) String filter) {
                ResponseObject<Page<Candidate>> responseObject = new ResponseObject<Page<Candidate>>();
                if (StringUtils.isEmpty(search) && StringUtils.isEmpty(filter)) {
                        responseObject = new ResponseObject<Page<Candidate>>(true, "All Candidate data",
                                        candidatService.findAllCandidates(PageRequest.of(page, size)));
                        return new ResponseEntity<ResponseObject<Page<Candidate>>>(responseObject, HttpStatus.OK);
                } else {
                        if (!StringUtils.isEmpty(search)) {
                                responseObject = new ResponseObject<Page<Candidate>>(true, "All Candidate data",
                                                candidatService.searchAllCandidates(search,
                                                                PageRequest.of(page, size)));
                        }
                        if (!StringUtils.isEmpty(filter)) {
                                responseObject = new ResponseObject<Page<Candidate>>(true, "All Candidate data",
                                                candidatService.filterAllCandidates(filter,
                                                                PageRequest.of(page, size)));
                        }
                        return new ResponseEntity<ResponseObject<Page<Candidate>>>(responseObject, HttpStatus.OK);

                }
        }

        /**
         * Delete a candidate giving his id
         *
         * @param id
         * @return String
         */
        @DeleteMapping("/candidate/{id}")
        public ResponseEntity<ResponseObject<String>> DeleteCandidate(@PathVariable Long id) {
                candidatService.deleteCandidate(id);
                ResponseObject<String> responseObject = new ResponseObject<String>(true,
                                "Candidate deleted successfully", null);
                return new ResponseEntity<ResponseObject<String>>(responseObject, HttpStatus.OK);
        }

        /**
         * Update a candidate giving his id
         *
         * @param id
         * @param candidateRequest
         * @return candidateResponse
         */
        @PutMapping("/candidate/{id}")
        public ResponseEntity<?> updateCandidate(@PathVariable Long id,
                        @Valid @RequestBody CandidateRequest candidateRequest) {
                Utilisateur user = userService.getUtilisateurById(id);
                if (!(user.getUsername().toLowerCase().equals(candidateRequest.getUsername().toLowerCase()))
                                && candidatService.existUsername(candidateRequest.getUsername().toLowerCase())) {

                        return ResponseEntity.badRequest()
                                        .body(new MessageResponse("Error: Username is already taken!"));
                }

                if (!(user.getEmail().toLowerCase().equals(candidateRequest.getEmail().toLowerCase()))
                                && candidatService.existEmail(candidateRequest.getEmail().toLowerCase())) {
                        return ResponseEntity.badRequest()
                                        .body(new MessageResponse("Error: Email is already in use!"));
                }

                CandidateDto candidateDto = new CandidateDto();

                BeanUtils.copyProperties(candidateRequest, candidateDto);

                CandidateDto updateCandidate = candidatService.updateCandidate(id, candidateDto);

                CandidateResponse candidateResponse = new CandidateResponse();

                BeanUtils.copyProperties(updateCandidate, candidateResponse);

                ResponseObject<CandidateResponse> responseObject = new ResponseObject<CandidateResponse>(true,
                                "Candidate updated successfully", candidateResponse);
                return new ResponseEntity<ResponseObject<CandidateResponse>>(responseObject, HttpStatus.OK);
        }

        /**
         * Update personal data of the Admin
         *
         * @param adminRequest
         * @return adminResponse
         */
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
                adminResponse.setRole("ROLE_ADMIN");
                ResponseObject<AdminResponse> responseObject = new ResponseObject<AdminResponse>(true,
                                "Admin updated successfully", adminResponse);
                return new ResponseEntity<ResponseObject<AdminResponse>>(responseObject, HttpStatus.OK);
        }

        /**
         * Import Candidates
         *
         * @param MultipartFile
         */
        @PostMapping(path = "/candidate/upload")
        public ResponseEntity<?> UploadRecipients(@RequestParam("file") MultipartFile file)
                        throws Exception {
                if (!"csv".equals(FilenameUtils.getExtension(file.getOriginalFilename()))) {
                        return ResponseEntity.badRequest()
                                        .body(new MessageResponse("Error: File format is not supported!"));
                }
                try {
                        candidatService.uploadCandidates(file);
                        ResponseObject<Void> responseObject = new ResponseObject<>(true,
                                        "Recipients uploaded successfully", null);
                        return new ResponseEntity<>(responseObject, HttpStatus.OK);

                } catch (Exception e) {
                        ResponseObject<Void> responseObject = new ResponseObject<>(false,
                                        "Could not upload the file: " + file.getOriginalFilename() + "!", null);
                        return new ResponseEntity<>(responseObject, HttpStatus.EXPECTATION_FAILED);
                }

        }
}
