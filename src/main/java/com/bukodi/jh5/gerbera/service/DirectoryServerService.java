package com.bukodi.jh5.gerbera.service;

import com.bukodi.jh5.gerbera.service.dto.DirectoryServerDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing DirectoryServer.
 */
public interface DirectoryServerService {

    /**
     * Save a directoryServer.
     *
     * @param directoryServerDTO the entity to save
     * @return the persisted entity
     */
    DirectoryServerDTO save(DirectoryServerDTO directoryServerDTO);

    /**
     * Get all the directoryServers.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<DirectoryServerDTO> findAll(Pageable pageable);


    /**
     * Get the "id" directoryServer.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<DirectoryServerDTO> findOne(Long id);

    /**
     * Delete the "id" directoryServer.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
