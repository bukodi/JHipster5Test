package com.bukodi.jh5.gerbera.service.mapper;

import com.bukodi.jh5.gerbera.domain.*;
import com.bukodi.jh5.gerbera.service.dto.DirectoryServerDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity DirectoryServer and its DTO DirectoryServerDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DirectoryServerMapper extends EntityMapper<DirectoryServerDTO, DirectoryServer> {



    default DirectoryServer fromId(Long id) {
        if (id == null) {
            return null;
        }
        DirectoryServer directoryServer = new DirectoryServer();
        directoryServer.setId(id);
        return directoryServer;
    }
}
