package com.pm.patientservice.mapper;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.model.Patient;

import java.util.Date;

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
                .address(dto.address())
                .email(dto.email())
                .dateOfBirth(dto.dateOfBirth())
                .name(dto.name())
                .build();
    }
}
