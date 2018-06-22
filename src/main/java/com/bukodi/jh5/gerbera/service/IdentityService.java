package com.bukodi.jh5.gerbera.service;

import com.bukodi.jh5.gerbera.service.dto.IdentityDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Identity.
 */
public interface IdentityService {

    /**
     * Save a identity.
     *
     * @param identityDTO the entity to save
     * @return the persisted entity
     */
    IdentityDTO save(IdentityDTO identityDTO);

    /**
     * Get all the identities.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<IdentityDTO> findAll(Pageable pageable);


    /**
     * Get the "id" identity.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<IdentityDTO> findOne(Long id);

    /**
     * Delete the "id" identity.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
