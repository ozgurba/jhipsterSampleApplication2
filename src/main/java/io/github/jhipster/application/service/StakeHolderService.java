package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.StakeHolderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing StakeHolder.
 */
public interface StakeHolderService {

    /**
     * Save a stakeHolder.
     *
     * @param stakeHolderDTO the entity to save
     * @return the persisted entity
     */
    StakeHolderDTO save(StakeHolderDTO stakeHolderDTO);

    /**
     * Get all the stakeHolders.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<StakeHolderDTO> findAll(Pageable pageable);

    /**
     * Get the "id" stakeHolder.
     *
     * @param id the id of the entity
     * @return the entity
     */
    StakeHolderDTO findOne(Long id);

    /**
     * Delete the "id" stakeHolder.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
