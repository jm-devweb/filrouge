package com.jm.projet.filrouge.dto;

import com.jm.projet.filrouge.model.Department;
import io.swagger.annotations.ApiModel;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ApiModel(value = "City", description = "A city of France")
public class CityDTO {

    private Long id;

    private String name;

    private String postalCode;

    private Double latitude;

    private Double longitude;

    private Department department;



}
