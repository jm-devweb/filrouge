package com.jm.projet.filrouge.dto;


import com.jm.projet.filrouge.model.Region;
import io.swagger.annotations.ApiModel;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ApiModel(value = "Department", description = "A department of France")
public class DepartmentDTO {

    private Long id;

    private String name;

    private Region region;
}
