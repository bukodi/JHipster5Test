package com.bukodi.jh5.gerbera.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import com.bukodi.jh5.gerbera.domain.enumeration.CertificateType;

/**
 * A Certificate.
 */
@Entity
@Table(name = "app_certificate")
public class Certificate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "subject_name", nullable = false)
    private String subjectName;

    @Column(name = "serial_number")
    private String serialNumber;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "_type", nullable = false)
    private CertificateType type;

    @Lob
    @Column(name = "cert_data")
    private byte[] certData;

    @Column(name = "cert_data_content_type")
    private String certDataContentType;

    @Lob
    @Column(name = "private_key")
    private byte[] privateKey;

    @Column(name = "private_key_content_type")
    private String privateKeyContentType;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("")
    private CertificateAuthority ca;

    @ManyToOne
    @JsonIgnoreProperties("")
    private CertificateTemplate template;

    @ManyToOne
    @JsonIgnoreProperties("certificates")
    private Identity identity;

    @ManyToMany(mappedBy = "certificates")
    @JsonIgnore
    private Set<LogicalCard> logicalCards = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public Certificate subjectName(String subjectName) {
        this.subjectName = subjectName;
        return this;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public Certificate serialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
        return this;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public CertificateType getType() {
        return type;
    }

    public Certificate type(CertificateType type) {
        this.type = type;
        return this;
    }

    public void setType(CertificateType type) {
        this.type = type;
    }

    public byte[] getCertData() {
        return certData;
    }

    public Certificate certData(byte[] certData) {
        this.certData = certData;
        return this;
    }

    public void setCertData(byte[] certData) {
        this.certData = certData;
    }

    public String getCertDataContentType() {
        return certDataContentType;
    }

    public Certificate certDataContentType(String certDataContentType) {
        this.certDataContentType = certDataContentType;
        return this;
    }

    public void setCertDataContentType(String certDataContentType) {
        this.certDataContentType = certDataContentType;
    }

    public byte[] getPrivateKey() {
        return privateKey;
    }

    public Certificate privateKey(byte[] privateKey) {
        this.privateKey = privateKey;
        return this;
    }

    public void setPrivateKey(byte[] privateKey) {
        this.privateKey = privateKey;
    }

    public String getPrivateKeyContentType() {
        return privateKeyContentType;
    }

    public Certificate privateKeyContentType(String privateKeyContentType) {
        this.privateKeyContentType = privateKeyContentType;
        return this;
    }

    public void setPrivateKeyContentType(String privateKeyContentType) {
        this.privateKeyContentType = privateKeyContentType;
    }

    public CertificateAuthority getCa() {
        return ca;
    }

    public Certificate ca(CertificateAuthority certificateAuthority) {
        this.ca = certificateAuthority;
        return this;
    }

    public void setCa(CertificateAuthority certificateAuthority) {
        this.ca = certificateAuthority;
    }

    public CertificateTemplate getTemplate() {
        return template;
    }

    public Certificate template(CertificateTemplate certificateTemplate) {
        this.template = certificateTemplate;
        return this;
    }

    public void setTemplate(CertificateTemplate certificateTemplate) {
        this.template = certificateTemplate;
    }

    public Identity getIdentity() {
        return identity;
    }

    public Certificate identity(Identity identity) {
        this.identity = identity;
        return this;
    }

    public void setIdentity(Identity identity) {
        this.identity = identity;
    }

    public Set<LogicalCard> getLogicalCards() {
        return logicalCards;
    }

    public Certificate logicalCards(Set<LogicalCard> logicalCards) {
        this.logicalCards = logicalCards;
        return this;
    }

    public Certificate addLogicalCards(LogicalCard logicalCard) {
        this.logicalCards.add(logicalCard);
        logicalCard.getCertificates().add(this);
        return this;
    }

    public Certificate removeLogicalCards(LogicalCard logicalCard) {
        this.logicalCards.remove(logicalCard);
        logicalCard.getCertificates().remove(this);
        return this;
    }

    public void setLogicalCards(Set<LogicalCard> logicalCards) {
        this.logicalCards = logicalCards;
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
        Certificate certificate = (Certificate) o;
        if (certificate.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), certificate.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Certificate{" +
            "id=" + getId() +
            ", subjectName='" + getSubjectName() + "'" +
            ", serialNumber='" + getSerialNumber() + "'" +
            ", type='" + getType() + "'" +
            ", certData='" + getCertData() + "'" +
            ", certDataContentType='" + getCertDataContentType() + "'" +
            ", privateKey='" + getPrivateKey() + "'" +
            ", privateKeyContentType='" + getPrivateKeyContentType() + "'" +
            "}";
    }
}
