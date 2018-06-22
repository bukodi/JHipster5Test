package com.bukodi.jh5.gerbera.service;

import com.bukodi.jh5.gerbera.service.dto.CertificateDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Certificate.
 */
public interface CertificateService {

    /**
     * Save a certificate.
     *
     * @param certificateDTO the entity to save
     * @return the persisted entity
     */
    CertificateDTO save(CertificateDTO certificateDTO);

    /**
     * Get all the certificates.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CertificateDTO> findAll(Pageable pageable);


    /**
     * Get the "id" certificate.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CertificateDTO> findOne(Long id);

    /**
     * Delete the "id" certificate.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
