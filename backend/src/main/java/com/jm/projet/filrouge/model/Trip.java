package com.jm.projet.filrouge.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Table(name = "trip")
@AttributeOverride(name = "id", column = @Column(name = "ID_TRIP"))
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"users"})
@ToString(exclude = {"users"})
public class Trip implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", length = 255, nullable = false)
    private String name;

    @Column(name = "date_trip", nullable = false)
    private Date dateTrip;

    @Column(name = "time_start", nullable = false)
    private Date timeStart;

    @Column(name = "time_end", nullable = false)
    private Date timeEnd;

    @Column(name = "nb_person", nullable = false)
    private Integer nbPerson;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne
    private User promoteur;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<User> users;

    // A tester
    @ManyToOne
    private City city;

    @ManyToOne
    private PoI poi;

    @Column(name = "age_min")
    private Integer ageMin;

    @Column(name = "age_max")
    private Integer ageMax;

    public boolean register(User user) {
        boolean result = this.users.contains (user);
        if (result == false) this.users.add (user);
        return !result;
    }

    public boolean unregister(User user) {
        boolean result = this.users.contains (user);
        if (result == true) this.users.remove (user);
        return result;
    }
}
