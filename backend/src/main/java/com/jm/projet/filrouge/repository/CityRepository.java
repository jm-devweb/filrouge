package com.jm.projet.filrouge.repository;

import java.util.List;

import com.jm.projet.filrouge.model.City;
import com.jm.projet.filrouge.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long>,  JpaSpecificationExecutor<City> {
    List<City> findCitiesByDepartmentIdOrderByName(Long id);
}
