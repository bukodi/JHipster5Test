package com.bukodi.jh5.gerbera.service.mapper;

import com.bukodi.jh5.gerbera.domain.*;
import com.bukodi.jh5.gerbera.service.dto.IdentityDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Identity and its DTO IdentityDTO.
 */
@Mapper(componentModel = "spring", uses = {IdentityProfileMapper.class, DirectoryServerMapper.class, PersonMapper.class})
public interface IdentityMapper extends EntityMapper<IdentityDTO, Identity> {

    @Mapping(source = "profile.id", target = "profileId")
    @Mapping(source = "profile.name", target = "profileName")
    @Mapping(source = "sourceSystem.id", target = "sourceSystemId")
    @Mapping(source = "sourceSystem.name", target = "sourceSystemName")
    @Mapping(source = "realPerson.id", target = "realPersonId")
    @Mapping(source = "realPerson.name", target = "realPersonName")
    IdentityDTO toDto(Identity identity);

    @Mapping(target = "certificates", ignore = true)
    @Mapping(source = "profileId", target = "profile")
    @Mapping(source = "sourceSystemId", target = "sourceSystem")
    @Mapping(source = "realPersonId", target = "realPerson")
    Identity toEntity(IdentityDTO identityDTO);

    default Identity fromId(Long id) {
        if (id == null) {
            return null;
        }
        Identity identity = new Identity();
        identity.setId(id);
        return identity;
    }
}
