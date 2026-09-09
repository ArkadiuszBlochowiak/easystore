package com.easybytes.easystore.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequestDto {
    @NotBlank(message = "Name is required")
    @Size(min = 5, max = 30, message = "The length of the name should be between 5 and 30 characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email address must be a valid value")
    private String email;

    @NotBlank(message = "Mobile number is required")
    @Pattern(regexp = "^\\d{9}$", message = "Mobile number must be 9 digits")
    private String mobileNumber;

    @NotBlank(message = "Password is required")
    @Size(min = 5, max = 20, message = "The length of the password should be between 5 and 20 characters")
    private String password;
}
