package com.example.demo.services.impl;

import com.example.demo.dao.AdminRepository;
import com.example.demo.dto.AdminDto;
import com.example.demo.dto.CandidateDto;
import com.example.demo.entities.Admin;
import com.example.demo.entities.Candidate;
import com.example.demo.exceptions.EntityNotFoundException;
import com.example.demo.services.AdminService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Component

public class AdminServiceImp implements AdminService {

    @Autowired
    AdminRepository adminRepository;

    @Override
    public AdminDto createAdmin(AdminDto adminDto) {
        Admin checkAdmin = adminRepository.findByUsername(adminDto.getUsername());

        if(checkAdmin != null){
            throw new RuntimeException(String.valueOf(HttpStatus.FOUND.value()));
        }
        Admin adminEntity = new Admin();
        BeanUtils.copyProperties(adminDto, adminEntity);
        Admin newAdmin = adminRepository.save(adminEntity);
        AdminDto adminDto1 = new AdminDto();
        BeanUtils.copyProperties(newAdmin, adminDto1);
        return  adminDto1;

    }


    @Override
    public AdminDto getAdmin(String email) {

        return null;
    }


    @Override
    public AdminDto getAdminByUsername(String username) {
        Admin adminEntity = adminRepository.findByUsername(username);

        if(adminEntity == null){
            throw new UsernameNotFoundException(username);
        }
        AdminDto adminDto = new AdminDto();
        BeanUtils.copyProperties(adminEntity, adminDto);
        return adminDto;
    }


    @Override
    public AdminDto updateAdmin(String username, AdminDto adminDto) {
        Admin adminEntity = adminRepository.findByUsername(username);

        if(adminEntity == null) throw new UsernameNotFoundException(username);

        if(adminDto.getFirstname() != null) adminEntity.setFirstname(adminDto.getFirstname());
        if(adminDto.getLastname() != null) adminEntity.setLastname(adminDto.getLastname());
        if(adminDto.getEmail() != null) adminEntity.setEmail(adminDto.getEmail());
        if(adminDto.getPassword() != null) adminEntity.setPassword(adminDto.getPassword());
        if(adminDto.getAddress() != null) adminEntity.setAddress(adminDto.getAddress());
        if(adminDto.getDateOfBirth() != null) adminEntity.setDateOfBirth(adminDto.getDateOfBirth());
        if(adminDto.getGender() != null) adminEntity.setGender(adminDto.getGender());
        if(adminDto.getPhone() != null) adminEntity.setPhone(adminDto.getPhone());
        if(adminDto.getPicture() != null) adminEntity.setPicture(adminDto.getPicture());
        if(adminDto.getOrganization()!=null) adminEntity.setOrganization(adminDto.getOrganization());

        Admin adminUpdate = adminRepository.save(adminEntity);

        AdminDto adminDto1 = new AdminDto();

        BeanUtils.copyProperties(adminUpdate,adminDto1);

        return adminDto1;

    }

    @Override
    public void deleteAdmin(String username) {
        Admin adminEntity = adminRepository.findByUsername(username);

        if (adminEntity == null){
            throw new EntityNotFoundException("This account does not exist");
        }

        adminRepository.delete(adminEntity);

    }


    @Override
    public List<AdminDto> getAllAdmins() {
        List<Admin> listAdmin = adminRepository.findAll();

        List<AdminDto> listAdminDto = new ArrayList<>();

        for ( Admin a : listAdmin){
            AdminDto adminDto = new AdminDto();

            BeanUtils.copyProperties(a,adminDto);

            listAdminDto.add(adminDto);
        }
        return listAdminDto;
    }


    /*
    @Override
    public MessageResponse createAdmin(SignupRequest AdminRequest) {
        Admin newAdmin = new Admin();
        newAdmin.setFirstName(AdminRequest.getFirstname());
        newAdmin.setLastName(AdminRequest.getLastname());
        newAdmin.setUsername(AdminRequest.getUsername());
        newAdmin.setEmail(AdminRequest.getEmail());
        //newAdmin.setOrganization(AdminRequest.getRole());

        adminRepository.save(newAdmin);
        return new MessageResponse("New Admin created successfully");
    }

    @Override
    public Optional<Admin> updateAdmin(Long id, SignupRequest AdminRequest) throws ResourceNotFoundException {
        Optional<Admin> admin = adminRepository.findById(id);
        if (admin.isEmpty()){
            throw new ResourceNotFoundException("Admin");
        }
        else
            admin.get().setFirstName(AdminRequest.getFirstname())    ;
        admin.get().setLastName(AdminRequest.getLastname());
        admin.get().setUsername(AdminRequest.getUsername());
        admin.get().setEmail(AdminRequest.getEmail());
        //admin.get().setOrganization(AdminRequest.getRole());

        adminRepository.save(admin.get());
        return admin;
    }

    @Override
    public void deleteAdmin(Long id) throws ResourceNotFoundException {
        if (adminRepository.getById(id).getId().equals(id)){
            adminRepository.deleteById(id);
        }
        else throw new ResourceNotFoundException("admin");

    }

    @Override
    public Admin getAdminById(Long id) throws ResourceNotFoundException{
        return adminRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("admin"));
    }

    @Override
    public List<Admin> getAllAdmin() {
        return adminRepository.findAll();

    }

     */

}


