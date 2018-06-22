package com.bukodi.jh5.gerbera.service;

import com.bukodi.jh5.gerbera.service.dto.IdentityProfileDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing IdentityProfile.
 */
public interface IdentityProfileService {

    /**
     * Save a identityProfile.
     *
     * @param identityProfileDTO the entity to save
     * @return the persisted entity
     */
    IdentityProfileDTO save(IdentityProfileDTO identityProfileDTO);

    /**
     * Get all the identityProfiles.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<IdentityProfileDTO> findAll(Pageable pageable);


    /**
     * Get the "id" identityProfile.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<IdentityProfileDTO> findOne(Long id);

    /**
     * Delete the "id" identityProfile.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
