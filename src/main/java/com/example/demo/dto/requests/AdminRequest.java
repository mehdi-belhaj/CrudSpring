package com.example.demo.dto.requests;

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

public class AdminRequest {
    private String organization;
    private String firstname;
    private String lastname;
    private String email;
    private String picture;
    private String username;
    private String password;
    private LocalDate dateOfBirth;
    private Gender gender;
    private String phone;
    private String address;
    private boolean state;
}
