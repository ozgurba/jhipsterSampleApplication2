package io.github.jhipster.application.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the StakeHolder entity.
 */
public class StakeHolderDTO implements Serializable {

    private Long id;

    private String description;

    private String agency;

    private String unit;

    private Long stakeHolderId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Long getStakeHolderId() {
        return stakeHolderId;
    }

    public void setStakeHolderId(Long stakeHolderId) {
        this.stakeHolderId = stakeHolderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        StakeHolderDTO stakeHolderDTO = (StakeHolderDTO) o;
        if(stakeHolderDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), stakeHolderDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "StakeHolderDTO{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", agency='" + getAgency() + "'" +
            ", unit='" + getUnit() + "'" +
            "}";
    }
}
