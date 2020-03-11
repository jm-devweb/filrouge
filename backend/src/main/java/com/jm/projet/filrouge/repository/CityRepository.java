package com.jm.projet.filrouge.repository;

import java.util.List;

import com.jm.projet.filrouge.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    List<City> findCitiesByDepartmentId(Long id);
}
