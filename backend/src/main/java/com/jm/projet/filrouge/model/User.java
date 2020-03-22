package com.jm.projet.filrouge.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Table(name="myuser")
@AttributeOverride(name = "id", column = @Column(name = "ID_USER"))
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FIRSTNAME", length = 255, nullable = false)
    private String firstName;

    @Column(name = "lastname", length = 50, nullable = false)
    private String lastName;

    @Column(name = "login", length = 50, nullable = false)
    private String login;

    @Column(name = "password", length = 128, nullable = false)
    private String password;

    @Column(name = "email", length = 255, nullable = false)
    private String email;

    @Column(name = "avatar", length = 255)
    private String avatar;

    @Column(name = "descriptions", length = 2048)
    private String descriptions;

    @Column(name = "birthday", nullable = true)
    private Date birthday;

    @ManyToOne
    private City city;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<PoI> pois;

    @Column(name = "sex", nullable = false)
    @Enumerated(EnumType.STRING)
    private Sex sex;

    public enum Sex {
        M,
        F,
        N
    }
}
