package com.example.demo.entities;

import com.example.demo.enumerations.ActivityArea;
import com.example.demo.enumerations.Poste;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@DiscriminatorValue(value = "Candidate")
public class Candidate extends Utilisateur {
    private Poste poste;
    private ActivityArea activityArea;
}
