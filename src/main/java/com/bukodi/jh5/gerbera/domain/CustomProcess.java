package com.bukodi.jh5.gerbera.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A CustomProcess.
 */
@Entity
@Table(name = "app_process")
public class CustomProcess implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "interface_fqn", nullable = false)
    private String interfaceFqn;

    @Column(name = "template")
    private Boolean template;

    @Column(name = "source")
    private String source;

    @Column(name = "scheduling")
    private String scheduling;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public CustomProcess name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInterfaceFqn() {
        return interfaceFqn;
    }

    public CustomProcess interfaceFqn(String interfaceFqn) {
        this.interfaceFqn = interfaceFqn;
        return this;
    }

    public void setInterfaceFqn(String interfaceFqn) {
        this.interfaceFqn = interfaceFqn;
    }

    public Boolean isTemplate() {
        return template;
    }

    public CustomProcess template(Boolean template) {
        this.template = template;
        return this;
    }

    public void setTemplate(Boolean template) {
        this.template = template;
    }

    public String getSource() {
        return source;
    }

    public CustomProcess source(String source) {
        this.source = source;
        return this;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getScheduling() {
        return scheduling;
    }

    public CustomProcess scheduling(String scheduling) {
        this.scheduling = scheduling;
        return this;
    }

    public void setScheduling(String scheduling) {
        this.scheduling = scheduling;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CustomProcess customProcess = (CustomProcess) o;
        if (customProcess.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), customProcess.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CustomProcess{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", interfaceFqn='" + getInterfaceFqn() + "'" +
            ", template='" + isTemplate() + "'" +
            ", source='" + getSource() + "'" +
            ", scheduling='" + getScheduling() + "'" +
            "}";
    }
}
