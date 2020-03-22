package com.jm.projet.filrouge.dto;

import com.jm.projet.filrouge.model.Region;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@ApiModel(value = "Department", description = "A department of France")
public class DepartmentDTO {

    @ApiModelProperty(
            example = "1",
            required = true,
            value = "Id of Department")
    private Long id;

    @ApiModelProperty(
            example = "Seine et Marne",
            required = true,
            value = "Name of Department")
    private String name;

    @ApiModelProperty(
            example = "Région parisienne",
            required = true,
            value = "Region of Department")
    private Region region;
}
