package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.StakeHolderService;
import io.github.jhipster.application.domain.StakeHolder;
import io.github.jhipster.application.repository.StakeHolderRepository;
import io.github.jhipster.application.service.dto.StakeHolderDTO;
import io.github.jhipster.application.service.mapper.StakeHolderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing StakeHolder.
 */
@Service
@Transactional
public class StakeHolderServiceImpl implements StakeHolderService {

    private final Logger log = LoggerFactory.getLogger(StakeHolderServiceImpl.class);

    private final StakeHolderRepository stakeHolderRepository;

    private final StakeHolderMapper stakeHolderMapper;

    public StakeHolderServiceImpl(StakeHolderRepository stakeHolderRepository, StakeHolderMapper stakeHolderMapper) {
        this.stakeHolderRepository = stakeHolderRepository;
        this.stakeHolderMapper = stakeHolderMapper;
    }

    /**
     * Save a stakeHolder.
     *
     * @param stakeHolderDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public StakeHolderDTO save(StakeHolderDTO stakeHolderDTO) {
        log.debug("Request to save StakeHolder : {}", stakeHolderDTO);
        StakeHolder stakeHolder = stakeHolderMapper.toEntity(stakeHolderDTO);
        stakeHolder = stakeHolderRepository.save(stakeHolder);
        return stakeHolderMapper.toDto(stakeHolder);
    }

    /**
     * Get all the stakeHolders.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<StakeHolderDTO> findAll(Pageable pageable) {
        log.debug("Request to get all StakeHolders");
        return stakeHolderRepository.findAll(pageable)
            .map(stakeHolderMapper::toDto);
    }

    /**
     * Get one stakeHolder by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public StakeHolderDTO findOne(Long id) {
        log.debug("Request to get StakeHolder : {}", id);
        StakeHolder stakeHolder = stakeHolderRepository.findOne(id);
        return stakeHolderMapper.toDto(stakeHolder);
    }

    /**
     * Delete the stakeHolder by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete StakeHolder : {}", id);
        stakeHolderRepository.delete(id);
    }
}
