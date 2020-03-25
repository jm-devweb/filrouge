package com.jm.projet.filrouge.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Table(name="trip")
@AttributeOverride(name = "id", column = @Column(name = "ID_TRIP"))
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Trip implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", length = 255, nullable = false)
    private String name;

    @Column(name = "date_trip", nullable = false)
    private Date dateTrip;

    @Column(name = "time_start", nullable = false)
    private Time timeStart;

    @Column(name = "time_end", nullable = false)
      private Time timeEnd;

    @Column(name = "nb_person", nullable = false)
    private Integer nbPerson;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne
    private User promoteur;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<User> users;

    @ManyToOne
    private City city;

    @ManyToOne
    private PoI poi ;

    @Column(name = "age_min")
    private Integer ageMin;

    @Column(name = "age_max")
    private Integer ageMax;

}
