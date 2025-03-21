package com.pm.patientservice.mapper;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.dto.PatientUpdateDTO;
import com.pm.patientservice.model.Patient;

import java.time.LocalDate;

public class PatientMapper {
    public static PatientResponseDTO toDto(Patient patient) {
        return PatientResponseDTO.builder()
                .id(patient.getId().toString())
                .name(patient.getName())
                .email(patient.getEmail())
                .address(patient.getAddress())
                .dateOfBirth(patient.getDateOfBirth().toString())
                .build();
    }

    public static Patient toEntity(PatientRequestDTO dto) {
        return Patient.builder()
                .name(dto.name())
                .email(dto.email())
                .address(dto.address())
                .dateOfBirth(LocalDate.parse(dto.dateOfBirth()))
                .registeredDate(dto.registeredDate() != null ? LocalDate.parse(dto.registeredDate()) : null)
                .build();
    }

    public static Patient toEntity(PatientUpdateDTO dto) {
        return Patient.builder()
                .name(dto.name())
                .email(dto.email())
                .address(dto.address())
                .dateOfBirth(LocalDate.parse(dto.dateOfBirth()))
                .build();
    }
}
