package com.bukodi.jh5.gerbera.service.impl;

import com.bukodi.jh5.gerbera.service.EventLogService;
import com.bukodi.jh5.gerbera.domain.EventLog;
import com.bukodi.jh5.gerbera.repository.EventLogRepository;
import com.bukodi.jh5.gerbera.service.dto.EventLogDTO;
import com.bukodi.jh5.gerbera.service.mapper.EventLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing EventLog.
 */
@Service
@Transactional
public class EventLogServiceImpl implements EventLogService {

    private final Logger log = LoggerFactory.getLogger(EventLogServiceImpl.class);

    private final EventLogRepository eventLogRepository;

    private final EventLogMapper eventLogMapper;

    public EventLogServiceImpl(EventLogRepository eventLogRepository, EventLogMapper eventLogMapper) {
        this.eventLogRepository = eventLogRepository;
        this.eventLogMapper = eventLogMapper;
    }

    /**
     * Save a eventLog.
     *
     * @param eventLogDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public EventLogDTO save(EventLogDTO eventLogDTO) {
        log.debug("Request to save EventLog : {}", eventLogDTO);
        EventLog eventLog = eventLogMapper.toEntity(eventLogDTO);
        eventLog = eventLogRepository.save(eventLog);
        return eventLogMapper.toDto(eventLog);
    }

    /**
     * Get all the eventLogs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EventLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EventLogs");
        return eventLogRepository.findAll(pageable)
            .map(eventLogMapper::toDto);
    }


    /**
     * Get one eventLog by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EventLogDTO> findOne(Long id) {
        log.debug("Request to get EventLog : {}", id);
        return eventLogRepository.findById(id)
            .map(eventLogMapper::toDto);
    }

    /**
     * Delete the eventLog by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EventLog : {}", id);
        eventLogRepository.deleteById(id);
    }
}
