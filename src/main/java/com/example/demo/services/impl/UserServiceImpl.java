package com.example.demo.services.impl;

import com.example.demo.dao.UserRepository;
import com.example.demo.dto.UtilisateurDto;
import com.example.demo.dto.requests.ChangePasswordRequest;
import com.example.demo.dto.responses.MessageResponse;
import com.example.demo.dto.responses.UtilisateurResponse;
import com.example.demo.entities.Utilisateur;
import com.example.demo.exceptions.EntityNotFoundException;
import com.example.demo.services.UserService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder encoder;

    @Override
    public MessageResponse changePassword(String username, ChangePasswordRequest passwordBody)
            throws EntityNotFoundException {
        Utilisateur existantUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("user nor found"));
        String newPassword = this.encoder.encode(passwordBody.getNewPassword());

        if (this.encoder.matches(passwordBody.getOldPassword(), existantUser.getPassword())) {
            existantUser.setPassword(newPassword);
            userRepository.save(existantUser);
            return new MessageResponse("password updated successfully");
        } else {
            throw new EntityNotFoundException("Old Password Is Incorrect");
        }
    }

    @Override
    public UtilisateurResponse getUser(String username) throws EntityNotFoundException {
        Utilisateur user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("user nor found"));
        UtilisateurDto UtilisatuerDto = new UtilisateurDto();

        BeanUtils.copyProperties(user, UtilisatuerDto);
        UtilisateurResponse utilisateurResponse = new UtilisateurResponse();
        BeanUtils.copyProperties(UtilisatuerDto, utilisateurResponse);

        return utilisateurResponse;
    }

    @Override
    public Utilisateur getUtilisateur(String username) {

        return userRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("user nor found"));
    }

}
