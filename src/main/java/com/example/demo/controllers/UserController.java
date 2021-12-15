package com.example.demo.controllers;

import com.example.demo.dto.AdminDto;
import com.example.demo.dto.CandidateDto;
import com.example.demo.dto.requests.ChangePasswordRequest;
import com.example.demo.dto.responses.AdminResponse;
import com.example.demo.dto.responses.CandidateResponse;
import com.example.demo.dto.responses.MessageResponse;
import com.example.demo.dto.responses.UtilisateurResponse;
import com.example.demo.services.AdminService;
import com.example.demo.services.UserService;
import com.example.demo.utils.ResponseObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "${api.endpoint}/user")
public class UserController {
    @Autowired
    private UserService userService;
    /**
     * get User by his username
     *
     * @param username
     * @return UtilisateurResponse
     */

    @GetMapping(path = "/{username}")
    public ResponseEntity<ResponseObject<UtilisateurResponse>> getUser(
            @PathVariable(value = "username") String username) {
        ResponseObject<UtilisateurResponse> responseObject = new ResponseObject<UtilisateurResponse>(true,
                String.format("User data"), userService.getUser(username));
        return new ResponseEntity<ResponseObject<UtilisateurResponse>>(responseObject, HttpStatus.OK);
    }


    /**
     * Change password
     *
     * @param username
     * @param passwordBody
     * @return MessageResponse
     */
    @PutMapping(path = "/changePassword/{username}")
    public ResponseEntity<ResponseObject<MessageResponse>> changePassword(@PathVariable String username,
                                                                          @RequestBody @Valid ChangePasswordRequest passwordBody) {
        ResponseObject<MessageResponse> responseObject = new ResponseObject<MessageResponse>(true,
                "User's password changed", userService.changePassword(username, passwordBody));
        return new ResponseEntity<ResponseObject<MessageResponse>>(responseObject, HttpStatus.OK);
    }



}
