package com.bukodi.jh5.gerbera.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;
import com.bukodi.jh5.gerbera.domain.enumeration.CertificateType;

/**
 * A DTO for the Certificate entity.
 */
public class CertificateDTO implements Serializable {

    private Long id;

    @NotNull
    private String subjectName;

    private String serialNumber;

    @NotNull
    private CertificateType type;

    @Lob
    private byte[] certData;
    private String certDataContentType;

    @Lob
    private byte[] privateKey;
    private String privateKeyContentType;

    private Long caId;

    private String caName;

    private Long templateId;

    private String templateName;

    private Long identityId;

    private String identityName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public CertificateType getType() {
        return type;
    }

    public void setType(CertificateType type) {
        this.type = type;
    }

    public byte[] getCertData() {
        return certData;
    }

    public void setCertData(byte[] certData) {
        this.certData = certData;
    }

    public String getCertDataContentType() {
        return certDataContentType;
    }

    public void setCertDataContentType(String certDataContentType) {
        this.certDataContentType = certDataContentType;
    }

    public byte[] getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(byte[] privateKey) {
        this.privateKey = privateKey;
    }

    public String getPrivateKeyContentType() {
        return privateKeyContentType;
    }

    public void setPrivateKeyContentType(String privateKeyContentType) {
        this.privateKeyContentType = privateKeyContentType;
    }

    public Long getCaId() {
        return caId;
    }

    public void setCaId(Long certificateAuthorityId) {
        this.caId = certificateAuthorityId;
    }

    public String getCaName() {
        return caName;
    }

    public void setCaName(String certificateAuthorityName) {
        this.caName = certificateAuthorityName;
    }

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long certificateTemplateId) {
        this.templateId = certificateTemplateId;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String certificateTemplateName) {
        this.templateName = certificateTemplateName;
    }

    public Long getIdentityId() {
        return identityId;
    }

    public void setIdentityId(Long identityId) {
        this.identityId = identityId;
    }

    public String getIdentityName() {
        return identityName;
    }

    public void setIdentityName(String identityName) {
        this.identityName = identityName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CertificateDTO certificateDTO = (CertificateDTO) o;
        if (certificateDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), certificateDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CertificateDTO{" +
            "id=" + getId() +
            ", subjectName='" + getSubjectName() + "'" +
            ", serialNumber='" + getSerialNumber() + "'" +
            ", type='" + getType() + "'" +
            ", certData='" + getCertData() + "'" +
            ", privateKey='" + getPrivateKey() + "'" +
            ", ca=" + getCaId() +
            ", ca='" + getCaName() + "'" +
            ", template=" + getTemplateId() +
            ", template='" + getTemplateName() + "'" +
            ", identity=" + getIdentityId() +
            ", identity='" + getIdentityName() + "'" +
            "}";
    }
}
