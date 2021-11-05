package com.example.ecf.service;

import com.example.ecf.model.Patient;
import com.example.ecf.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    public void createPatient(Patient patient) {
        //Convert Birthdate to Age
        patient.setAge(LocalDate.now().getYear() - patient.getBirthdate().getYear());
        patientRepository.save(patient);
    }

    public void updatePatient(Patient patient) {
        Patient patientSelected = patientRepository.findById(patient.getId()).orElseThrow(() -> new RuntimeException("Patient not found"));
        patientSelected.setLastName(patient.getLastName());
        patientSelected.setFirstName(patient.getFirstName());
        patientSelected.setBirthdate(patient.getBirthdate());
        patientSelected.setAddress(patient.getAddress());
        patientSelected.setGender(patient.getGender());
        patientRepository.save(patientSelected);
    }

    public void deletePatient(Patient patient) {
        patientRepository.delete(patient);
    }

    public Optional<Patient> findPatientById(Long id) {
        return patientRepository.findById(id);
    }

    public List<Patient> findAllPatients() {
        return patientRepository.findAll();
    }

    public Integer findAgeOfPatient(Long id) {
        return patientRepository.findById(id).get().getAge();
    }

    public List<Patient> findAllPatientsWithSameName(String name) {
        return patientRepository.findAll().stream()
                .filter(patient -> patient.getLastName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

}
