package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.service.StakeHolderService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.application.web.rest.util.PaginationUtil;
import io.github.jhipster.application.service.dto.StakeHolderDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing StakeHolder.
 */
@RestController
@RequestMapping("/api")
public class StakeHolderResource {

    private final Logger log = LoggerFactory.getLogger(StakeHolderResource.class);

    private static final String ENTITY_NAME = "stakeHolder";

    private final StakeHolderService stakeHolderService;

    public StakeHolderResource(StakeHolderService stakeHolderService) {
        this.stakeHolderService = stakeHolderService;
    }

    /**
     * POST  /stake-holders : Create a new stakeHolder.
     *
     * @param stakeHolderDTO the stakeHolderDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new stakeHolderDTO, or with status 400 (Bad Request) if the stakeHolder has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/stake-holders")
    @Timed
    public ResponseEntity<StakeHolderDTO> createStakeHolder(@RequestBody StakeHolderDTO stakeHolderDTO) throws URISyntaxException {
        log.debug("REST request to save StakeHolder : {}", stakeHolderDTO);
        if (stakeHolderDTO.getId() != null) {
            throw new BadRequestAlertException("A new stakeHolder cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StakeHolderDTO result = stakeHolderService.save(stakeHolderDTO);
        return ResponseEntity.created(new URI("/api/stake-holders/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /stake-holders : Updates an existing stakeHolder.
     *
     * @param stakeHolderDTO the stakeHolderDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated stakeHolderDTO,
     * or with status 400 (Bad Request) if the stakeHolderDTO is not valid,
     * or with status 500 (Internal Server Error) if the stakeHolderDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/stake-holders")
    @Timed
    public ResponseEntity<StakeHolderDTO> updateStakeHolder(@RequestBody StakeHolderDTO stakeHolderDTO) throws URISyntaxException {
        log.debug("REST request to update StakeHolder : {}", stakeHolderDTO);
        if (stakeHolderDTO.getId() == null) {
            return createStakeHolder(stakeHolderDTO);
        }
        StakeHolderDTO result = stakeHolderService.save(stakeHolderDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, stakeHolderDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /stake-holders : get all the stakeHolders.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of stakeHolders in body
     */
    @GetMapping("/stake-holders")
    @Timed
    public ResponseEntity<List<StakeHolderDTO>> getAllStakeHolders(Pageable pageable) {
        log.debug("REST request to get a page of StakeHolders");
        Page<StakeHolderDTO> page = stakeHolderService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/stake-holders");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /stake-holders/:id : get the "id" stakeHolder.
     *
     * @param id the id of the stakeHolderDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the stakeHolderDTO, or with status 404 (Not Found)
     */
    @GetMapping("/stake-holders/{id}")
    @Timed
    public ResponseEntity<StakeHolderDTO> getStakeHolder(@PathVariable Long id) {
        log.debug("REST request to get StakeHolder : {}", id);
        StakeHolderDTO stakeHolderDTO = stakeHolderService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(stakeHolderDTO));
    }

    /**
     * DELETE  /stake-holders/:id : delete the "id" stakeHolder.
     *
     * @param id the id of the stakeHolderDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/stake-holders/{id}")
    @Timed
    public ResponseEntity<Void> deleteStakeHolder(@PathVariable Long id) {
        log.debug("REST request to delete StakeHolder : {}", id);
        stakeHolderService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
