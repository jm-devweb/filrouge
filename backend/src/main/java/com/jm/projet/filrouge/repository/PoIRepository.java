package com.jm.projet.filrouge.repository;


import com.jm.projet.filrouge.model.PoI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoIRepository extends JpaRepository<PoI, Long> {

}
