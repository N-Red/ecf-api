package com.example.ecf.controller;

import com.example.ecf.model.Patient;
import com.example.ecf.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PatientController {

    @Autowired
    private PatientService patientService;

    //CREATE
    //http://localhost:8080/createPatient
    @PostMapping("/createPatient")
    public void createPatient(@RequestBody Patient patient) {
        patientService.createPatient(patient);
    }

    //READ
    //http://localhost:8080/readPatient=<id>
    @GetMapping("/readPatient={id}")
    public Optional<Patient> readPatient(@PathVariable Long id) {
        return patientService.findPatientById(id);
    }

    //UPDATE
    //http://localhost:8080/updatePatient
    @PutMapping("/updatePatient")
    public void updatePatient(@RequestBody Patient patient) {
        patientService.updatePatient(patient);
    }

    //DELETE
    //http://localhost:8080/deletePatient=<id>
    @DeleteMapping("/deletePatient={id}")
    public void deletePatient(@PathVariable Long id) {
        patientService.deletePatient(patientService.findPatientById(id).get());
    }

    //AGE OF PATIENT
    // http://localhost:8080/ageOfPatient=<id>
    @GetMapping("/ageOfPatient={id}")
    public Integer getPatientAge(@PathVariable Long id) {
        return patientService.findAgeOfPatient(id);
    }

    //ALL PATIENTS
    // http://localhost:8080/patients
    @GetMapping("/allPatients")
    public List<Patient> getPatients() {
        List<Patient> patients = patientService.findAllPatients();
        return patients;
    }

    //LIST OF PATIENT WITH SAME NAME
    // http://localhost:8080/patientsWithSameName=<name>
    @GetMapping("/patientsWithSameName={name}")
    public List<Patient> getListOfPatientsWithSameName(@PathVariable String name) {
        return patientService.findAllPatientsWithSameName(name);
    }
}
