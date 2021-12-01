package com.example.demo.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.security.auth.login.CredentialException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.example.demo.config.jwt.JwtUtils;
import com.example.demo.config.services.UserDetailsImpl;
import com.example.demo.dao.CandidateRepository;
import com.example.demo.dao.RoleRepository;
import com.example.demo.dao.UserRepository;
import com.example.demo.dao.AdminRepository;
import com.example.demo.dto.requests.LoginRequest;
import com.example.demo.dto.requests.SignupRequest;
import com.example.demo.dto.responses.JwtResponse;
import com.example.demo.dto.responses.MessageResponse;
import com.example.demo.dto.responses.UtilisateurResponse;
import com.example.demo.entities.Admin;
import com.example.demo.entities.Candidate;
import com.example.demo.entities.Role;
import com.example.demo.entities.Utilisateur;
import com.example.demo.enumerations.RoleName;
import com.example.demo.exceptions.EntityNotFoundException;
import com.example.demo.services.UserService;
import com.example.demo.utils.ResponseObject;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("${api.endpoint}/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CandidateRepository candidateRepository;

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserService userService;

    @PostMapping("/signin")
    public ResponseEntity<ResponseObject<JwtResponse>> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
                .collect(Collectors.toList());
        JwtResponse jwtResponse = new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(),
                userDetails.getEmail(), roles.get(0));
        ResponseObject<JwtResponse> responseObject = new ResponseObject<JwtResponse>(true, "Signed in successfully",
                jwtResponse);
        return new ResponseEntity<ResponseObject<JwtResponse>>(responseObject, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }

        if (!signUpRequest.getPassword().equals(signUpRequest.getConfirmedPassword()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please confirm your password");

        // Create new user's account
        Utilisateur user = new Utilisateur(signUpRequest.getFirstname(), signUpRequest.getLastname(),
                signUpRequest.getUsername(), signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()));

        String strRole = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRole == null) {
            ResponseObject<UtilisateurResponse> responseObjectError = new ResponseObject<UtilisateurResponse>(false,
                    "Registration incomplete", null);
            return new ResponseEntity<ResponseObject<UtilisateurResponse>>(responseObjectError, HttpStatus.OK);
        } else {
            switch (strRole) {
                case "ROLE_ADMIN":
                    Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(adminRole);
                    user.setRoles(roles);
                    Admin admin = new Admin();
                    BeanUtils.copyProperties(user, admin);
                    Admin admin2 = adminRepository.save(admin);
                    UtilisateurResponse utilisateurResponse = new UtilisateurResponse();
                    BeanUtils.copyProperties(admin2, utilisateurResponse);
                    ResponseObject<UtilisateurResponse> responseObject = new ResponseObject<UtilisateurResponse>(true,
                            "User registered successfully!", utilisateurResponse);
                    return new ResponseEntity<ResponseObject<UtilisateurResponse>>(responseObject, HttpStatus.OK);
                case "ROLE_CANDIDAT":
                    Role candidateRole = roleRepository.findByName(RoleName.ROLE_CANDIDAT)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(candidateRole);
                    user.setRoles(roles);
                    Candidate canidate = new Candidate();
                    BeanUtils.copyProperties(user, canidate);
                    Candidate canidate2 = candidateRepository.save(canidate);
                    UtilisateurResponse utilisateurResponse2 = new UtilisateurResponse();
                    BeanUtils.copyProperties(canidate2, utilisateurResponse2);
                    ResponseObject<UtilisateurResponse> responseObject2 = new ResponseObject<UtilisateurResponse>(true,
                            "User registered successfully!", utilisateurResponse2);
                    return new ResponseEntity<ResponseObject<UtilisateurResponse>>(responseObject2, HttpStatus.OK);
                default:
                    ResponseObject<UtilisateurResponse> responseObjectError = new ResponseObject<UtilisateurResponse>(
                            false,
                            "Role is incorrect", null);
                    return new ResponseEntity<ResponseObject<UtilisateurResponse>>(responseObjectError, HttpStatus.OK);
            }
        }
    }

    @GetMapping(value = "/logout")
    public ResponseEntity<ResponseObject<Void>> Logout(HttpServletResponse response, HttpServletRequest request) {

        Object userPrincipal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetailsImpl) userPrincipal).getUsername();

        Utilisateur user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("user nor found"));

        // Emptying the security context holder
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        ResponseObject<Void> responseObject = new ResponseObject<Void>(true, "Logged out successfully", null);
        return new ResponseEntity<ResponseObject<Void>>(responseObject, HttpStatus.OK);
    }

    @GetMapping("/me")
    private ResponseEntity<Utilisateur> getMe() throws CredentialException {

        Object userPrincipal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetailsImpl) userPrincipal).getUsername();
        Utilisateur user = this.userService.getUtilisateur(username);
        if (user == null)
            throw new CredentialException("Invalid user");

        return new ResponseEntity<Utilisateur>(user, HttpStatus.OK);
    }
}