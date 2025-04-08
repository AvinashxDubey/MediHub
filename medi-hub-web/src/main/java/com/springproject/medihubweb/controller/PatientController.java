package com.springproject.medihubweb.controller;


import com.springproject.medihubdata.model.Patient;
import com.springproject.medihubdata.service.PatientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/api/patients"})
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    public Patient admitPatient(@RequestBody Patient patient) {
        return patientService.admitPatient(patient);
    }

    @GetMapping("/status")
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }
}
