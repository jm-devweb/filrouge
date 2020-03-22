package com.jm.projet.filrouge.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jm.projet.filrouge.model.City;
import com.jm.projet.filrouge.model.PoI;
import com.jm.projet.filrouge.model.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    private Date birthday;

    private Date dateCreation;

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
