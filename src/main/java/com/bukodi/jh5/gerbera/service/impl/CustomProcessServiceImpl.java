package com.bukodi.jh5.gerbera.service.impl;

import com.bukodi.jh5.gerbera.service.CustomProcessService;
import com.bukodi.jh5.gerbera.domain.CustomProcess;
import com.bukodi.jh5.gerbera.repository.CustomProcessRepository;
import com.bukodi.jh5.gerbera.service.dto.CustomProcessDTO;
import com.bukodi.jh5.gerbera.service.mapper.CustomProcessMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing CustomProcess.
 */
@Service
@Transactional
public class CustomProcessServiceImpl implements CustomProcessService {

    private final Logger log = LoggerFactory.getLogger(CustomProcessServiceImpl.class);

    private final CustomProcessRepository customProcessRepository;

    private final CustomProcessMapper customProcessMapper;

    public CustomProcessServiceImpl(CustomProcessRepository customProcessRepository, CustomProcessMapper customProcessMapper) {
        this.customProcessRepository = customProcessRepository;
        this.customProcessMapper = customProcessMapper;
    }

    /**
     * Save a customProcess.
     *
     * @param customProcessDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CustomProcessDTO save(CustomProcessDTO customProcessDTO) {
        log.debug("Request to save CustomProcess : {}", customProcessDTO);
        CustomProcess customProcess = customProcessMapper.toEntity(customProcessDTO);
        customProcess = customProcessRepository.save(customProcess);
        return customProcessMapper.toDto(customProcess);
    }

    /**
     * Get all the customProcesses.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CustomProcessDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CustomProcesses");
        return customProcessRepository.findAll(pageable)
            .map(customProcessMapper::toDto);
    }


    /**
     * Get one customProcess by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CustomProcessDTO> findOne(Long id) {
        log.debug("Request to get CustomProcess : {}", id);
        return customProcessRepository.findById(id)
            .map(customProcessMapper::toDto);
    }

    /**
     * Delete the customProcess by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CustomProcess : {}", id);
        customProcessRepository.deleteById(id);
    }
}
