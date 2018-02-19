package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.StakeHolderDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity StakeHolder and its DTO StakeHolderDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface StakeHolderMapper extends EntityMapper<StakeHolderDTO, StakeHolder> {

    @Mapping(source = "stakeHolder.id", target = "stakeHolderId")
    StakeHolderDTO toDto(StakeHolder stakeHolder);

    @Mapping(source = "stakeHolderId", target = "stakeHolder")
    @Mapping(target = "upperStakeHolders", ignore = true)
    StakeHolder toEntity(StakeHolderDTO stakeHolderDTO);

    default StakeHolder fromId(Long id) {
        if (id == null) {
            return null;
        }
        StakeHolder stakeHolder = new StakeHolder();
        stakeHolder.setId(id);
        return stakeHolder;
    }
}
