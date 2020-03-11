package com.jm.projet.filrouge.model;

import com.jm.projet.filrouge.common.jpa.AbstractEntity;
import lombok.*;

import javax.persistence.*;


@Table(name="region")
@Entity
@AttributeOverride(name = "id", column = @Column(name = "ID_REGION"))
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true, onlyExplicitlyIncluded = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Setter
@Getter
public class Region extends AbstractEntity<Long> {

    @Column(name = "NAME", length = 255, nullable = false, unique = true)
    @EqualsAndHashCode.Include
    @ToString.Include
    private String name;
}
