package com.bukodi.jh5.gerbera.service;

import com.bukodi.jh5.gerbera.service.dto.CertificateAuthorityDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing CertificateAuthority.
 */
public interface CertificateAuthorityService {

    /**
     * Save a certificateAuthority.
     *
     * @param certificateAuthorityDTO the entity to save
     * @return the persisted entity
     */
    CertificateAuthorityDTO save(CertificateAuthorityDTO certificateAuthorityDTO);

    /**
     * Get all the certificateAuthorities.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CertificateAuthorityDTO> findAll(Pageable pageable);


    /**
     * Get the "id" certificateAuthority.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CertificateAuthorityDTO> findOne(Long id);

    /**
     * Delete the "id" certificateAuthority.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
