package com.bukodi.jh5.gerbera.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the MessageTemplate entity.
 */
public class MessageTemplateDTO implements Serializable {

    private Long id;

    @NotNull
    private Instant instant;

    @NotNull
    private String eventTypeFqn;

    private Boolean template;

    private String source;

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

    public Boolean isTemplate() {
        return template;
    }

    public void setTemplate(Boolean template) {
        this.template = template;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MessageTemplateDTO messageTemplateDTO = (MessageTemplateDTO) o;
        if (messageTemplateDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), messageTemplateDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MessageTemplateDTO{" +
            "id=" + getId() +
            ", instant='" + getInstant() + "'" +
            ", eventTypeFqn='" + getEventTypeFqn() + "'" +
            ", template='" + isTemplate() + "'" +
            ", source='" + getSource() + "'" +
            "}";
    }
}
