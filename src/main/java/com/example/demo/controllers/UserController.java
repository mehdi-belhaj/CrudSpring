package com.example.demo.controllers;

import javax.validation.Valid;

import com.example.demo.dto.requests.ChangePasswordRequest;
import com.example.demo.dto.responses.MessageResponse;
import com.example.demo.dto.responses.UtilisateurResponse;
import com.example.demo.services.UserService;
import com.example.demo.utils.ResponseObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "${api.endpoint}/user")
public class UserController {
    @Autowired
    private UserService userService;

    /* ---------- get user ------------ */

    @GetMapping(path = "/{username}")
    public ResponseEntity<ResponseObject<UtilisateurResponse>> getUser(
            @PathVariable(value = "username") String username) {
        ResponseObject<UtilisateurResponse> responseObject = new ResponseObject<UtilisateurResponse>(true,
                String.format("User data"), userService.getUser(username));
        return new ResponseEntity<ResponseObject<UtilisateurResponse>>(responseObject, HttpStatus.OK);
    }

    /* ---------- change user's password ------------ */

    @PutMapping(path = "/changePassword/{username}")
    public ResponseEntity<ResponseObject<MessageResponse>> changePassword(@PathVariable String username,
            @RequestBody @Valid ChangePasswordRequest passwordBody) {
        ResponseObject<MessageResponse> responseObject = new ResponseObject<MessageResponse>(true,
                "User's password changed", userService.changePassword(username, passwordBody));
        return new ResponseEntity<ResponseObject<MessageResponse>>(responseObject, HttpStatus.OK);
    }

}
