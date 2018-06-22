package com.bukodi.jh5.gerbera.service.mapper;

import com.bukodi.jh5.gerbera.domain.*;
import com.bukodi.jh5.gerbera.service.dto.CustomProcessDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CustomProcess and its DTO CustomProcessDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CustomProcessMapper extends EntityMapper<CustomProcessDTO, CustomProcess> {



    default CustomProcess fromId(Long id) {
        if (id == null) {
            return null;
        }
        CustomProcess customProcess = new CustomProcess();
        customProcess.setId(id);
        return customProcess;
    }
}
