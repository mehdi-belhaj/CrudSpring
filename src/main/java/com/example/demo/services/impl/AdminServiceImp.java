package com.example.demo.services.impl;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.dao.AdminRepository;
import com.example.demo.dto.AdminDto;
import com.example.demo.entities.Admin;
import com.example.demo.exceptions.EntityNotFoundException;
import com.example.demo.services.AdminService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component

public class AdminServiceImp implements AdminService {

    @Autowired
    AdminRepository adminRepository;

    @Override
    public AdminDto createAdmin(AdminDto adminDto) {
        Admin checkAdmin = adminRepository.findByUsername(adminDto.getUsername()).get();

        if (checkAdmin != null) {
            throw new RuntimeException(String.valueOf(HttpStatus.FOUND.value()));
        }
        Admin adminEntity = new Admin();
        BeanUtils.copyProperties(adminDto, adminEntity);
        Admin newAdmin = adminRepository.save(adminEntity);
        AdminDto adminDto1 = new AdminDto();
        BeanUtils.copyProperties(newAdmin, adminDto1);
        return adminDto1;

    }

    @Override
    public AdminDto getAdminByEmail(String email) {

        Admin adminEntity = adminRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(" User not found !"));
        AdminDto adminDto = new AdminDto();
        BeanUtils.copyProperties(adminEntity, adminDto);
        return adminDto;
    }

    @Override
    public AdminDto getAdminByUsername(String username) {
        Admin adminEntity = adminRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(" User not found !"));
        AdminDto adminDto = new AdminDto();
        BeanUtils.copyProperties(adminEntity, adminDto);
        return adminDto;
    }

    @Override
    public AdminDto updateAdmin(Long id, AdminDto adminDto) {
        Admin adminEntity = adminRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(" User not found !"));

        if (adminDto.getFirstname() != null)
            adminEntity.setFirstname(adminDto.getFirstname());
        if (adminDto.getLastname() != null)
            adminEntity.setLastname(adminDto.getLastname());
        if (adminDto.getEmail() != null)
            adminEntity.setEmail(adminDto.getEmail());
        if (adminDto.getUsername() != null)
            adminEntity.setUsername(adminDto.getUsername());
        if (adminDto.getAddress() != null)
            adminEntity.setAddress(adminDto.getAddress());
        if (adminDto.getDateOfBirth() != null)
            adminEntity.setDateOfBirth(adminDto.getDateOfBirth());
        if (adminDto.getGender() != null)
            adminEntity.setGender(adminDto.getGender());
        if (adminDto.getPhone() != null)
            adminEntity.setPhone(adminDto.getPhone());
        if (adminDto.getPicture() != null)
            adminEntity.setPicture(adminDto.getPicture());
        if (adminDto.getOrganization() != null)
            adminEntity.setOrganization(adminDto.getOrganization());

        Admin adminUpdate = adminRepository.save(adminEntity);

        AdminDto adminDto1 = new AdminDto();

        BeanUtils.copyProperties(adminUpdate, adminDto1);

        return adminDto1;

    }

    @Override
    public void deleteAdmin(String username) {
        Admin adminEntity = adminRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(" User not found !"));

        adminRepository.delete(adminEntity);

    }

    @Override
    public List<AdminDto> getAllAdmins() {
        List<Admin> listAdmin = adminRepository.findAll();

        List<AdminDto> listAdminDto = new ArrayList<>();

        for (Admin a : listAdmin) {
            AdminDto adminDto = new AdminDto();

            BeanUtils.copyProperties(a, adminDto);

            listAdminDto.add(adminDto);
        }
        return listAdminDto;
    }

    @Override
    public AdminDto getAdminById(Long id) {
        Admin adminEntity = adminRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(" User not found !"));
        AdminDto adminDto = new AdminDto();
        BeanUtils.copyProperties(adminEntity, adminDto);
        return adminDto;
    }

}
