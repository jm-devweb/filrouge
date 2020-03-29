package com.jm.projet.filrouge.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jm.projet.filrouge.model.Trip;
import com.jm.projet.filrouge.model.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Set;

@ApiModel(value = "POI", description = "Point of Interest")

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PoIDTO {

    @ApiModelProperty(
            example = "1",
            required = true,
            value = "Id of POI")
    private Long id;

    @ApiModelProperty(
            example = "Cinéma",
            required = true,
            value = "Name of POI")
    private String name;

}
