package com.jm.projet.filrouge.repository;

import com.jm.projet.filrouge.model.City;
import org.springframework.data.jpa.domain.Specification;

public class CitySpecifications {

    /**
     * Check a optional department
     *
     * @param departmentId the department to check
     * @return a Department specification
     */
    public static Specification<City> hasDepartmentOptional(int departmentId) {
        if(departmentId == 0){
            return (city, cq, cb) -> {
                return cb.equal(city.get("department").get("id"), city.get("city").get("department").get("id"));
            };
        } else {
            return (city, cq, cb) -> {
                return cb.equal(city.get("department").get("id"), departmentId);
            };
        }
    }

}
