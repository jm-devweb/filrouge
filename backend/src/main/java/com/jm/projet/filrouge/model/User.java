package com.jm.projet.filrouge.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<PoI> pois;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    public enum Gender {
        M,
        F,
        N
    }
}
