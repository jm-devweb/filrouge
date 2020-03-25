package com.jm.projet.filrouge.repository;

import com.jm.projet.filrouge.model.Trip;
import org.springframework.data.jpa.domain.Specification;

public class TripSpecifications {

    /**
     * Check a optional poi
     *
     * @param poi the poi to check
     * @return a trip specification
     */
    public static Specification<Trip> hasPoIOptional(int poiId) {
        if(poiId == 0){
            return (trip, cq, cb) -> {
                return cb.equal(trip.get("poi").get("id"), trip.get("poi").get("id"));
            };
        } else {
            return (trip, cq, cb) -> {
                return cb.equal(trip.get("poi").get("id"), poiId);
            };
        }
    }

    /**
     * Check a optional department
     *
     * @param departmentId the department to check
     * @return a user specification
     */
    public static Specification<Trip> hasDepartmentOptional(int departmentId) {
        if(departmentId == 0){
            return (trip, cq, cb) -> {
                return cb.equal(trip.get("city").get("department").get("id"), trip.get("city").get("department").get("id"));
            };
        } else {
            return (trip, cq, cb) -> {
                return cb.equal(trip.get("city").get("department").get("id"), departmentId);
            };
        }
    }

    /**
     * Check a optional region
     *
     * @param regionId the region to check
     * @return a user specification
     */
    public static Specification<Trip> hasRegionOptional(int regionId) {
        if(regionId == 0){
            return (trip, cq, cb) -> {
                return cb.equal(
                        trip.get("city").get("department").get("region").get("id"),
                        trip.get("city").get("department").get("region").get("id")
                );
            };
        } else {
            return (trip, cq, cb) -> {
                return cb.equal(trip.get("city").get("department").get("region").get("id"), regionId);
            };
        }
    }

}
