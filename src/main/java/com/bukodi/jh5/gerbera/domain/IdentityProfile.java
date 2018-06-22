package com.bukodi.jh5.gerbera.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * Identity profile is template for creating identities.
 */
@ApiModel(description = "Identity profile is template for creating identities.")
@Entity
@Table(name = "app_identity_profile")
public class IdentityProfile implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "profile")
    private Set<Identity> identities = new HashSet<>();

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

    public IdentityProfile name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Identity> getIdentities() {
        return identities;
    }

    public IdentityProfile identities(Set<Identity> identities) {
        this.identities = identities;
        return this;
    }

    public IdentityProfile addIdentities(Identity identity) {
        this.identities.add(identity);
        identity.setProfile(this);
        return this;
    }

    public IdentityProfile removeIdentities(Identity identity) {
        this.identities.remove(identity);
        identity.setProfile(null);
        return this;
    }

    public void setIdentities(Set<Identity> identities) {
        this.identities = identities;
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
        IdentityProfile identityProfile = (IdentityProfile) o;
        if (identityProfile.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), identityProfile.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "IdentityProfile{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
