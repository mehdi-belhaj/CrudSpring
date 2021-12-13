
package com.example.demo.dto;

import java.time.LocalDate;

import com.example.demo.enumerations.ActivityArea;
import com.example.demo.enumerations.Gender;
import com.example.demo.enumerations.Poste;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CandidateDto {
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
    private Poste poste;
    private ActivityArea activityArea;





}
