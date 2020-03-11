package com.jm.projet.filrouge.common.jpa;

import lombok.EqualsAndHashCode;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Objects;

/**
 * Abstract base class for entities. Allows parameterization of id type and implements {@link
 * #equals(Object)} and {@link #hashCode()} based on that id.
 *
 * @param <ID> the type of the identifier.
 */
@MappedSuperclass
public abstract class AbstractEntity<ID extends Serializable> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ID id;

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Persistable#getId()
     */
    public ID getId() {
        return id;
    }

    /**
     * Sets the id of the entity.
     *
     * @param id the id to set
     */
    public void setId(final ID id) {
        this.id = id;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return String.format("Entity of type %s with id: %s", this.getClass().getName(), getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEntity<?> that = (AbstractEntity<?>) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
