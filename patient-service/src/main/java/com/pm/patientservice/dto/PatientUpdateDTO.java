package com.pm.patientservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record PatientUpdateDTO(
        @NotBlank(message = "Name is required")
        @NotEmpty(message = "Name is required")
        String name,

        @NotBlank(message = "Email is required")
        @NotEmpty(message = "Email is required")
        @Email(message = "Email is not valid")
        String email,

        @NotBlank(message = "Address is required")
        @NotEmpty(message = "Address is required")
        String address,

        @NotBlank(message = "Date of Birth is required")
        @NotEmpty(message = "Date of Birth is required")
        String dateOfBirth
) {
}
