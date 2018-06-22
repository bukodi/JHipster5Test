package com.bukodi.jh5.gerbera.service.impl;

import com.bukodi.jh5.gerbera.service.CertificateTemplateService;
import com.bukodi.jh5.gerbera.domain.CertificateTemplate;
import com.bukodi.jh5.gerbera.repository.CertificateTemplateRepository;
import com.bukodi.jh5.gerbera.service.dto.CertificateTemplateDTO;
import com.bukodi.jh5.gerbera.service.mapper.CertificateTemplateMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing CertificateTemplate.
 */
@Service
@Transactional
public class CertificateTemplateServiceImpl implements CertificateTemplateService {

    private final Logger log = LoggerFactory.getLogger(CertificateTemplateServiceImpl.class);

    private final CertificateTemplateRepository certificateTemplateRepository;

    private final CertificateTemplateMapper certificateTemplateMapper;

    public CertificateTemplateServiceImpl(CertificateTemplateRepository certificateTemplateRepository, CertificateTemplateMapper certificateTemplateMapper) {
        this.certificateTemplateRepository = certificateTemplateRepository;
        this.certificateTemplateMapper = certificateTemplateMapper;
    }

    /**
     * Save a certificateTemplate.
     *
     * @param certificateTemplateDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CertificateTemplateDTO save(CertificateTemplateDTO certificateTemplateDTO) {
        log.debug("Request to save CertificateTemplate : {}", certificateTemplateDTO);
        CertificateTemplate certificateTemplate = certificateTemplateMapper.toEntity(certificateTemplateDTO);
        certificateTemplate = certificateTemplateRepository.save(certificateTemplate);
        return certificateTemplateMapper.toDto(certificateTemplate);
    }

    /**
     * Get all the certificateTemplates.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CertificateTemplateDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CertificateTemplates");
        return certificateTemplateRepository.findAll(pageable)
            .map(certificateTemplateMapper::toDto);
    }


    /**
     * Get one certificateTemplate by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CertificateTemplateDTO> findOne(Long id) {
        log.debug("Request to get CertificateTemplate : {}", id);
        return certificateTemplateRepository.findById(id)
            .map(certificateTemplateMapper::toDto);
    }

    /**
     * Delete the certificateTemplate by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CertificateTemplate : {}", id);
        certificateTemplateRepository.deleteById(id);
    }
}
