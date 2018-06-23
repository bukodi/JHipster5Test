package com.bukodi.jh5.gerbera.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A MessageTemplate.
 */
@Entity
@Table(name = "app_message_template")
public class MessageTemplate implements Serializable {

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

    @Column(name = "template")
    private Boolean template;

    @Column(name = "source")
    private String source;

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

    public MessageTemplate instant(Instant instant) {
        this.instant = instant;
        return this;
    }

    public void setInstant(Instant instant) {
        this.instant = instant;
    }

    public String getEventTypeFqn() {
        return eventTypeFqn;
    }

    public MessageTemplate eventTypeFqn(String eventTypeFqn) {
        this.eventTypeFqn = eventTypeFqn;
        return this;
    }

    public void setEventTypeFqn(String eventTypeFqn) {
        this.eventTypeFqn = eventTypeFqn;
    }

    public Boolean isTemplate() {
        return template;
    }

    public MessageTemplate template(Boolean template) {
        this.template = template;
        return this;
    }

    public void setTemplate(Boolean template) {
        this.template = template;
    }

    public String getSource() {
        return source;
    }

    public MessageTemplate source(String source) {
        this.source = source;
        return this;
    }

    public void setSource(String source) {
        this.source = source;
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
        MessageTemplate messageTemplate = (MessageTemplate) o;
        if (messageTemplate.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), messageTemplate.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MessageTemplate{" +
            "id=" + getId() +
            ", instant='" + getInstant() + "'" +
            ", eventTypeFqn='" + getEventTypeFqn() + "'" +
            ", template='" + isTemplate() + "'" +
            ", source='" + getSource() + "'" +
            "}";
    }
}
