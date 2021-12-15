package com.example.demo.dto.requests;

import java.time.LocalDate;

import com.example.demo.enumerations.ActivityArea;
import com.example.demo.enumerations.Gender;
import com.example.demo.enumerations.Poste;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CandidateRequest {
    private Poste poste;
    private ActivityArea activityArea;
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
