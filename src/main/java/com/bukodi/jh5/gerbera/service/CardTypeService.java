package com.bukodi.jh5.gerbera.service;

import com.bukodi.jh5.gerbera.service.dto.CardTypeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing CardType.
 */
public interface CardTypeService {

    /**
     * Save a cardType.
     *
     * @param cardTypeDTO the entity to save
     * @return the persisted entity
     */
    CardTypeDTO save(CardTypeDTO cardTypeDTO);

    /**
     * Get all the cardTypes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CardTypeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" cardType.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CardTypeDTO> findOne(Long id);

    /**
     * Delete the "id" cardType.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
