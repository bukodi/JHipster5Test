package com.bukodi.jh5.gerbera.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

import com.bukodi.jh5.gerbera.domain.enumeration.CertificateType;

import com.bukodi.jh5.gerbera.domain.enumeration.PKILocation;

/**
 * Certificate template is template for creating certificate requests.
 */
@ApiModel(description = "Certificate template is template for creating certificate requests.")
@Entity
@Table(name = "app_cert_template")
public class CertificateTemplate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "_type", nullable = false)
    private CertificateType type;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "key_generation", nullable = false)
    private PKILocation keyGeneration;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "key_archivation", nullable = false)
    private PKILocation keyArchivation;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("templates")
    private CertificateAuthority ca;

    @ManyToOne
    @JsonIgnoreProperties("template2S")
    private CertificateAuthority ca2;

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

    public CertificateTemplate name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CertificateType getType() {
        return type;
    }

    public CertificateTemplate type(CertificateType type) {
        this.type = type;
        return this;
    }

    public void setType(CertificateType type) {
        this.type = type;
    }

    public PKILocation getKeyGeneration() {
        return keyGeneration;
    }

    public CertificateTemplate keyGeneration(PKILocation keyGeneration) {
        this.keyGeneration = keyGeneration;
        return this;
    }

    public void setKeyGeneration(PKILocation keyGeneration) {
        this.keyGeneration = keyGeneration;
    }

    public PKILocation getKeyArchivation() {
        return keyArchivation;
    }

    public CertificateTemplate keyArchivation(PKILocation keyArchivation) {
        this.keyArchivation = keyArchivation;
        return this;
    }

    public void setKeyArchivation(PKILocation keyArchivation) {
        this.keyArchivation = keyArchivation;
    }

    public CertificateAuthority getCa() {
        return ca;
    }

    public CertificateTemplate ca(CertificateAuthority certificateAuthority) {
        this.ca = certificateAuthority;
        return this;
    }

    public void setCa(CertificateAuthority certificateAuthority) {
        this.ca = certificateAuthority;
    }

    public CertificateAuthority getCa2() {
        return ca2;
    }

    public CertificateTemplate ca2(CertificateAuthority certificateAuthority) {
        this.ca2 = certificateAuthority;
        return this;
    }

    public void setCa2(CertificateAuthority certificateAuthority) {
        this.ca2 = certificateAuthority;
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
        CertificateTemplate certificateTemplate = (CertificateTemplate) o;
        if (certificateTemplate.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), certificateTemplate.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CertificateTemplate{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", type='" + getType() + "'" +
            ", keyGeneration='" + getKeyGeneration() + "'" +
            ", keyArchivation='" + getKeyArchivation() + "'" +
            "}";
    }
}
