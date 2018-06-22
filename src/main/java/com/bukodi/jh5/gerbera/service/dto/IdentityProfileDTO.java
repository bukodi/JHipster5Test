package com.bukodi.jh5.gerbera.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the IdentityProfile entity.
 */
public class IdentityProfileDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        IdentityProfileDTO identityProfileDTO = (IdentityProfileDTO) o;
        if (identityProfileDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), identityProfileDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "IdentityProfileDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
