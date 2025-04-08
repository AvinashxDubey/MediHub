package com.springproject.medihubdata.repository;

import com.springproject.medihubdata.model.Bed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BedRepository extends JpaRepository<Bed,Long> {
    long countByOccupied(boolean occupied);

    List<Bed> findByWardAndOccupied(String ward, boolean occupied);

}
