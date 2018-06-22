package com.bukodi.jh5.gerbera.service.mapper;

import com.bukodi.jh5.gerbera.domain.*;
import com.bukodi.jh5.gerbera.service.dto.LogicalCardDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity LogicalCard and its DTO LogicalCardDTO.
 */
@Mapper(componentModel = "spring", uses = {PhysicalCardMapper.class, CertificateMapper.class})
public interface LogicalCardMapper extends EntityMapper<LogicalCardDTO, LogicalCard> {

    @Mapping(source = "physicalCard.id", target = "physicalCardId")
    @Mapping(source = "physicalCard.visualId", target = "physicalCardVisualId")
    LogicalCardDTO toDto(LogicalCard logicalCard);

    @Mapping(source = "physicalCardId", target = "physicalCard")
    LogicalCard toEntity(LogicalCardDTO logicalCardDTO);

    default LogicalCard fromId(Long id) {
        if (id == null) {
            return null;
        }
        LogicalCard logicalCard = new LogicalCard();
        logicalCard.setId(id);
        return logicalCard;
    }
}
