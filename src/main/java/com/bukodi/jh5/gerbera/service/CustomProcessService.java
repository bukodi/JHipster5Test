package com.bukodi.jh5.gerbera.service;

import com.bukodi.jh5.gerbera.service.dto.CustomProcessDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing CustomProcess.
 */
public interface CustomProcessService {

    /**
     * Save a customProcess.
     *
     * @param customProcessDTO the entity to save
     * @return the persisted entity
     */
    CustomProcessDTO save(CustomProcessDTO customProcessDTO);

    /**
     * Get all the customProcesses.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CustomProcessDTO> findAll(Pageable pageable);


    /**
     * Get the "id" customProcess.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CustomProcessDTO> findOne(Long id);

    /**
     * Delete the "id" customProcess.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
