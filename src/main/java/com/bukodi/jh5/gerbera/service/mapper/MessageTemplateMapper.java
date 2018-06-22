package com.bukodi.jh5.gerbera.service.mapper;

import com.bukodi.jh5.gerbera.domain.*;
import com.bukodi.jh5.gerbera.service.dto.MessageTemplateDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity MessageTemplate and its DTO MessageTemplateDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MessageTemplateMapper extends EntityMapper<MessageTemplateDTO, MessageTemplate> {



    default MessageTemplate fromId(Long id) {
        if (id == null) {
            return null;
        }
        MessageTemplate messageTemplate = new MessageTemplate();
        messageTemplate.setId(id);
        return messageTemplate;
    }
}
