package com.example.demo.config.services;

import com.example.demo.dao.UserRepository;
import com.example.demo.entities.Utilisateur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String UsernameOrEmail) throws UsernameNotFoundException {
        Utilisateur user = userRepository.findByUsernameOrEmail(UsernameOrEmail, UsernameOrEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + UsernameOrEmail));

        return UserDetailsImpl.build(user);
    }

}
