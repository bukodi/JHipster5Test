package com.bukodi.jh5.gerbera.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * Certificate Authority is a server component, what used for creating certificates.
 * Known CA types are: Microsoft CA, EJB CA, OpenSSL
 */
@ApiModel(description = "Certificate Authority is a server component, what used for creating certificates. Known CA types are: Microsoft CA, EJB CA, OpenSSL")
@Entity
@Table(name = "app_ca")
public class CertificateAuthority implements Serializable {

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

    @OneToMany(mappedBy = "ca2")
    private Set<CertificateTemplate> template2S = new HashSet<>();

    @OneToMany(mappedBy = "ca")
    private Set<CertificateTemplate> templates = new HashSet<>();

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

    public CertificateAuthority name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHost() {
        return host;
    }

    public CertificateAuthority host(String host) {
        this.host = host;
        return this;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getCustomizationSource() {
        return customizationSource;
    }

    public CertificateAuthority customizationSource(String customizationSource) {
        this.customizationSource = customizationSource;
        return this;
    }

    public void setCustomizationSource(String customizationSource) {
        this.customizationSource = customizationSource;
    }

    public byte[] getCustomizationClass() {
        return customizationClass;
    }

    public CertificateAuthority customizationClass(byte[] customizationClass) {
        this.customizationClass = customizationClass;
        return this;
    }

    public void setCustomizationClass(byte[] customizationClass) {
        this.customizationClass = customizationClass;
    }

    public String getCustomizationClassContentType() {
        return customizationClassContentType;
    }

    public CertificateAuthority customizationClassContentType(String customizationClassContentType) {
        this.customizationClassContentType = customizationClassContentType;
        return this;
    }

    public void setCustomizationClassContentType(String customizationClassContentType) {
        this.customizationClassContentType = customizationClassContentType;
    }

    public Set<CertificateTemplate> getTemplate2S() {
        return template2S;
    }

    public CertificateAuthority template2S(Set<CertificateTemplate> certificateTemplates) {
        this.template2S = certificateTemplates;
        return this;
    }

    public CertificateAuthority addTemplate2(CertificateTemplate certificateTemplate) {
        this.template2S.add(certificateTemplate);
        certificateTemplate.setCa2(this);
        return this;
    }

    public CertificateAuthority removeTemplate2(CertificateTemplate certificateTemplate) {
        this.template2S.remove(certificateTemplate);
        certificateTemplate.setCa2(null);
        return this;
    }

    public void setTemplate2S(Set<CertificateTemplate> certificateTemplates) {
        this.template2S = certificateTemplates;
    }

    public Set<CertificateTemplate> getTemplates() {
        return templates;
    }

    public CertificateAuthority templates(Set<CertificateTemplate> certificateTemplates) {
        this.templates = certificateTemplates;
        return this;
    }

    public CertificateAuthority addTemplate(CertificateTemplate certificateTemplate) {
        this.templates.add(certificateTemplate);
        certificateTemplate.setCa(this);
        return this;
    }

    public CertificateAuthority removeTemplate(CertificateTemplate certificateTemplate) {
        this.templates.remove(certificateTemplate);
        certificateTemplate.setCa(null);
        return this;
    }

    public void setTemplates(Set<CertificateTemplate> certificateTemplates) {
        this.templates = certificateTemplates;
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
        CertificateAuthority certificateAuthority = (CertificateAuthority) o;
        if (certificateAuthority.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), certificateAuthority.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CertificateAuthority{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", host='" + getHost() + "'" +
            ", customizationSource='" + getCustomizationSource() + "'" +
            ", customizationClass='" + getCustomizationClass() + "'" +
            ", customizationClassContentType='" + getCustomizationClassContentType() + "'" +
            "}";
    }
}
