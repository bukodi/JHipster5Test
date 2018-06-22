package com.bukodi.jh5.gerbera.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * This an identity of a person.
 * Typical use case, when
 */
@ApiModel(description = "This an identity of a person. Typical use case, when")
@Entity
@Table(name = "app_identity")
public class Identity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "external_name", nullable = false)
    private String externalName;

    @OneToMany(mappedBy = "identity")
    private Set<Certificate> certificates = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("identities")
    private IdentityProfile profile;

    @ManyToOne
    @JsonIgnoreProperties("")
    private DirectoryServer sourceSystem;

    @ManyToOne
    @JsonIgnoreProperties("identities")
    private Person realPerson;

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

    public Identity name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExternalName() {
        return externalName;
    }

    public Identity externalName(String externalName) {
        this.externalName = externalName;
        return this;
    }

    public void setExternalName(String externalName) {
        this.externalName = externalName;
    }

    public Set<Certificate> getCertificates() {
        return certificates;
    }

    public Identity certificates(Set<Certificate> certificates) {
        this.certificates = certificates;
        return this;
    }

    public Identity addCertificates(Certificate certificate) {
        this.certificates.add(certificate);
        certificate.setIdentity(this);
        return this;
    }

    public Identity removeCertificates(Certificate certificate) {
        this.certificates.remove(certificate);
        certificate.setIdentity(null);
        return this;
    }

    public void setCertificates(Set<Certificate> certificates) {
        this.certificates = certificates;
    }

    public IdentityProfile getProfile() {
        return profile;
    }

    public Identity profile(IdentityProfile identityProfile) {
        this.profile = identityProfile;
        return this;
    }

    public void setProfile(IdentityProfile identityProfile) {
        this.profile = identityProfile;
    }

    public DirectoryServer getSourceSystem() {
        return sourceSystem;
    }

    public Identity sourceSystem(DirectoryServer directoryServer) {
        this.sourceSystem = directoryServer;
        return this;
    }

    public void setSourceSystem(DirectoryServer directoryServer) {
        this.sourceSystem = directoryServer;
    }

    public Person getRealPerson() {
        return realPerson;
    }

    public Identity realPerson(Person person) {
        this.realPerson = person;
        return this;
    }

    public void setRealPerson(Person person) {
        this.realPerson = person;
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
        Identity identity = (Identity) o;
        if (identity.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), identity.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Identity{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", externalName='" + getExternalName() + "'" +
            "}";
    }
}
