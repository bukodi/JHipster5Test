package com.bukodi.jh5.gerbera.service.impl;

import com.bukodi.jh5.gerbera.service.PhysicalCardService;
import com.bukodi.jh5.gerbera.domain.PhysicalCard;
import com.bukodi.jh5.gerbera.repository.PhysicalCardRepository;
import com.bukodi.jh5.gerbera.service.dto.PhysicalCardDTO;
import com.bukodi.jh5.gerbera.service.mapper.PhysicalCardMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing PhysicalCard.
 */
@Service
@Transactional
public class PhysicalCardServiceImpl implements PhysicalCardService {

    private final Logger log = LoggerFactory.getLogger(PhysicalCardServiceImpl.class);

    private final PhysicalCardRepository physicalCardRepository;

    private final PhysicalCardMapper physicalCardMapper;

    public PhysicalCardServiceImpl(PhysicalCardRepository physicalCardRepository, PhysicalCardMapper physicalCardMapper) {
        this.physicalCardRepository = physicalCardRepository;
        this.physicalCardMapper = physicalCardMapper;
    }

    /**
     * Save a physicalCard.
     *
     * @param physicalCardDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public PhysicalCardDTO save(PhysicalCardDTO physicalCardDTO) {
        log.debug("Request to save PhysicalCard : {}", physicalCardDTO);
        PhysicalCard physicalCard = physicalCardMapper.toEntity(physicalCardDTO);
        physicalCard = physicalCardRepository.save(physicalCard);
        return physicalCardMapper.toDto(physicalCard);
    }

    /**
     * Get all the physicalCards.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PhysicalCardDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PhysicalCards");
        return physicalCardRepository.findAll(pageable)
            .map(physicalCardMapper::toDto);
    }


    /**
     * Get one physicalCard by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PhysicalCardDTO> findOne(Long id) {
        log.debug("Request to get PhysicalCard : {}", id);
        return physicalCardRepository.findById(id)
            .map(physicalCardMapper::toDto);
    }

    /**
     * Delete the physicalCard by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PhysicalCard : {}", id);
        physicalCardRepository.deleteById(id);
    }
}
