package com.example.demo.entities;

import com.example.demo.enumerations.ActivityArea;
import com.example.demo.enumerations.Poste;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@DiscriminatorValue(value = "Candidate")
public class Candidate extends Utilisateur {
    @Enumerated(EnumType.STRING)
    private Poste poste;
    @Enumerated(EnumType.STRING)
    private ActivityArea activityArea;

    public Candidate(String firstname, String lastname, String username,
            String email, String password, LocalDate dateOfBirth, String phone, Poste poste,
            ActivityArea activityArea) {
        super(firstname, lastname, username, email, password, dateOfBirth, phone);
        this.poste = poste;
        this.activityArea = activityArea;

    }

}
