
 package com.example.demo.dto;

import com.example.demo.enumerations.ActivityArea;
import com.example.demo.enumerations.Poste;
import lombok.*;

 @Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CandidateDto extends UtilisatuerDto{
    private Poste poste;
    private ActivityArea activityArea;
}
