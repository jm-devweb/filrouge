package com.jm.projet.filrouge.repository;

import com.jm.projet.filrouge.model.Trip;
import com.jm.projet.filrouge.model.User;
import org.springframework.data.jpa.domain.Specification;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDate;
import java.util.Objects;

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

    /**
     * Check a optional promoteur
     *
     * @param userId the department to check
     * @return a user specification
     */
    public static Specification<Trip> hasPromoteurOptional(Long userId) {


        if(userId == 0){
            return (trip, cq, cb) -> {
                return cb.equal(trip.get("promoteur").get("id"),trip.get("promoteur").get("id"));
            };
        } else {
            return (trip, cq, cb) -> {
                return cb.equal(trip.get("promoteur").get("id"), userId);
            };
        }
    }


    /**
     * Check a optional word
     *
     * @param words the department to check
     * @return a Trip specification
     */
    public static Specification<Trip> hasWordsTitleOptional(String words) {
        final String tmp = "%" + words + "%" ;
        if(words.equals ("") ){
            return (trip, cq, cb) -> {
                return cb.equal(trip.get("name"),trip.get("name"));
            };
        } else {
            return (trip, cq, cb) -> {
                return cb.like(trip.get("name"), tmp);
            };
        }
    }

    /**
     * Check a optional word
     *
     * @param words the department to check
     * @return a Trip specification
     */
    public static Specification<Trip> hasWordsDescriptionOptional(String words) {
        final String tmp = "%" + words + "%" ;
        if(words.equals ("") ){
            return (trip, cq, cb) -> {
                return cb.equal(trip.get("description"),trip.get("description"));
            };
        } else {

            return (trip, cq, cb) -> {
                return cb.like(trip.get("description"), tmp);
            };
        }
    }

    /**
     * Check a optional date
     *
     * @param startDate
     * @return a Trip specification
     */
    public static Specification<Trip> hasDateOptional(Date startDate) {
        DateFormat dateFormat = new SimpleDateFormat ("yyyy-MM-dd");
        Date date = new Date();
    //    System.out.println(dateFormat.format(date));

           if(startDate.equals (date) ){
            return (trip, cq, cb) -> {
                return cb.equal(trip.get("dateTrip"),trip.get("dateTrip"));
            };
        } else {
               return (trip, cq, cb) -> {
                   return cb.equal(trip.get("dateTrip"),startDate);
        };
           }
    }

}
