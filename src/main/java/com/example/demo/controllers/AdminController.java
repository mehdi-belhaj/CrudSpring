package com.example.demo.controllers;

import com.example.demo.dao.AdminRepository;
import com.example.demo.dto.AdminDto;
import com.example.demo.dto.requests.AdminRequest;
import com.example.demo.dto.responses.AdminResponse;
import com.example.demo.dto.responses.enums.ErrorMessage;
import com.example.demo.entities.Admin;
import com.example.demo.exceptions.AdminException;
import com.example.demo.services.AdminService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping(value = "/api")

public class AdminController {
    @Autowired
    AdminService adminService;

    //create
    @PostMapping("/admins")
    public ResponseEntity<AdminResponse> createAdmin(@RequestBody @Valid AdminRequest adminRequest) throws Exception {
        if (adminRequest.getFirstname().isEmpty())
            throw new AdminException(ErrorMessage.MISSING_REQUIRED_FIELD.getErrorMessage());

        AdminDto adminDto = new AdminDto();

        AdminResponse adminResponse = new AdminResponse();

        BeanUtils.copyProperties(adminRequest, adminDto);

        AdminDto CreateAdminDto = new AdminDto();

        if ((CreateAdminDto = adminService.createAdmin(adminDto)) != null) {
            System.out.println("CreateAdminDto");

        } else {
            return new ResponseEntity<>(HttpStatus.FOUND);
        }

        BeanUtils.copyProperties(CreateAdminDto, adminResponse);
        return new ResponseEntity<AdminResponse>(adminResponse, HttpStatus.CREATED);

    }

    //get
    @GetMapping("/admins/{username}")
    public AdminResponse getAdminByUsername(@PathVariable String username) {

        AdminDto adminDto = new AdminDto();

        AdminResponse adminResponse = new AdminResponse();

        try {
            adminDto = adminService.getAdminByUsername(username);
            BeanUtils.copyProperties(adminDto, adminResponse);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        return adminResponse;

    }

    //get
    @GetMapping("/admins/{email}")
    public AdminResponse getAdmin(@PathVariable String email) {

        AdminDto adminDto1 = new AdminDto();

        AdminResponse adminResponse = new AdminResponse();

        try {
            adminDto1 = adminService.getAdmin(email);
            BeanUtils.copyProperties(adminDto1, adminResponse);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        return adminResponse;
    }


    //update
    @PutMapping("/admins/{id}")
    public ResponseEntity<AdminResponse> updateAdmin(@PathVariable String id, @Valid @RequestBody AdminRequest adminRequest) {

        AdminDto adminDto = new AdminDto();

        BeanUtils.copyProperties(adminRequest, adminDto);

        AdminDto updateAdmin = adminService.updateAdmin(id, adminDto);

        AdminResponse adminResponse = new AdminResponse();

        BeanUtils.copyProperties(updateAdmin, adminResponse);

        return new ResponseEntity<AdminResponse>(adminResponse, HttpStatus.ACCEPTED);
    }

    //delete
    @DeleteMapping("/admins/{username}")
    public ResponseEntity<Map<String, Boolean>> deleteAdmin(@PathVariable String username) {

        adminService.deleteAdmin(username);

        Map<String, Boolean> response = new HashMap<>();

        response.put("deleted", Boolean.TRUE);

        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);

    }


}







    /*
    @GetMapping("/all")
    public ResponseEntity<List<Admin>> getAllAdmin () {
        List<Admin> admins = adminService.getAllAdmin();
        return new ResponseEntity<>(admins, HttpStatus.OK);
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<Admin> getAdminById (@PathVariable("id") Long id) {
        Admin admin = adminService.getAdminById(id);
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<MessageResponse> addAdmin(@RequestBody SignupRequest admin) {
        MessageResponse newAdmin = adminService.createAdmin(admin);
        return new ResponseEntity<>(newAdmin, HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<MessageResponse> updateAdmin( @PathVariable Long id, @RequestBody SignupRequest admin) {
        Optional<Admin> updateAdmin = adminService.updateAdmin(id, admin);
        return new ResponseEntity(updateAdmin, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAdmin(@PathVariable("id") Long id) {
        adminService.deleteAdmin(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    */

