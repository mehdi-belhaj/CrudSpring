package com.example.demo.dto.responses;

import java.time.LocalDate;

import com.example.demo.enumerations.ActivityArea;
import com.example.demo.enumerations.Gender;
import com.example.demo.enumerations.Poste;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CandidateResponse {
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String picture;
    private LocalDate dateOfBirth;
    private Gender gender;
    private String phone;
    private String address;
    private Poste poste;
    private ActivityArea activityArea;
}
