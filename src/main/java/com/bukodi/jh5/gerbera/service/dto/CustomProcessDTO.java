package com.bukodi.jh5.gerbera.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the CustomProcess entity.
 */
public class CustomProcessDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String interfaceFqn;

    private Boolean template;

    private String source;

    private String scheduling;

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

    public String getInterfaceFqn() {
        return interfaceFqn;
    }

    public void setInterfaceFqn(String interfaceFqn) {
        this.interfaceFqn = interfaceFqn;
    }

    public Boolean isTemplate() {
        return template;
    }

    public void setTemplate(Boolean template) {
        this.template = template;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getScheduling() {
        return scheduling;
    }

    public void setScheduling(String scheduling) {
        this.scheduling = scheduling;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CustomProcessDTO customProcessDTO = (CustomProcessDTO) o;
        if (customProcessDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), customProcessDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CustomProcessDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", interfaceFqn='" + getInterfaceFqn() + "'" +
            ", template='" + isTemplate() + "'" +
            ", source='" + getSource() + "'" +
            ", scheduling='" + getScheduling() + "'" +
            "}";
    }
}
