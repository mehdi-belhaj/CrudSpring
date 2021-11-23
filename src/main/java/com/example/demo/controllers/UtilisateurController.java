package com.example.demo.controllers;

import com.example.demo.dto.UtilisateurDto;
import com.example.demo.dto.requests.UtilisateurRequest;
import com.example.demo.dto.responses.UtilisateurResponse;
import com.example.demo.entities.Utilisateur;
import com.example.demo.services.UtilisateurService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/utilisateur")
@RestController
public class UtilisateurController {
    private final UtilisateurService service;

    public UtilisateurController(UtilisateurService service) {
        this.service = service;
    }

    @PostMapping("/addutilisateur")
    public Utilisateur addUtilisateur(@RequestBody Utilisateur utilisateur)
    {
        return service.saveUtilisateur(utilisateur);
    }
    //@PostMapping("/addutilisateurs")
    //  public List<Utilisateur> addUtilisateurs(@RequestBody List<Utilisateur> utilisateurs)
    //{

    //  return service.saveUtilisateurs(utilisateurs);
    //}

    //@GetMapping("/utilisateurs")
    //public List<Utilisateur> findAllUtilisateurs(){
      //  return service.getUtilisateurs();
    //}

    @GetMapping("/utilisateurs/{id}")
    public Utilisateur findUtilisateurById(@PathVariable Long id){
        return service.getUtilisateursById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UtilisateurResponse> updateUtilisateur(@PathVariable String id, @RequestBody UtilisateurRequest utilisateurRequest){
        UtilisateurDto utilisateurDto = new UtilisateurDto();

        BeanUtils.copyProperties(utilisateurRequest, utilisateurDto);

        UtilisateurDto updateUtilisateur = service.updateUtilisateur(id, utilisateurDto);

        UtilisateurResponse utilisateurResponse = new UtilisateurResponse();

        BeanUtils.copyProperties(updateUtilisateur, utilisateurResponse);

        return new ResponseEntity<UtilisateurResponse>(utilisateurResponse, HttpStatus.ACCEPTED);
    }
    //  @DeleteMapping("/delete/{id}")
    //public String deleteUtilisateur(@PathVariable Long id)
    //{
    //  return service.deleteUtilisateur(id);

    //}



}
