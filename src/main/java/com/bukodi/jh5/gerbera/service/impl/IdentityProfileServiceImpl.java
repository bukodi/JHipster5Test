package com.bukodi.jh5.gerbera.service.impl;

import com.bukodi.jh5.gerbera.service.IdentityProfileService;
import com.bukodi.jh5.gerbera.domain.IdentityProfile;
import com.bukodi.jh5.gerbera.repository.IdentityProfileRepository;
import com.bukodi.jh5.gerbera.service.dto.IdentityProfileDTO;
import com.bukodi.jh5.gerbera.service.mapper.IdentityProfileMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing IdentityProfile.
 */
@Service
@Transactional
public class IdentityProfileServiceImpl implements IdentityProfileService {

    private final Logger log = LoggerFactory.getLogger(IdentityProfileServiceImpl.class);

    private final IdentityProfileRepository identityProfileRepository;

    private final IdentityProfileMapper identityProfileMapper;

    public IdentityProfileServiceImpl(IdentityProfileRepository identityProfileRepository, IdentityProfileMapper identityProfileMapper) {
        this.identityProfileRepository = identityProfileRepository;
        this.identityProfileMapper = identityProfileMapper;
    }

    /**
     * Save a identityProfile.
     *
     * @param identityProfileDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public IdentityProfileDTO save(IdentityProfileDTO identityProfileDTO) {
        log.debug("Request to save IdentityProfile : {}", identityProfileDTO);
        IdentityProfile identityProfile = identityProfileMapper.toEntity(identityProfileDTO);
        identityProfile = identityProfileRepository.save(identityProfile);
        return identityProfileMapper.toDto(identityProfile);
    }

    /**
     * Get all the identityProfiles.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<IdentityProfileDTO> findAll(Pageable pageable) {
        log.debug("Request to get all IdentityProfiles");
        return identityProfileRepository.findAll(pageable)
            .map(identityProfileMapper::toDto);
    }


    /**
     * Get one identityProfile by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<IdentityProfileDTO> findOne(Long id) {
        log.debug("Request to get IdentityProfile : {}", id);
        return identityProfileRepository.findById(id)
            .map(identityProfileMapper::toDto);
    }

    /**
     * Delete the identityProfile by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete IdentityProfile : {}", id);
        identityProfileRepository.deleteById(id);
    }
}
