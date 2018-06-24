package com.bukodi.jh5.gerbera.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A PhysicalCard.
 */
@Entity
@Table(name = "app_physical_card")
public class PhysicalCard implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "visual_id", nullable = false)
    private String visualId;

    @ManyToOne
    @JsonIgnoreProperties("physicalCards")
    private Person owner;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("")
    private CardType type;

    @OneToMany(mappedBy = "physicalCard")
    private Set<LogicalCard> logicalCards = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVisualId() {
        return visualId;
    }

    public PhysicalCard visualId(String visualId) {
        this.visualId = visualId;
        return this;
    }

    public void setVisualId(String visualId) {
        this.visualId = visualId;
    }

    public Person getOwner() {
        return owner;
    }

    public PhysicalCard owner(Person person) {
        this.owner = person;
        return this;
    }

    public void setOwner(Person person) {
        this.owner = person;
    }

    public CardType getType() {
        return type;
    }

    public PhysicalCard type(CardType cardType) {
        this.type = cardType;
        return this;
    }

    public void setType(CardType cardType) {
        this.type = cardType;
    }

    public Set<LogicalCard> getLogicalCards() {
        return logicalCards;
    }

    public PhysicalCard logicalCards(Set<LogicalCard> logicalCards) {
        this.logicalCards = logicalCards;
        return this;
    }

    public PhysicalCard addLogicalCards(LogicalCard logicalCard) {
        this.logicalCards.add(logicalCard);
        logicalCard.setPhysicalCard(this);
        return this;
    }

    public PhysicalCard removeLogicalCards(LogicalCard logicalCard) {
        this.logicalCards.remove(logicalCard);
        logicalCard.setPhysicalCard(null);
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
        PhysicalCard physicalCard = (PhysicalCard) o;
        if (physicalCard.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), physicalCard.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PhysicalCard{" +
            "id=" + getId() +
            ", visualId='" + getVisualId() + "'" +
            "}";
    }
}
