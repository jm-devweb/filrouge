package com.jm.projet.filrouge.model;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class TripManager  implements Serializable {
    private Long id;
    private String name;
    private String dateTrip;
    private String timeStart;
    private String timeEnd;
    private Integer nbPerson;
    private String description;
    private Long promoteurId;
    private Long cityId;
    private Long poiId;
    private Integer ageMin;
    private Integer ageMax;
}
