package com.bukodi.jh5.gerbera.service.mapper;

import com.bukodi.jh5.gerbera.domain.*;
import com.bukodi.jh5.gerbera.service.dto.EventLogDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity EventLog and its DTO EventLogDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EventLogMapper extends EntityMapper<EventLogDTO, EventLog> {



    default EventLog fromId(Long id) {
        if (id == null) {
            return null;
        }
        EventLog eventLog = new EventLog();
        eventLog.setId(id);
        return eventLog;
    }
}
