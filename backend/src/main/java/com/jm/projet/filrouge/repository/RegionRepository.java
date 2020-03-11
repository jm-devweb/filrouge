package com.jm.projet.filrouge.repository;

import com.jm.projet.filrouge.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {

    Optional<Region> findRegionByName(String name);
}
