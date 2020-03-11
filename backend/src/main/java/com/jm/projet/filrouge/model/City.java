package com.jm.projet.filrouge.model;

import com.jm.projet.filrouge.common.jpa.AbstractEntity;
import lombok.*;

import javax.persistence.*;

@Table(name="city")
@Entity
@AttributeOverride(name = "id", column = @Column(name = "ID_CITY"))
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true, onlyExplicitlyIncluded = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Setter
@Getter
public class City extends AbstractEntity<Long> {

    @Column(name = "NAME", length = 255, nullable = false)
    @EqualsAndHashCode.Include
    @ToString.Include
    private String name;

    @Column(name = "postal_code",nullable = false)
    @ToString.Include
    private String postalCode;

    @Column(name = "latitude", precision = 4, scale = 9)
    @ToString.Include
    private Double latitude;

    @Column(name = "longitude" , precision = 4, scale = 9)
    @ToString.Include
    private Double longitude;

    @ToString.Include
    @ManyToOne
    private Department department;
}
