package com.bukodi.jh5.gerbera.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the CertificateAuthority entity.
 */
public class CertificateAuthorityDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String host;

    private String customizationSource;

    @Lob
    private byte[] customizationClass;
    private String customizationClassContentType;

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

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getCustomizationSource() {
        return customizationSource;
    }

    public void setCustomizationSource(String customizationSource) {
        this.customizationSource = customizationSource;
    }

    public byte[] getCustomizationClass() {
        return customizationClass;
    }

    public void setCustomizationClass(byte[] customizationClass) {
        this.customizationClass = customizationClass;
    }

    public String getCustomizationClassContentType() {
        return customizationClassContentType;
    }

    public void setCustomizationClassContentType(String customizationClassContentType) {
        this.customizationClassContentType = customizationClassContentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CertificateAuthorityDTO certificateAuthorityDTO = (CertificateAuthorityDTO) o;
        if (certificateAuthorityDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), certificateAuthorityDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CertificateAuthorityDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", host='" + getHost() + "'" +
            ", customizationSource='" + getCustomizationSource() + "'" +
            ", customizationClass='" + getCustomizationClass() + "'" +
            "}";
    }
}
