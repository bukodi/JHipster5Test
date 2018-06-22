package com.bukodi.jh5.gerbera.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Identity entity.
 */
public class IdentityDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String externalName;

    private Long profileId;

    private String profileName;

    private Long sourceSystemId;

    private String sourceSystemName;

    private Long realPersonId;

    private String realPersonName;

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

    public String getExternalName() {
        return externalName;
    }

    public void setExternalName(String externalName) {
        this.externalName = externalName;
    }

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long identityProfileId) {
        this.profileId = identityProfileId;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String identityProfileName) {
        this.profileName = identityProfileName;
    }

    public Long getSourceSystemId() {
        return sourceSystemId;
    }

    public void setSourceSystemId(Long directoryServerId) {
        this.sourceSystemId = directoryServerId;
    }

    public String getSourceSystemName() {
        return sourceSystemName;
    }

    public void setSourceSystemName(String directoryServerName) {
        this.sourceSystemName = directoryServerName;
    }

    public Long getRealPersonId() {
        return realPersonId;
    }

    public void setRealPersonId(Long personId) {
        this.realPersonId = personId;
    }

    public String getRealPersonName() {
        return realPersonName;
    }

    public void setRealPersonName(String personName) {
        this.realPersonName = personName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        IdentityDTO identityDTO = (IdentityDTO) o;
        if (identityDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), identityDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "IdentityDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", externalName='" + getExternalName() + "'" +
            ", profile=" + getProfileId() +
            ", profile='" + getProfileName() + "'" +
            ", sourceSystem=" + getSourceSystemId() +
            ", sourceSystem='" + getSourceSystemName() + "'" +
            ", realPerson=" + getRealPersonId() +
            ", realPerson='" + getRealPersonName() + "'" +
            "}";
    }
}
