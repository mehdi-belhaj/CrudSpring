package com.example.demo.services;

import com.example.demo.entities.Candidate;
import com.example.demo.entities.Utilisateur;

import java.util.List;

public interface UserService {

    public void saveUser(Utilisateur user);

    public boolean isUserAlreadyPresent(Utilisateur user);
    public boolean changePassword(String email, String password);

}
