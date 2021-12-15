package com.example.demo.dto.requests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChangePasswordRequest {
    @NotBlank
    @Size(min = 6, max = 40)
    String oldPassword;
    @NotBlank
    @Size(min = 6, max = 40)
    String newPassword;
}
