package com.example.demo.services;

import com.example.demo.dto.AdminDto;
import com.example.demo.dto.requests.SignupRequest;
import com.example.demo.dto.responses.MessageResponse;
import com.example.demo.entities.Admin;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;



public interface AdminService {

    AdminDto createAdmin(AdminDto adminDto);

    AdminDto getAdmin (String email);

    //AdminDto getAdminById (Long id);

    AdminDto getAdminByUsername (String username);

    AdminDto updateAdmin(String username, AdminDto adminDto);

    void deleteAdmin(String username);

    List<AdminDto> getAllAdmins();


}

 /*MessageResponse createAdmin(SignupRequest AdminRequest);

    //Optional<Admin> updateAdmin(Long id, SignupRequest AdminRequest);

    //void deleteAdmin(Long id);

    //Admin getAdminById(Long id);

    //List<Admin> getAllAdmin();*/