package com.bukodi.jh5.gerbera.service.mapper;

import com.bukodi.jh5.gerbera.domain.*;
import com.bukodi.jh5.gerbera.service.dto.PhysicalCardDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity PhysicalCard and its DTO PhysicalCardDTO.
 */
@Mapper(componentModel = "spring", uses = {PersonMapper.class, CardTypeMapper.class})
public interface PhysicalCardMapper extends EntityMapper<PhysicalCardDTO, PhysicalCard> {

    @Mapping(source = "owner.id", target = "ownerId")
    @Mapping(source = "owner.login", target = "ownerLogin")
    @Mapping(source = "type.id", target = "typeId")
    @Mapping(source = "type.name", target = "typeName")
    PhysicalCardDTO toDto(PhysicalCard physicalCard);

    @Mapping(source = "ownerId", target = "owner")
    @Mapping(source = "typeId", target = "type")
    @Mapping(target = "logicalCards", ignore = true)
    PhysicalCard toEntity(PhysicalCardDTO physicalCardDTO);

    default PhysicalCard fromId(Long id) {
        if (id == null) {
            return null;
        }
        PhysicalCard physicalCard = new PhysicalCard();
        physicalCard.setId(id);
        return physicalCard;
    }
}
