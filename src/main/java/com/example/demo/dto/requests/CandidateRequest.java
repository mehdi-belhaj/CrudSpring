package com.example.demo.dto.requests;

import com.example.demo.enumerations.ActivityArea;
import com.example.demo.enumerations.Poste;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CandidateRequest extends UtilisateurRequest {
    private Poste poste;
    private ActivityArea activityArea;
}
