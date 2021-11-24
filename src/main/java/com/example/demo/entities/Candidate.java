package com.example.demo.entities;

import com.example.demo.enumerations.ActivityArea;
import com.example.demo.enumerations.Poste;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
    // @Column(columnDefinition = "ENUM('PENDING', 'ACTIVE', 'INACTIVE')")
    @Enumerated(EnumType.STRING)
    private Poste poste;
    // @Column(columnDefinition = "ENUM('PENDING', 'ACTIVE', 'INACTIVE')")
    @Enumerated(EnumType.STRING)
    private ActivityArea activityArea;
}
