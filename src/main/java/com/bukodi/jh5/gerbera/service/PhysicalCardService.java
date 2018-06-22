package com.bukodi.jh5.gerbera.service;

import com.bukodi.jh5.gerbera.service.dto.PhysicalCardDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing PhysicalCard.
 */
public interface PhysicalCardService {

    /**
     * Save a physicalCard.
     *
     * @param physicalCardDTO the entity to save
     * @return the persisted entity
     */
    PhysicalCardDTO save(PhysicalCardDTO physicalCardDTO);

    /**
     * Get all the physicalCards.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<PhysicalCardDTO> findAll(Pageable pageable);


    /**
     * Get the "id" physicalCard.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<PhysicalCardDTO> findOne(Long id);

    /**
     * Delete the "id" physicalCard.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
