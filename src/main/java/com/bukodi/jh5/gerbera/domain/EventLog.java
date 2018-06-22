package com.bukodi.jh5.gerbera.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A EventLog.
 */
@Entity
@Table(name = "app_event_log")
public class EventLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "instant", nullable = false)
    private Instant instant;

    @NotNull
    @Column(name = "event_type_fqn", nullable = false)
    private String eventTypeFqn;

    @NotNull
    @Column(name = "node", nullable = false)
    private String node;

    @NotNull
    @Column(name = "serach_key", nullable = false)
    private String serachKey;

    @Column(name = "payload")
    private String payload;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getInstant() {
        return instant;
    }

    public EventLog instant(Instant instant) {
        this.instant = instant;
        return this;
    }

    public void setInstant(Instant instant) {
        this.instant = instant;
    }

    public String getEventTypeFqn() {
        return eventTypeFqn;
    }

    public EventLog eventTypeFqn(String eventTypeFqn) {
        this.eventTypeFqn = eventTypeFqn;
        return this;
    }

    public void setEventTypeFqn(String eventTypeFqn) {
        this.eventTypeFqn = eventTypeFqn;
    }

    public String getNode() {
        return node;
    }

    public EventLog node(String node) {
        this.node = node;
        return this;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getSerachKey() {
        return serachKey;
    }

    public EventLog serachKey(String serachKey) {
        this.serachKey = serachKey;
        return this;
    }

    public void setSerachKey(String serachKey) {
        this.serachKey = serachKey;
    }

    public String getPayload() {
        return payload;
    }

    public EventLog payload(String payload) {
        this.payload = payload;
        return this;
    }

    public void setPayload(String payload) {
        this.payload = payload;
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
        EventLog eventLog = (EventLog) o;
        if (eventLog.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), eventLog.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EventLog{" +
            "id=" + getId() +
            ", instant='" + getInstant() + "'" +
            ", eventTypeFqn='" + getEventTypeFqn() + "'" +
            ", node='" + getNode() + "'" +
            ", serachKey='" + getSerachKey() + "'" +
            ", payload='" + getPayload() + "'" +
            "}";
    }
}
