package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.StakeHolder;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the StakeHolder entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StakeHolderRepository extends JpaRepository<StakeHolder, Long> {

}
