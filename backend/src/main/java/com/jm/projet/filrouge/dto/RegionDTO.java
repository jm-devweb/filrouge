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

  //  @Null(groups = OnCreate.class, message = "{validation.Region.creation.id.invalid}")
 //   @NotNull(groups = OnUpdate.class, message = "{validation.Region.update.id.invalid}")
    private Long id;

    private String name;

  public interface OnCreate {}

  public interface OnUpdate {}
}
