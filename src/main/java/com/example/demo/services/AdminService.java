package com.example.demo.services;

import java.util.List;

import com.example.demo.dto.AdminDto;

public interface AdminService {

    AdminDto createAdmin(AdminDto adminDto);

    AdminDto getAdminByEmail(String email);

    AdminDto getAdminById(Long id);

    AdminDto getAdminByUsername(String username);

    AdminDto updateAdmin(Long id, AdminDto adminDto);

    void deleteAdmin(String username);

    List<AdminDto> getAllAdmins();

}