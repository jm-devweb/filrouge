package com.jm.projet.filrouge.repository;

import com.jm.projet.filrouge.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.sql.Date;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    User findUserByLogin(String login);

    @Query("SELECT u FROM User u WHERE EXTRACT(month FROM u.birthday) = :month AND EXTRACT(day FROM u.birthday) = :day")
    Page<User> findAllByBirthday(Pageable pageable, @Param("month") int month, @Param("day") int day);
}

