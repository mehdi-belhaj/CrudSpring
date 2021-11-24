package com.example.demo.dto.responses;

import com.example.demo.enumerations.Gender;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UtilisateurResponse {
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String picture;
    private LocalDate dateOfBirth;
    private Gender gender;
    private String phone;
    private String address;
}
