package com.bukodi.jh5.gerbera.service.impl;

import com.bukodi.jh5.gerbera.service.CertificateAuthorityService;
import com.bukodi.jh5.gerbera.domain.CertificateAuthority;
import com.bukodi.jh5.gerbera.repository.CertificateAuthorityRepository;
import com.bukodi.jh5.gerbera.service.dto.CertificateAuthorityDTO;
import com.bukodi.jh5.gerbera.service.mapper.CertificateAuthorityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing CertificateAuthority.
 */
@Service
@Transactional
public class CertificateAuthorityServiceImpl implements CertificateAuthorityService {

    private final Logger log = LoggerFactory.getLogger(CertificateAuthorityServiceImpl.class);

    private final CertificateAuthorityRepository certificateAuthorityRepository;

    private final CertificateAuthorityMapper certificateAuthorityMapper;

    public CertificateAuthorityServiceImpl(CertificateAuthorityRepository certificateAuthorityRepository, CertificateAuthorityMapper certificateAuthorityMapper) {
        this.certificateAuthorityRepository = certificateAuthorityRepository;
        this.certificateAuthorityMapper = certificateAuthorityMapper;
    }

    /**
     * Save a certificateAuthority.
     *
     * @param certificateAuthorityDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CertificateAuthorityDTO save(CertificateAuthorityDTO certificateAuthorityDTO) {
        log.debug("Request to save CertificateAuthority : {}", certificateAuthorityDTO);
        CertificateAuthority certificateAuthority = certificateAuthorityMapper.toEntity(certificateAuthorityDTO);
        certificateAuthority = certificateAuthorityRepository.save(certificateAuthority);
        return certificateAuthorityMapper.toDto(certificateAuthority);
    }

    /**
     * Get all the certificateAuthorities.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CertificateAuthorityDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CertificateAuthorities");
        return certificateAuthorityRepository.findAll(pageable)
            .map(certificateAuthorityMapper::toDto);
    }


    /**
     * Get one certificateAuthority by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CertificateAuthorityDTO> findOne(Long id) {
        log.debug("Request to get CertificateAuthority : {}", id);
        return certificateAuthorityRepository.findById(id)
            .map(certificateAuthorityMapper::toDto);
    }

    /**
     * Delete the certificateAuthority by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CertificateAuthority : {}", id);
        certificateAuthorityRepository.deleteById(id);
    }
}
