package com.springproject.medihubdata.service;

import com.springproject.medihubdata.model.Bed;
import com.springproject.medihubdata.repository.BedRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BedService {
    private final BedRepository bedRepository;

    public BedService(BedRepository bedRepository) {
        this.bedRepository = bedRepository;
    }

    public long getTotalBeds() {
        return bedRepository.count();
    }

    public long getAvailableBeds(){
        return bedRepository.countByOccupied(false);
    }

    public long getOccupiedBeds(){
        return bedRepository.countByOccupied(true);
    }

    // for testing or management
    public Bed addBed(Bed bed) {
        return bedRepository.save(bed);
    }

    public List<Bed> getAllBeds(){
        return bedRepository.findAll();
    }

    public Page<Bed> getAllBeds(Pageable pageable) {
        return bedRepository.findAll(pageable);
    }

    public Bed updateBed(Long id, Bed bed) {
        Optional<Bed> optionalBed = bedRepository.findById(id);
        //after adding exception handling throw exceptions
        if (optionalBed.isEmpty()) {
            return null;
        }

        Bed existingBed = optionalBed.get();
        existingBed.setOccupied(bed.isOccupied());
        existingBed.setWard(bed.getWard());
        return bedRepository.save(existingBed);
    }

    public boolean deleteBed(Long id) {
        if (bedRepository.existsById(id)) {
            bedRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Bed> findByWardAndOccupied(String ward, boolean occupied) {
        return bedRepository.findByWardAndOccupied(ward, occupied);
    }


}
