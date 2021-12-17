package com.example.demo.dto.responses;

import com.example.demo.enumerations.Gender;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String email;
    private String firstname;
    private String lastname;
    private String role;
    private LocalDate dateOfBirth;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String phone;
    private String address;

    public JwtResponse(String token, Long id, String username, String firstname, String lastname, String email,
            String role) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public JwtResponse(String token, Long id, String username, String firstname, String lastname, String email,
            String role, LocalDate dateOfBirth, Gender gender, String phone, String address) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.role = role;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String Token) {
        this.token = Token;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
