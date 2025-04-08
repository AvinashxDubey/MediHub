package com.springproject.medihubweb.controller;

import com.springproject.medihubdata.model.Bed;
import com.springproject.medihubdata.service.BedService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/beds")
public class BedController {
    private final BedService bedService;

    public BedController(BedService bedService) {
        this.bedService = bedService;
    }

    @GetMapping("/total-beds")
    public ResponseEntity<Long> getTotalBeds() {
        return ResponseEntity.ok(bedService.getTotalBeds());
    }

    @GetMapping("/available")
    public ResponseEntity<Long> getAvailableBeds() {
        return ResponseEntity.ok(bedService.getAvailableBeds());
    }

    @GetMapping("/occupied")
    public ResponseEntity<Long> getOccupiedBeds() {
        return ResponseEntity.ok(bedService.getOccupiedBeds());
    }

    @GetMapping // No pagination — existing frontend keeps working
    public List<Bed> getAllBeds() {
        return bedService.getAllBeds();
    }

    @GetMapping("/paginated") // Paginated version
    public ResponseEntity<Page<Bed>> getPaginatedBeds(Pageable pageable) {
        Page<Bed> beds = bedService.getAllBeds(pageable);
        return ResponseEntity.ok(beds);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Bed>> getBedsByWardAndOccupancy(
            @RequestParam String ward,
            @RequestParam boolean occupied
    ) {
        List<Bed> beds = bedService.findByWardAndOccupied(ward, occupied);

        if (beds.isEmpty()) {
            return ResponseEntity.status(204).build(); // No Content
        }

        return ResponseEntity.ok(beds);
    }

    @PostMapping("/adding")
    public ResponseEntity<Bed> addBed(@RequestBody Bed bed){
        return ResponseEntity.ok(bedService.addBed(bed));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bed> updateBed(@PathVariable Long id, @RequestBody Bed bed) {
        Bed updatedBed = bedService.updateBed(id, bed);
        if (updatedBed != null) {
            return ResponseEntity.ok(updatedBed);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBed(@PathVariable Long id) {
        boolean deleted = bedService.deleteBed(id);
        if (deleted) {
            return ResponseEntity.ok("Bed deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bed not found.");
        }
    }



}
