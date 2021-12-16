package com.example.demo.dto.responses;

import java.time.LocalDate;

import com.example.demo.enumerations.Gender;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class AdminResponse {
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String picture;
    private LocalDate dateOfBirth;
    private Gender gender;
    private String phone;
    private String address;
    private String organization;
    private String password;
    private String role;
}
