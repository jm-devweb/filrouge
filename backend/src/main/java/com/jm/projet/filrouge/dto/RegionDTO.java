package com.jm.projet.filrouge.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@ApiModel(value = "Region", description = "A region of France")

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegionDTO {

    @ApiModelProperty(
            example = "1",
            required = true,
            value = "Id of Region")
    private Long id;

    @ApiModelProperty(
            example = "Bourgogne",
            required = true,
            value = "Name of Region")
    private String name;
}
