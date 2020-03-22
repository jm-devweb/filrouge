package com.jm.projet.filrouge.repository;

import java.util.Optional;
import com.jm.projet.filrouge.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {

}
