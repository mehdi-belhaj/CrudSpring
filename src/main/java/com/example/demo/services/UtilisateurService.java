package com.example.demo.services;


import com.example.demo.dao.UtilisateurRepository;
import com.example.demo.dto.UtilisateurDto;
import com.example.demo.entities.Utilisateur;
import com.example.demo.exceptions.ResourceNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurService {
    @Autowired
    private UtilisateurRepository repository;
    public Utilisateur saveUtilisateur(Utilisateur utilisateur){

        return repository.save(utilisateur);
    }

    //public List<Utilisateur> saveUtilisateurs(List<Utilisateur> utilisateurs){

    //  return repository.saveAll(utilisateurs);
    //}

    public List<Utilisateur> getUtilisateurs(){

        return repository.findAll();
    }
    public Utilisateur getUtilisateursById(Long id){

        return repository.findById(id).orElseThrow(()->new ResourceNotFoundException("utilisateur","id",id));
    }
   // public String deleteUtilisateur(Long id){

     //   repository.deleteById(id);
       // return "Utilisateur est bien supprimé !!  "+id;
    //}
     public UtilisateurDto getCandidateByUsername(String username) {
         Utilisateur utilisateur = repository.findByUsername(username);

         if(utilisateur == null){
             throw new ResourceNotFoundException("utilisateur","id",username);
         }
         UtilisateurDto utilisateurDto = new UtilisateurDto();
         BeanUtils.copyProperties(utilisateur, utilisateurDto);
         return utilisateurDto;
     }
     public UtilisateurDto updateUtilisateur(String username, UtilisateurDto utilisateurDto) {
         Utilisateur existingUtilisateur = repository.findByUsername(username);
         if(existingUtilisateur == null) throw new ResourceNotFoundException("utilisateur","id",username);
         existingUtilisateur.setFirstname(utilisateurDto.getFirstname());
         existingUtilisateur.setLastname(utilisateurDto.getLastname());
         existingUtilisateur.setUsername(utilisateurDto.getUsername());
         existingUtilisateur.setAddress(utilisateurDto.getAddress());
         existingUtilisateur.setEmail(utilisateurDto.getEmail());
         existingUtilisateur.setGender(utilisateurDto.getGender());
         existingUtilisateur.setPassword(utilisateurDto.getPassword());
         existingUtilisateur.setPhone(utilisateurDto.getPhone());
         existingUtilisateur.setPicture(utilisateurDto.getPicture());
         existingUtilisateur.setDateOfBirth(utilisateurDto.getDateOfBirth());
         existingUtilisateur.setGender(utilisateurDto.getGender());
         existingUtilisateur.setState(utilisateurDto.isState());
         Utilisateur utilisateur =repository.save(existingUtilisateur);
         UtilisateurDto utilisateurDto1 = new UtilisateurDto();
         BeanUtils.copyProperties(utilisateur,utilisateurDto1);
         return utilisateurDto1;
     }
}
