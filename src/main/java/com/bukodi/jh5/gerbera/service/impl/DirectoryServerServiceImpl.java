package com.bukodi.jh5.gerbera.service.impl;

import com.bukodi.jh5.gerbera.service.DirectoryServerService;
import com.bukodi.jh5.gerbera.domain.DirectoryServer;
import com.bukodi.jh5.gerbera.repository.DirectoryServerRepository;
import com.bukodi.jh5.gerbera.service.dto.DirectoryServerDTO;
import com.bukodi.jh5.gerbera.service.mapper.DirectoryServerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing DirectoryServer.
 */
@Service
@Transactional
public class DirectoryServerServiceImpl implements DirectoryServerService {

    private final Logger log = LoggerFactory.getLogger(DirectoryServerServiceImpl.class);

    private final DirectoryServerRepository directoryServerRepository;

    private final DirectoryServerMapper directoryServerMapper;

    public DirectoryServerServiceImpl(DirectoryServerRepository directoryServerRepository, DirectoryServerMapper directoryServerMapper) {
        this.directoryServerRepository = directoryServerRepository;
        this.directoryServerMapper = directoryServerMapper;
    }

    /**
     * Save a directoryServer.
     *
     * @param directoryServerDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public DirectoryServerDTO save(DirectoryServerDTO directoryServerDTO) {
        log.debug("Request to save DirectoryServer : {}", directoryServerDTO);
        DirectoryServer directoryServer = directoryServerMapper.toEntity(directoryServerDTO);
        directoryServer = directoryServerRepository.save(directoryServer);
        return directoryServerMapper.toDto(directoryServer);
    }

    /**
     * Get all the directoryServers.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DirectoryServerDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DirectoryServers");
        return directoryServerRepository.findAll(pageable)
            .map(directoryServerMapper::toDto);
    }


    /**
     * Get one directoryServer by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DirectoryServerDTO> findOne(Long id) {
        log.debug("Request to get DirectoryServer : {}", id);
        return directoryServerRepository.findById(id)
            .map(directoryServerMapper::toDto);
    }

    /**
     * Delete the directoryServer by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DirectoryServer : {}", id);
        directoryServerRepository.deleteById(id);
    }
}
