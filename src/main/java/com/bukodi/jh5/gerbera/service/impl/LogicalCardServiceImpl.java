package com.bukodi.jh5.gerbera.service.impl;

import com.bukodi.jh5.gerbera.service.LogicalCardService;
import com.bukodi.jh5.gerbera.domain.LogicalCard;
import com.bukodi.jh5.gerbera.repository.LogicalCardRepository;
import com.bukodi.jh5.gerbera.service.dto.LogicalCardDTO;
import com.bukodi.jh5.gerbera.service.mapper.LogicalCardMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing LogicalCard.
 */
@Service
@Transactional
public class LogicalCardServiceImpl implements LogicalCardService {

    private final Logger log = LoggerFactory.getLogger(LogicalCardServiceImpl.class);

    private final LogicalCardRepository logicalCardRepository;

    private final LogicalCardMapper logicalCardMapper;

    public LogicalCardServiceImpl(LogicalCardRepository logicalCardRepository, LogicalCardMapper logicalCardMapper) {
        this.logicalCardRepository = logicalCardRepository;
        this.logicalCardMapper = logicalCardMapper;
    }

    /**
     * Save a logicalCard.
     *
     * @param logicalCardDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public LogicalCardDTO save(LogicalCardDTO logicalCardDTO) {
        log.debug("Request to save LogicalCard : {}", logicalCardDTO);
        LogicalCard logicalCard = logicalCardMapper.toEntity(logicalCardDTO);
        logicalCard = logicalCardRepository.save(logicalCard);
        return logicalCardMapper.toDto(logicalCard);
    }

    /**
     * Get all the logicalCards.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<LogicalCardDTO> findAll(Pageable pageable) {
        log.debug("Request to get all LogicalCards");
        return logicalCardRepository.findAll(pageable)
            .map(logicalCardMapper::toDto);
    }

    /**
     * Get all the LogicalCard with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<LogicalCardDTO> findAllWithEagerRelationships(Pageable pageable) {
        return logicalCardRepository.findAllWithEagerRelationships(pageable).map(logicalCardMapper::toDto);
    }
    

    /**
     * Get one logicalCard by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<LogicalCardDTO> findOne(Long id) {
        log.debug("Request to get LogicalCard : {}", id);
        return logicalCardRepository.findOneWithEagerRelationships(id)
            .map(logicalCardMapper::toDto);
    }

    /**
     * Delete the logicalCard by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete LogicalCard : {}", id);
        logicalCardRepository.deleteById(id);
    }
}
