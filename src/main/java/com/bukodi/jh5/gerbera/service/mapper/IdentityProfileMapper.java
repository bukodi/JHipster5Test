package com.bukodi.jh5.gerbera.service.mapper;

import com.bukodi.jh5.gerbera.domain.*;
import com.bukodi.jh5.gerbera.service.dto.IdentityProfileDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity IdentityProfile and its DTO IdentityProfileDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface IdentityProfileMapper extends EntityMapper<IdentityProfileDTO, IdentityProfile> {


    @Mapping(target = "identities", ignore = true)
    IdentityProfile toEntity(IdentityProfileDTO identityProfileDTO);

    default IdentityProfile fromId(Long id) {
        if (id == null) {
            return null;
        }
        IdentityProfile identityProfile = new IdentityProfile();
        identityProfile.setId(id);
        return identityProfile;
    }
}
