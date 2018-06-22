package com.bukodi.jh5.gerbera.service.mapper;

import com.bukodi.jh5.gerbera.domain.*;
import com.bukodi.jh5.gerbera.service.dto.CertificateDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Certificate and its DTO CertificateDTO.
 */
@Mapper(componentModel = "spring", uses = {CertificateAuthorityMapper.class, CertificateTemplateMapper.class, IdentityMapper.class})
public interface CertificateMapper extends EntityMapper<CertificateDTO, Certificate> {

    @Mapping(source = "ca.id", target = "caId")
    @Mapping(source = "ca.name", target = "caName")
    @Mapping(source = "template.id", target = "templateId")
    @Mapping(source = "template.name", target = "templateName")
    @Mapping(source = "identity.id", target = "identityId")
    @Mapping(source = "identity.name", target = "identityName")
    CertificateDTO toDto(Certificate certificate);

    @Mapping(source = "caId", target = "ca")
    @Mapping(source = "templateId", target = "template")
    @Mapping(source = "identityId", target = "identity")
    @Mapping(target = "logicalCards", ignore = true)
    Certificate toEntity(CertificateDTO certificateDTO);

    default Certificate fromId(Long id) {
        if (id == null) {
            return null;
        }
        Certificate certificate = new Certificate();
        certificate.setId(id);
        return certificate;
    }
}
