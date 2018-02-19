package io.github.jhipster.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A StakeHolder.
 */
@Entity
@Table(name = "stake_holder")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class StakeHolder implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "agency")
    private String agency;

    @Column(name = "unit")
    private String unit;

    @ManyToOne
    private StakeHolder stakeHolder;

    @OneToMany(mappedBy = "stakeHolder")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<StakeHolder> upperStakeHolders = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public StakeHolder description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAgency() {
        return agency;
    }

    public StakeHolder agency(String agency) {
        this.agency = agency;
        return this;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getUnit() {
        return unit;
    }

    public StakeHolder unit(String unit) {
        this.unit = unit;
        return this;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public StakeHolder getStakeHolder() {
        return stakeHolder;
    }

    public StakeHolder stakeHolder(StakeHolder stakeHolder) {
        this.stakeHolder = stakeHolder;
        return this;
    }

    public void setStakeHolder(StakeHolder stakeHolder) {
        this.stakeHolder = stakeHolder;
    }

    public Set<StakeHolder> getUpperStakeHolders() {
        return upperStakeHolders;
    }

    public StakeHolder upperStakeHolders(Set<StakeHolder> stakeHolders) {
        this.upperStakeHolders = stakeHolders;
        return this;
    }

    public StakeHolder addUpperStakeHolder(StakeHolder stakeHolder) {
        this.upperStakeHolders.add(stakeHolder);
        stakeHolder.setStakeHolder(this);
        return this;
    }

    public StakeHolder removeUpperStakeHolder(StakeHolder stakeHolder) {
        this.upperStakeHolders.remove(stakeHolder);
        stakeHolder.setStakeHolder(null);
        return this;
    }

    public void setUpperStakeHolders(Set<StakeHolder> stakeHolders) {
        this.upperStakeHolders = stakeHolders;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StakeHolder stakeHolder = (StakeHolder) o;
        if (stakeHolder.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), stakeHolder.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "StakeHolder{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", agency='" + getAgency() + "'" +
            ", unit='" + getUnit() + "'" +
            "}";
    }
}
