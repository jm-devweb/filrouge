package com.jm.projet.filrouge.repository;

import com.jm.projet.filrouge.model.User;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecifications {

    /**
     * Check a optional gender
     *
     * @param gender the gender to check
     * @return a user specification
     */
    public static Specification<User> hasGenderOptional(User.Gender gender) {
        if(gender == null){
            return (user, cq, cb) -> {
                return cb.like(user.get("gender"), user.get("gender"));
            };
        } else {
            return (user, cq, cb) -> {
                return cb.equal(user.get("gender"), gender);
            };
        }
    }

    /**
     * Check a optional pseudo
     *
     * @param pseudo the pseudo to check
     * @return a user specification
     */
    public static Specification<User> hasPseudoOptional(String pseudo) {
        if(pseudo.compareTo("") == 0){
            return (user, cq, cb) -> {
                return cb.equal(user.get("login"), user.get("login"));
            };
        } else {
            return (user, cq, cb) -> {
                return cb.like(user.get("login"), pseudo.toLowerCase());
            };
        }
    }

    /**
     * Check a optional department
     *
     * @param departmentId the department to check
     * @return a user specification
     */
    public static Specification<User> hasDepartmentOptional(int departmentId) {
        if(departmentId == 0){
            return (user, cq, cb) -> {
                return cb.equal(user.get("city").get("department").get("id"), user.get("city").get("department").get("id"));
            };
        } else {
            return (user, cq, cb) -> {
                return cb.equal(user.get("city").get("department").get("id"), departmentId);
            };
        }
    }

    /**
     * Check a optional region
     *
     * @param regionId the region to check
     * @return a user specification
     */
    public static Specification<User> hasRegionOptional(int regionId) {
        if(regionId == 0){
            return (user, cq, cb) -> {
                return cb.equal(
                        user.get("city").get("department").get("region").get("id"),
                        user.get("city").get("department").get("region").get("id")
                );
            };
        } else {
            return (user, cq, cb) -> {
                return cb.equal(user.get("city").get("department").get("region").get("id"), regionId);
            };
        }
    }

    /**
     * Check a optional age
     *
     * @param ageCategory the age to check
     * @return a user specification
     */
    public static Specification<User> hasAgeCategoryOptional(String ageCategory) {
        String[] tAges = ageCategory.split("-");

        if(tAges.length < 2){
            return (user, cq, cb) -> {
                return cb.equal(user.get("age"), user.get("age"));
            };
        } else {
            int ageMin = Integer.parseInt(tAges[0]);
            int ageMax = Integer.parseInt(tAges[1]);
            return (user, cq, cb) -> {
                return cb.between(user.get("age"), ageMin, ageMax);
            };
        }
    }

}
