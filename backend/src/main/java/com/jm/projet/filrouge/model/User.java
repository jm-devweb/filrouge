package com.jm.projet.filrouge.model;

import lombok.*;

import javax.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Table(name="myuser")
@AttributeOverride(name = "id", column = @Column(name = "ID_USER"))
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of= {"id"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstname", length = 255, nullable = false)
    private String firstname;

    @Column(name = "lastname", length = 50, nullable = false)
    private String lastname;

    @Column(name = "login", length = 50, nullable = false)
    private String login;

    @Column(name = "password", length = 128, nullable = false)
    private String password;

    @Column(name = "email", length = 255, nullable = false)
    private String email;

    @Column(name = "avatar", length = 255)
    private String avatar;

    @Column(name = "description", length = 2048)
    private String description;

    @Column(name = "birthday", nullable = true)
    private Date birthday;

    @Column(name = "date_creation", nullable = true)
    private Date dateCreation;

    @Column(name = "age")
    private Integer age;

    @ManyToOne
    private City city;

    //@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "poi_users",
            joinColumns = @JoinColumn(name = "users_id_user"),
            inverseJoinColumns = @JoinColumn(name = "poi_id_poi"))
    private Set<PoI> pois = new HashSet<>();

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    public enum Gender {
        M,
        F,
        N
    }
}
