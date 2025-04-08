package com.springproject.medihubdata.service;

import com.springproject.medihubdata.model.Patient;
import com.springproject.medihubdata.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient admitPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public List<Patient> getAllPatients(){
        return patientRepository.findAll();
    }

    public int getPatientCount(){
        return (int)patientRepository.count();
    }

    public int getOpdQueueSize(){
        //placeholder logic, proper queue mgmt need to be done later
        return (int) patientRepository.findAll().stream()
                .filter(p -> "OPD".equalsIgnoreCase(p.getDepartment()))
                .count();
    }
}
