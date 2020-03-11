package com.jm.projet.filrouge.dto;

import io.swagger.annotations.ApiModel;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "Region", description = "A region of France")
@Setter
@Getter
public class RegionDTO {

    private Long id;

    private String name;

}
