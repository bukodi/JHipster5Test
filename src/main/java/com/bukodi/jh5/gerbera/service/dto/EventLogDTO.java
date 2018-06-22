package com.bukodi.jh5.gerbera.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the EventLog entity.
 */
public class EventLogDTO implements Serializable {

    private Long id;

    @NotNull
    private Instant instant;

    @NotNull
    private String eventTypeFqn;

    @NotNull
    private String node;

    @NotNull
    private String serachKey;

    private String payload;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getInstant() {
        return instant;
    }

    public void setInstant(Instant instant) {
        this.instant = instant;
    }

    public String getEventTypeFqn() {
        return eventTypeFqn;
    }

    public void setEventTypeFqn(String eventTypeFqn) {
        this.eventTypeFqn = eventTypeFqn;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getSerachKey() {
        return serachKey;
    }

    public void setSerachKey(String serachKey) {
        this.serachKey = serachKey;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EventLogDTO eventLogDTO = (EventLogDTO) o;
        if (eventLogDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), eventLogDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EventLogDTO{" +
            "id=" + getId() +
            ", instant='" + getInstant() + "'" +
            ", eventTypeFqn='" + getEventTypeFqn() + "'" +
            ", node='" + getNode() + "'" +
            ", serachKey='" + getSerachKey() + "'" +
            ", payload='" + getPayload() + "'" +
            "}";
    }
}
