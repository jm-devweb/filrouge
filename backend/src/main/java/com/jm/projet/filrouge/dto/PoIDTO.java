package com.jm.projet.filrouge.dto;


import io.swagger.annotations.ApiModel;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "POI", description = "Point of Interest")
@Setter
@Getter
public class PoIDTO {
    private Long id;

    private String name;

    public interface OnCreate {}

    public interface OnUpdate {}
}
