package com.bukodi.jh5.gerbera.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the LogicalCard entity.
 */
public class LogicalCardDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    private Long physicalCardId;

    private String physicalCardVisualId;

    private Set<CertificateDTO> certificates = new HashSet<>();

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

    public Long getPhysicalCardId() {
        return physicalCardId;
    }

    public void setPhysicalCardId(Long physicalCardId) {
        this.physicalCardId = physicalCardId;
    }

    public String getPhysicalCardVisualId() {
        return physicalCardVisualId;
    }

    public void setPhysicalCardVisualId(String physicalCardVisualId) {
        this.physicalCardVisualId = physicalCardVisualId;
    }

    public Set<CertificateDTO> getCertificates() {
        return certificates;
    }

    public void setCertificates(Set<CertificateDTO> certificates) {
        this.certificates = certificates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LogicalCardDTO logicalCardDTO = (LogicalCardDTO) o;
        if (logicalCardDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), logicalCardDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LogicalCardDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", physicalCard=" + getPhysicalCardId() +
            ", physicalCard='" + getPhysicalCardVisualId() + "'" +
            "}";
    }
}
