package com.bukodi.jh5.gerbera.service;

import com.bukodi.jh5.gerbera.service.dto.CertificateTemplateDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing CertificateTemplate.
 */
public interface CertificateTemplateService {

    /**
     * Save a certificateTemplate.
     *
     * @param certificateTemplateDTO the entity to save
     * @return the persisted entity
     */
    CertificateTemplateDTO save(CertificateTemplateDTO certificateTemplateDTO);

    /**
     * Get all the certificateTemplates.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CertificateTemplateDTO> findAll(Pageable pageable);


    /**
     * Get the "id" certificateTemplate.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CertificateTemplateDTO> findOne(Long id);

    /**
     * Delete the "id" certificateTemplate.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
