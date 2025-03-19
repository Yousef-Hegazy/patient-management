package com.pm.patientservice.service;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.mapper.PatientMapper;
import com.pm.patientservice.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    @Override
    public List<PatientResponseDTO> getPatients() {
        return patientRepository.findAll()
                .stream()
                .map(PatientMapper::toDto)
                .toList();
    }

    @Override
    public PatientResponseDTO createPatient(PatientRequestDTO patient) {
        if (patientRepository.existsByEmail(patient.email())) {
            throw new IllegalArgumentException("Email is used by another patient");
        }
        return PatientMapper.toDto(patientRepository.save(PatientMapper.toEntity(patient)));
    }
}
