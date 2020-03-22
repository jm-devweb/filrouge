package com.jm.projet.filrouge.model;

import lombok.*;
import javax.persistence.*;


@Table(name="region")
@AttributeOverride(name = "id", column = @Column(name = "ID_REGION"))
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", length = 255, nullable = false, unique = true)
    private String name;

}
