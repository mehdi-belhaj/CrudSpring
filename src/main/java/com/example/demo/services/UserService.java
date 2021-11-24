package com.example.demo.services;

import com.example.demo.dto.requests.ChangePasswordRequest;
import com.example.demo.dto.responses.MessageResponse;
import com.example.demo.dto.responses.UtilisateurResponse;
import com.example.demo.entities.Utilisateur;
import com.example.demo.exceptions.EntityNotFoundException;

public interface UserService {

    public MessageResponse changePassword(String username, ChangePasswordRequest passwordBody)
            throws EntityNotFoundException;

    public UtilisateurResponse getUser(String username);

    public Utilisateur getUtilisateur(String username);
}
