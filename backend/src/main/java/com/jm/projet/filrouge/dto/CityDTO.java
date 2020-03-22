package com.jm.projet.filrouge.dto;

import com.jm.projet.filrouge.model.Department;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@ApiModel(value = "City", description = "A city of France")


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CityDTO {

    @ApiModelProperty(
            example = "1",
            required = true,
            value = "Id of City")
    private Long id;

    @ApiModelProperty(
            example = "Paris",
            required = true,
            value = "Name of City")
    private String name;

    @ApiModelProperty(
            example = "7500",
            required = true,
            value = "PostalCode of City")
    private String postalCode;

    private Double latitude;

    private Double longitude;

    @ApiModelProperty(
            example = "Paris",
            required = true,
            value = "Department of City")
    private Department department;

}
