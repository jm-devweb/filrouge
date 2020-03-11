package com.jm.projet.filrouge.repository;

import com.jm.projet.filrouge.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    List<City> findCitiesByDepartmentId(Long id);
}
