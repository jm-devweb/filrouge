package com.jm.projet.filrouge.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Table(name="poi")
@AttributeOverride(name = "id", column = @Column(name = "ID_POI"))

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of= {"id"})
@ToString(exclude = {"users", "trips"})
public class PoI  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", length = 255, nullable = false, unique = true)
    private String name;

    //@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    @ManyToMany(mappedBy = "pois", fetch = FetchType.LAZY)
    private Set<User> users;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Trip> trips;
}
