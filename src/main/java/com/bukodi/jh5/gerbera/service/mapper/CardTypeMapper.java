package com.bukodi.jh5.gerbera.service.mapper;

import com.bukodi.jh5.gerbera.domain.*;
import com.bukodi.jh5.gerbera.service.dto.CardTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CardType and its DTO CardTypeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CardTypeMapper extends EntityMapper<CardTypeDTO, CardType> {



    default CardType fromId(Long id) {
        if (id == null) {
            return null;
        }
        CardType cardType = new CardType();
        cardType.setId(id);
        return cardType;
    }
}
