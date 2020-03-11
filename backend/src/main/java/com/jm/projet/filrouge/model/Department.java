package com.jm.projet.filrouge.model;


import com.jm.projet.filrouge.common.jpa.AbstractEntity;
import lombok.*;

import javax.persistence.*;

@Table(name="department")
@Entity
@AttributeOverride(name = "id", column = @Column(name = "ID_DEPARTMENT"))
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true, onlyExplicitlyIncluded = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Setter
@Getter
public class Department  extends AbstractEntity<Long> {

    @Column(name = "NAME", length = 255, nullable = false, unique = true)
    @ToString.Include
    private String name;

    @EqualsAndHashCode.Include
    @ManyToOne
    private Region region;
}
