package com.matchdayai.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRequest {

    @NotBlank(message="Name is mandatory")
    private String name;

    @Email(message = "Email should be valid")
    @NotBlank(message="Email is mandatory")
    private String email;

    @NotBlank(message="Password is mandatory")
    @jakarta.validation.constraints.Size(min = 8, message = "Password must be at least 8 characters")
    private String password;

    private String role;

    private String phone;

    private String preferredLanguage;


}
