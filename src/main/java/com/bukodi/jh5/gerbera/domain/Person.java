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
 * Person represent a real natural person.
 */
@ApiModel(description = "Person represent a real natural person.")
@Entity
@Table(name = "app_person")
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "title")
    private String title;

    @NotNull
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotNull
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "id_card")
    private String idCard;

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "city")
    private String city;

    @Column(name = "address")
    private String address;

    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "additional_info")
    private String additionalInfo;

    @OneToMany(mappedBy = "realPerson")
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

    public Person name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public Person title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public Person firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Person lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIdCard() {
        return idCard;
    }

    public Person idCard(String idCard) {
        this.idCard = idCard;
        return this;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getZipCode() {
        return zipCode;
    }

    public Person zipCode(String zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public Person city(String city) {
        this.city = city;
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public Person address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public Person email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public Person phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public Person additionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
        return this;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public Set<Identity> getIdentities() {
        return identities;
    }

    public Person identities(Set<Identity> identities) {
        this.identities = identities;
        return this;
    }

    public Person addIdentities(Identity identity) {
        this.identities.add(identity);
        identity.setRealPerson(this);
        return this;
    }

    public Person removeIdentities(Identity identity) {
        this.identities.remove(identity);
        identity.setRealPerson(null);
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
        Person person = (Person) o;
        if (person.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), person.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Person{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", title='" + getTitle() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", idCard='" + getIdCard() + "'" +
            ", zipCode='" + getZipCode() + "'" +
            ", city='" + getCity() + "'" +
            ", address='" + getAddress() + "'" +
            ", email='" + getEmail() + "'" +
            ", phone='" + getPhone() + "'" +
            ", additionalInfo='" + getAdditionalInfo() + "'" +
            "}";
    }
}
