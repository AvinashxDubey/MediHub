package com.springproject.medihubweb.controller;

import com.springproject.medihubdata.dto.DashboardResponse;
import com.springproject.medihubdata.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/dashboard"})
public class DashboardController {
    private final int TOTAL_BEDS = 100; //later we'll update this dynamically
    private final PatientService patientService;

    public DashboardController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public ResponseEntity<DashboardResponse> getDashboardData(){
        int opdQueueSize = patientService.getOpdQueueSize();
        int totalPatients = patientService.getPatientCount();
        int availableBeds = TOTAL_BEDS-(totalPatients-opdQueueSize);

        DashboardResponse response = new DashboardResponse(
                availableBeds,
                totalPatients,
                opdQueueSize
        );

        return  ResponseEntity.ok(response);
    }


}
