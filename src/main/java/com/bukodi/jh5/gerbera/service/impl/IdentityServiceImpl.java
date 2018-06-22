package com.bukodi.jh5.gerbera.service.impl;

import com.bukodi.jh5.gerbera.service.IdentityService;
import com.bukodi.jh5.gerbera.domain.Identity;
import com.bukodi.jh5.gerbera.repository.IdentityRepository;
import com.bukodi.jh5.gerbera.service.dto.IdentityDTO;
import com.bukodi.jh5.gerbera.service.mapper.IdentityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing Identity.
 */
@Service
@Transactional
public class IdentityServiceImpl implements IdentityService {

    private final Logger log = LoggerFactory.getLogger(IdentityServiceImpl.class);

    private final IdentityRepository identityRepository;

    private final IdentityMapper identityMapper;

    public IdentityServiceImpl(IdentityRepository identityRepository, IdentityMapper identityMapper) {
        this.identityRepository = identityRepository;
        this.identityMapper = identityMapper;
    }

    /**
     * Save a identity.
     *
     * @param identityDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public IdentityDTO save(IdentityDTO identityDTO) {
        log.debug("Request to save Identity : {}", identityDTO);
        Identity identity = identityMapper.toEntity(identityDTO);
        identity = identityRepository.save(identity);
        return identityMapper.toDto(identity);
    }

    /**
     * Get all the identities.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<IdentityDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Identities");
        return identityRepository.findAll(pageable)
            .map(identityMapper::toDto);
    }


    /**
     * Get one identity by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<IdentityDTO> findOne(Long id) {
        log.debug("Request to get Identity : {}", id);
        return identityRepository.findById(id)
            .map(identityMapper::toDto);
    }

    /**
     * Delete the identity by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Identity : {}", id);
        identityRepository.deleteById(id);
    }
}
