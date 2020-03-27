package com.jm.projet.filrouge.dto;

import com.jm.projet.filrouge.model.City;
import com.jm.projet.filrouge.model.PoI;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.sql.Date;
import java.util.List;


@ApiModel(value = "User", description = "User of application")

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;

    private String firstname;

    private String lastname;

    private String login;

    private String password;

    private String email;

    private String avatar;

    private String description;

    private String birthday;

    private String dateCreation;

    private Integer age;

    private City city;

    private List<PoI> pois;

    @Enumerated(EnumType.STRING)
    private GenderDTO gender;

    public enum GenderDTO {
        M,
        F,
        N
    }
}
