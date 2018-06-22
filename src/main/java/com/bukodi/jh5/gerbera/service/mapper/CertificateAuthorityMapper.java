package com.bukodi.jh5.gerbera.service.mapper;

import com.bukodi.jh5.gerbera.domain.*;
import com.bukodi.jh5.gerbera.service.dto.CertificateAuthorityDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CertificateAuthority and its DTO CertificateAuthorityDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CertificateAuthorityMapper extends EntityMapper<CertificateAuthorityDTO, CertificateAuthority> {


    @Mapping(target = "template2S", ignore = true)
    @Mapping(target = "templates", ignore = true)
    CertificateAuthority toEntity(CertificateAuthorityDTO certificateAuthorityDTO);

    default CertificateAuthority fromId(Long id) {
        if (id == null) {
            return null;
        }
        CertificateAuthority certificateAuthority = new CertificateAuthority();
        certificateAuthority.setId(id);
        return certificateAuthority;
    }
}
