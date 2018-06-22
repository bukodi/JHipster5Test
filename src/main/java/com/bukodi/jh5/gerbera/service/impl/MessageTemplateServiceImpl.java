package com.bukodi.jh5.gerbera.service.impl;

import com.bukodi.jh5.gerbera.service.MessageTemplateService;
import com.bukodi.jh5.gerbera.domain.MessageTemplate;
import com.bukodi.jh5.gerbera.repository.MessageTemplateRepository;
import com.bukodi.jh5.gerbera.service.dto.MessageTemplateDTO;
import com.bukodi.jh5.gerbera.service.mapper.MessageTemplateMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing MessageTemplate.
 */
@Service
@Transactional
public class MessageTemplateServiceImpl implements MessageTemplateService {

    private final Logger log = LoggerFactory.getLogger(MessageTemplateServiceImpl.class);

    private final MessageTemplateRepository messageTemplateRepository;

    private final MessageTemplateMapper messageTemplateMapper;

    public MessageTemplateServiceImpl(MessageTemplateRepository messageTemplateRepository, MessageTemplateMapper messageTemplateMapper) {
        this.messageTemplateRepository = messageTemplateRepository;
        this.messageTemplateMapper = messageTemplateMapper;
    }

    /**
     * Save a messageTemplate.
     *
     * @param messageTemplateDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public MessageTemplateDTO save(MessageTemplateDTO messageTemplateDTO) {
        log.debug("Request to save MessageTemplate : {}", messageTemplateDTO);
        MessageTemplate messageTemplate = messageTemplateMapper.toEntity(messageTemplateDTO);
        messageTemplate = messageTemplateRepository.save(messageTemplate);
        return messageTemplateMapper.toDto(messageTemplate);
    }

    /**
     * Get all the messageTemplates.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MessageTemplateDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MessageTemplates");
        return messageTemplateRepository.findAll(pageable)
            .map(messageTemplateMapper::toDto);
    }


    /**
     * Get one messageTemplate by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MessageTemplateDTO> findOne(Long id) {
        log.debug("Request to get MessageTemplate : {}", id);
        return messageTemplateRepository.findById(id)
            .map(messageTemplateMapper::toDto);
    }

    /**
     * Delete the messageTemplate by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MessageTemplate : {}", id);
        messageTemplateRepository.deleteById(id);
    }
}
