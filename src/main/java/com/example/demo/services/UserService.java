package com.example.demo.services;

import com.example.demo.entities.Utilisateur;

public interface UserService {
    public void saveUser(Utilisateur user);

    public boolean isUserAlreadyPresent(Utilisateur user);
    public boolean changePassword(String email, String password);

}
