package com.bukodi.jh5.gerbera.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A LogicalCard.
 */
@Entity
@Table(name = "app_logical_card")
public class LogicalCard implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("logicalCards")
    private PhysicalCard physicalCard;

    @ManyToMany
    @JoinTable(name = "logical_card_certificates",
               joinColumns = @JoinColumn(name = "logical_cards_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "certificates_id", referencedColumnName = "id"))
    private Set<Certificate> certificates = new HashSet<>();

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

    public LogicalCard name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PhysicalCard getPhysicalCard() {
        return physicalCard;
    }

    public LogicalCard physicalCard(PhysicalCard physicalCard) {
        this.physicalCard = physicalCard;
        return this;
    }

    public void setPhysicalCard(PhysicalCard physicalCard) {
        this.physicalCard = physicalCard;
    }

    public Set<Certificate> getCertificates() {
        return certificates;
    }

    public LogicalCard certificates(Set<Certificate> certificates) {
        this.certificates = certificates;
        return this;
    }

    public LogicalCard addCertificates(Certificate certificate) {
        this.certificates.add(certificate);
        certificate.getLogicalCards().add(this);
        return this;
    }

    public LogicalCard removeCertificates(Certificate certificate) {
        this.certificates.remove(certificate);
        certificate.getLogicalCards().remove(this);
        return this;
    }

    public void setCertificates(Set<Certificate> certificates) {
        this.certificates = certificates;
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
        LogicalCard logicalCard = (LogicalCard) o;
        if (logicalCard.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), logicalCard.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LogicalCard{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
