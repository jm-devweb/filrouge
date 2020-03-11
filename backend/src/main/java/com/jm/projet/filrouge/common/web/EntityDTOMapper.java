package com.jm.projet.filrouge.common.web;

import com.jm.projet.filrouge.common.jpa.AbstractEntity;
import com.jm.projet.filrouge.dto.RegionDTO;
import com.jm.projet.filrouge.model.Region;

import java.util.Collection;
import java.util.List;

/**
 * Parent interface of all mappers.
 *
 * @param <E> Entity object
 * @param <D> DTO object
 */
public interface EntityDTOMapper<E extends AbstractEntity, D> {

    /**
     * Convert the <code>D</code> DTO object to its corresponding entity <code>E</code>.
     *
     * @param dto of <code>D</code> The DTO to convert
     * @return <code>E</code> resulting entity
     */
    public E toEntity(D dto);

    /**
     * Convert the <code>E</code> entity object to its corresponding DTO <code>D</code>.
     *
     * @param entity of <code>E</code> entity object to convert
     * @return <code>D</code> resulting DTO
     */
    public D toDTO(E entity);

    public List<D> toListDTO(List<E> entity);
}
