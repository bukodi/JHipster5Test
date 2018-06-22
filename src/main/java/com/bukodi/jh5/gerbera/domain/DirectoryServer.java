package com.bukodi.jh5.gerbera.domain;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DirectoryServer.
 */
@Entity
@Table(name = "app_directory_server")
public class DirectoryServer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * IP adress or hostname
     */
    @NotNull
    @ApiModelProperty(value = "IP adress or hostname", required = true)
    @Column(name = "host", nullable = false)
    private String host;

    @Column(name = "customization_source")
    private String customizationSource;

    @Lob
    @Column(name = "customization_class")
    private byte[] customizationClass;

    @Column(name = "customization_class_content_type")
    private String customizationClassContentType;

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

    public DirectoryServer name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHost() {
        return host;
    }

    public DirectoryServer host(String host) {
        this.host = host;
        return this;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getCustomizationSource() {
        return customizationSource;
    }

    public DirectoryServer customizationSource(String customizationSource) {
        this.customizationSource = customizationSource;
        return this;
    }

    public void setCustomizationSource(String customizationSource) {
        this.customizationSource = customizationSource;
    }

    public byte[] getCustomizationClass() {
        return customizationClass;
    }

    public DirectoryServer customizationClass(byte[] customizationClass) {
        this.customizationClass = customizationClass;
        return this;
    }

    public void setCustomizationClass(byte[] customizationClass) {
        this.customizationClass = customizationClass;
    }

    public String getCustomizationClassContentType() {
        return customizationClassContentType;
    }

    public DirectoryServer customizationClassContentType(String customizationClassContentType) {
        this.customizationClassContentType = customizationClassContentType;
        return this;
    }

    public void setCustomizationClassContentType(String customizationClassContentType) {
        this.customizationClassContentType = customizationClassContentType;
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
        DirectoryServer directoryServer = (DirectoryServer) o;
        if (directoryServer.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), directoryServer.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DirectoryServer{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", host='" + getHost() + "'" +
            ", customizationSource='" + getCustomizationSource() + "'" +
            ", customizationClass='" + getCustomizationClass() + "'" +
            ", customizationClassContentType='" + getCustomizationClassContentType() + "'" +
            "}";
    }
}
