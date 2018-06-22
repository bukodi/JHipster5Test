package com.bukodi.jh5.gerbera.service;

import com.bukodi.jh5.gerbera.service.dto.LogicalCardDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing LogicalCard.
 */
public interface LogicalCardService {

    /**
     * Save a logicalCard.
     *
     * @param logicalCardDTO the entity to save
     * @return the persisted entity
     */
    LogicalCardDTO save(LogicalCardDTO logicalCardDTO);

    /**
     * Get all the logicalCards.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<LogicalCardDTO> findAll(Pageable pageable);

    /**
     * Get all the LogicalCard with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<LogicalCardDTO> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" logicalCard.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<LogicalCardDTO> findOne(Long id);

    /**
     * Delete the "id" logicalCard.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
