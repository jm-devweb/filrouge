package com.jm.projet.filrouge.dto;

import com.jm.projet.filrouge.model.Department;
import com.jm.projet.filrouge.model.Region;
import io.swagger.annotations.ApiModel;
import lombok.*;

import javax.persistence.Column;

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

    public interface OnCreate {}

    public interface OnUpdate {}

}
