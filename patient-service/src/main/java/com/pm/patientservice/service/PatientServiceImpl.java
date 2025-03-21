package com.pm.patientservice.service;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.mapper.PatientMapper;
import com.pm.patientservice.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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

    @Override
    public PatientResponseDTO updatePatient(UUID id, PatientRequestDTO patientRequestDTO) {
        var patient = patientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Patient not found"));


        if (patientRepository.existsByEmailAndIdNot(patientRequestDTO.email(), patient.getId())) {
            throw new IllegalArgumentException("Email is used by another patient");
        }

        var updatedPatient = PatientMapper.toEntity(patientRequestDTO);

        updatedPatient.setRegisteredDate(patient.getRegisteredDate());
        updatedPatient.setId(id);

        return PatientMapper.toDto(patientRepository.save(updatedPatient));
    }

    @Override
    public void deletePatient(UUID id) {
        patientRepository.deleteById(id);
    }
}
