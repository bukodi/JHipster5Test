package com.bukodi.jh5.gerbera.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.bukodi.jh5.gerbera.domain.enumeration.CertificateType;
import com.bukodi.jh5.gerbera.domain.enumeration.PKILocation;
import com.bukodi.jh5.gerbera.domain.enumeration.PKILocation;

/**
 * A DTO for the CertificateTemplate entity.
 */
public class CertificateTemplateDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private CertificateType type;

    @NotNull
    private PKILocation keyGeneration;

    @NotNull
    private PKILocation keyArchivation;

    private Long caId;

    private String caName;

    private Long ca2Id;

    private String ca2Name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CertificateType getType() {
        return type;
    }

    public void setType(CertificateType type) {
        this.type = type;
    }

    public PKILocation getKeyGeneration() {
        return keyGeneration;
    }

    public void setKeyGeneration(PKILocation keyGeneration) {
        this.keyGeneration = keyGeneration;
    }

    public PKILocation getKeyArchivation() {
        return keyArchivation;
    }

    public void setKeyArchivation(PKILocation keyArchivation) {
        this.keyArchivation = keyArchivation;
    }

    public Long getCaId() {
        return caId;
    }

    public void setCaId(Long certificateAuthorityId) {
        this.caId = certificateAuthorityId;
    }

    public String getCaName() {
        return caName;
    }

    public void setCaName(String certificateAuthorityName) {
        this.caName = certificateAuthorityName;
    }

    public Long getCa2Id() {
        return ca2Id;
    }

    public void setCa2Id(Long certificateAuthorityId) {
        this.ca2Id = certificateAuthorityId;
    }

    public String getCa2Name() {
        return ca2Name;
    }

    public void setCa2Name(String certificateAuthorityName) {
        this.ca2Name = certificateAuthorityName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CertificateTemplateDTO certificateTemplateDTO = (CertificateTemplateDTO) o;
        if (certificateTemplateDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), certificateTemplateDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CertificateTemplateDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", type='" + getType() + "'" +
            ", keyGeneration='" + getKeyGeneration() + "'" +
            ", keyArchivation='" + getKeyArchivation() + "'" +
            ", ca=" + getCaId() +
            ", ca='" + getCaName() + "'" +
            ", ca2=" + getCa2Id() +
            ", ca2='" + getCa2Name() + "'" +
            "}";
    }
}
