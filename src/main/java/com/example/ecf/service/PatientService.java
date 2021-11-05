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
        patientRepository.save(patient);
        System.out.println("Patient Created");
    }

    public void updatePatient(Patient patient) {
        Patient patientSelected = patientRepository.findById(patient.getId()).orElseThrow(() -> new RuntimeException("Patient not found"));
        patientSelected.setLastName(patient.getLastName());
        patientSelected.setFirstName(patient.getFirstName());
        patientSelected.setBirthdate(patient.getBirthdate());
        patientSelected.setAddress(patient.getAddress());
        patientSelected.setGender(patient.getGender());
        patientRepository.save(patientSelected);
        System.out.println("Patient updated");
    }

    public void deletePatient(Patient patient) {
        patientRepository.delete(patient);
        System.out.println("Patient deleted");
    }

    public Optional<Patient> findPatientById(Long id) {
        return patientRepository.findById(id);
    }

    public List<Patient> findAllPatients() {
        return patientRepository.findAll();
    }

    public Integer findAgeOfPatient(Long id) {
        Integer year = patientRepository.findById(id).get().getBirthdate().getYear();
        Integer actualYear = LocalDate.now().getYear();
        Integer age = actualYear - year;
        return age;
    }

    public List<Patient> findAllPatientsWithSameName(String name) {
        return patientRepository.findAll().stream()
                .filter(patient -> patient.getLastName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }
}
