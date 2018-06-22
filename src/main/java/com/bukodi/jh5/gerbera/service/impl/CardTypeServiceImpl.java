package com.bukodi.jh5.gerbera.service.impl;

import com.bukodi.jh5.gerbera.service.CardTypeService;
import com.bukodi.jh5.gerbera.domain.CardType;
import com.bukodi.jh5.gerbera.repository.CardTypeRepository;
import com.bukodi.jh5.gerbera.service.dto.CardTypeDTO;
import com.bukodi.jh5.gerbera.service.mapper.CardTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing CardType.
 */
@Service
@Transactional
public class CardTypeServiceImpl implements CardTypeService {

    private final Logger log = LoggerFactory.getLogger(CardTypeServiceImpl.class);

    private final CardTypeRepository cardTypeRepository;

    private final CardTypeMapper cardTypeMapper;

    public CardTypeServiceImpl(CardTypeRepository cardTypeRepository, CardTypeMapper cardTypeMapper) {
        this.cardTypeRepository = cardTypeRepository;
        this.cardTypeMapper = cardTypeMapper;
    }

    /**
     * Save a cardType.
     *
     * @param cardTypeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CardTypeDTO save(CardTypeDTO cardTypeDTO) {
        log.debug("Request to save CardType : {}", cardTypeDTO);
        CardType cardType = cardTypeMapper.toEntity(cardTypeDTO);
        cardType = cardTypeRepository.save(cardType);
        return cardTypeMapper.toDto(cardType);
    }

    /**
     * Get all the cardTypes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CardTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CardTypes");
        return cardTypeRepository.findAll(pageable)
            .map(cardTypeMapper::toDto);
    }


    /**
     * Get one cardType by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CardTypeDTO> findOne(Long id) {
        log.debug("Request to get CardType : {}", id);
        return cardTypeRepository.findById(id)
            .map(cardTypeMapper::toDto);
    }

    /**
     * Delete the cardType by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CardType : {}", id);
        cardTypeRepository.deleteById(id);
    }
}
