package com.example.demo.dto.responses;

import com.example.demo.enumerations.Gender;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UtilisateurResponse {

    @NotNull(message="veuillez remplir ce champ !")
    @Size(min = 3)
    private String firstname;
    @NotNull(message="veuillez remplir ce champ !")
    @Size(min=2, message="le champ doit avoir au moins 3 Caracteres !")
    private String lastname;
    private String username;
    @NotNull(message="veuillez remplir ce champ !")
    @Email
    private String email;
    @NotNull(message="veuillez remplir ce champ !")
    @Size(min=6, max= 14)
    private String password;
    private String picture;
    private LocalDate dateOfBirth;
    private Gender gender;
    private String phone;
    private String address;
    private boolean state;
}