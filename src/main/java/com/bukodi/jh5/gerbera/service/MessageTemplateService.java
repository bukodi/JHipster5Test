package com.bukodi.jh5.gerbera.service;

import com.bukodi.jh5.gerbera.service.dto.MessageTemplateDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing MessageTemplate.
 */
public interface MessageTemplateService {

    /**
     * Save a messageTemplate.
     *
     * @param messageTemplateDTO the entity to save
     * @return the persisted entity
     */
    MessageTemplateDTO save(MessageTemplateDTO messageTemplateDTO);

    /**
     * Get all the messageTemplates.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<MessageTemplateDTO> findAll(Pageable pageable);


    /**
     * Get the "id" messageTemplate.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<MessageTemplateDTO> findOne(Long id);

    /**
     * Delete the "id" messageTemplate.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
