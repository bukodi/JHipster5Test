package com.bukodi.jh5.gerbera.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the PhysicalCard entity.
 */
public class PhysicalCardDTO implements Serializable {

    private Long id;

    @NotNull
    private String visualId;

    private Long ownerId;

    private String ownerLogin;

    private Long typeId;

    private String typeName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVisualId() {
        return visualId;
    }

    public void setVisualId(String visualId) {
        this.visualId = visualId;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long personId) {
        this.ownerId = personId;
    }

    public String getOwnerLogin() {
        return ownerLogin;
    }

    public void setOwnerLogin(String personLogin) {
        this.ownerLogin = personLogin;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long cardTypeId) {
        this.typeId = cardTypeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String cardTypeName) {
        this.typeName = cardTypeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PhysicalCardDTO physicalCardDTO = (PhysicalCardDTO) o;
        if (physicalCardDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), physicalCardDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PhysicalCardDTO{" +
            "id=" + getId() +
            ", visualId='" + getVisualId() + "'" +
            ", owner=" + getOwnerId() +
            ", owner='" + getOwnerLogin() + "'" +
            ", type=" + getTypeId() +
            ", type='" + getTypeName() + "'" +
            "}";
    }
}
