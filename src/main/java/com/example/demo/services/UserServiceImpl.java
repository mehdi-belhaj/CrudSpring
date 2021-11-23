package com.example.demo.services;

import com.example.demo.dao.UserRepository;
import com.example.demo.entities.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements  UserService{
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder encoder;


    @Override
    public void saveUser(Utilisateur user) {

    }

    @Override
    public boolean isUserAlreadyPresent(Utilisateur user) {
        return false;
    }

    @Override
    public boolean changePassword(String email, String password) {
        boolean changed = false;
        Utilisateur user = userRepository.findUserByEmail(email);
        System.out.println("Origin : " + user);
        if(password != null){
            changed = true;
        }

        user.setPassword(encoder.encode(password));
        System.out.println("changed : " + user);
        userRepository.save(user);
        return changed;
    }

}
