package com.jm.projet.filrouge.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name="city")
@AttributeOverride(name = "id", column = @Column(name = "ID_CITY"))

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class City  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", length = 255, nullable = false)
    private String name;

    @Column(name = "postal_code",nullable = false)
    private String postalCode;

    @Column(name = "latitude", precision = 4, scale = 9)
    private Double latitude;

    @Column(name = "longitude" , precision = 4, scale = 9)
    private Double longitude;

    @ManyToOne
    private Department department;

}
