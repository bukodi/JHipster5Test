package com.bukodi.jh5.gerbera.service.mapper;

import com.bukodi.jh5.gerbera.domain.*;
import com.bukodi.jh5.gerbera.service.dto.CertificateTemplateDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CertificateTemplate and its DTO CertificateTemplateDTO.
 */
@Mapper(componentModel = "spring", uses = {CertificateAuthorityMapper.class})
public interface CertificateTemplateMapper extends EntityMapper<CertificateTemplateDTO, CertificateTemplate> {

    @Mapping(source = "ca.id", target = "caId")
    @Mapping(source = "ca.name", target = "caName")
    @Mapping(source = "ca2.id", target = "ca2Id")
    @Mapping(source = "ca2.name", target = "ca2Name")
    CertificateTemplateDTO toDto(CertificateTemplate certificateTemplate);

    @Mapping(source = "caId", target = "ca")
    @Mapping(source = "ca2Id", target = "ca2")
    CertificateTemplate toEntity(CertificateTemplateDTO certificateTemplateDTO);

    default CertificateTemplate fromId(Long id) {
        if (id == null) {
            return null;
        }
        CertificateTemplate certificateTemplate = new CertificateTemplate();
        certificateTemplate.setId(id);
        return certificateTemplate;
    }
}
