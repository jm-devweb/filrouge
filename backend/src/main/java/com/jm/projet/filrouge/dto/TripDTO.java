package com.jm.projet.filrouge.dto;

import com.jm.projet.filrouge.model.City;
import com.jm.projet.filrouge.model.PoI;
import com.jm.projet.filrouge.model.User;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@ApiModel(value = "Trip", description = "Trip of application")

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TripDTO {

    private Long id;

    private String name;

    private Date dateTrip;

    private Time timeStart;

    private Time timeEnd;

    private Integer nbPerson;

    private String description;

    private User promoteur;

    private List<User> users;

    private City city;

    private PoI poi ;

    private Integer ageMin;

    private Integer ageMax;

}
