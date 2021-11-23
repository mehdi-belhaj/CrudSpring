package com.example.demo.dto;

import com.example.demo.enumerations.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UtilisatuerDto implements Serializable {
    @NotNull(message="le champ prénom ne doit pas être null !")
    @Size(min = 3)
    private String firstname;
    @NotNull(message="le champ nom ne doit pas être null !")
    @Size(min=2, message="le champ doit avoir au moins 3 Caracteres !")
    private String lastname;
    private String username;
    @NotNull(message="le champ email ne doit pas être null !")
    @Email
    private String email;
    @NotNull(message="le champ mot de passe ne doit pas être null !")
    @Size(min=6, max= 14)
    private String password;
    private String picture;
    private LocalDate dateOfBirth;
    private Gender gender;
    private String phone;
    private String address;
    private boolean state;
}
