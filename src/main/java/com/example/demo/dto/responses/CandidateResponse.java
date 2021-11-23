package com.example.demo.dto.responses;

import com.example.demo.enumerations.ActivityArea;
import com.example.demo.enumerations.Poste;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CandidateResponse extends UtilisateurResponse{
    private Poste poste;
    private ActivityArea activityArea;
}
