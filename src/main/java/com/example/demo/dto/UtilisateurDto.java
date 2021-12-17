package com.example.demo.dto;

import java.io.Serializable;
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
public class UtilisateurDto implements Serializable {
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String password;
    private String picture;
    private LocalDate dateOfBirth;
    private Gender gender;
    private String phone;
    private String address;
    private boolean state;
}
