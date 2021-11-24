package com.example.demo.dto.requests;

import com.example.demo.enumerations.Gender;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UtilisateurRequest {
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
