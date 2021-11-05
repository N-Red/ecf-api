package com.example.ecf.service;

import com.example.ecf.model.Patient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PatientServiceTest {

    @Autowired
    private PatientService patientService;
    Patient patient = new Patient("lastName", "firstName", LocalDate.of(2000, 01, 01), "address", "Male");

    @Test
    void createPatientTest() {
        patientService.createPatient(patient);
        Assertions.assertEquals("lastName", patientService.findPatientById(patient.getId()).get().getLastName());
        patientService.deletePatient(patient);
    }

    @Test
    void updatePatientTest() {
        patientService.createPatient(patient);
        patient.setLastName("lastName updated");
        patientService.updatePatient(patient);
        Assertions.assertEquals("lastName updated", patientService.findPatientById(patient.getId()).get().getLastName());
        patientService.deletePatient(patient);
    }

    @Test
    void deletePatientTest() {
        patientService.createPatient(patient);
        Long id = patient.getId();
        patientService.deletePatient(patient);
        Assertions.assertFalse(patientService.findPatientById(id).isPresent());
    }

    @Test
    void findPatientByIdTest() {
        patientService.createPatient(patient);
        Assertions.assertEquals("lastName", patientService.findPatientById(patient.getId()).get().getLastName());
        patientService.deletePatient(patient);
    }

    @Test
    void findAllPatientsTest() {
        patientService.createPatient(patient);
        Assertions.assertTrue(patientService.findAllPatients().size() > 0);
        patientService.deletePatient(patient);
    }

    @Test
    void findAgeOfPatientTest() {
        patientService.createPatient(patient);
        Assertions.assertEquals(21, patientService.findAgeOfPatient(patient.getId()));
        patientService.deletePatient(patient);
    }

    @Test
    void findAllPatientsWithSameNameTest() {
        patientService.createPatient(patient);
        patientService.createPatient(new Patient("lastName", "second", LocalDate.of(2000, 01, 01), "address", "Female"));
        Patient secondPatient = patientService.findAllPatients().stream().filter(patient1 -> patient1.getFirstName().equals("second")).findFirst().get();
        patientService.findAllPatientsWithSameName("lastName");
        Assertions.assertTrue(patientService.findAllPatientsWithSameName("lastName").size() > 0);
        patientService.deletePatient(patient);
        patientService.deletePatient(secondPatient);

    }
}