package com.bukodi.jh5.gerbera.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.bukodi.jh5.gerbera.service.EventLogService;
import com.bukodi.jh5.gerbera.web.rest.errors.BadRequestAlertException;
import com.bukodi.jh5.gerbera.web.rest.util.HeaderUtil;
import com.bukodi.jh5.gerbera.web.rest.util.PaginationUtil;
import com.bukodi.jh5.gerbera.service.dto.EventLogDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing EventLog.
 */
@RestController
@RequestMapping("/api")
public class EventLogResource {

    private final Logger log = LoggerFactory.getLogger(EventLogResource.class);

    private static final String ENTITY_NAME = "eventLog";

    private final EventLogService eventLogService;

    public EventLogResource(EventLogService eventLogService) {
        this.eventLogService = eventLogService;
    }

    /**
     * POST  /event-logs : Create a new eventLog.
     *
     * @param eventLogDTO the eventLogDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new eventLogDTO, or with status 400 (Bad Request) if the eventLog has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/event-logs")
    @Timed
    public ResponseEntity<EventLogDTO> createEventLog(@Valid @RequestBody EventLogDTO eventLogDTO) throws URISyntaxException {
        log.debug("REST request to save EventLog : {}", eventLogDTO);
        if (eventLogDTO.getId() != null) {
            throw new BadRequestAlertException("A new eventLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EventLogDTO result = eventLogService.save(eventLogDTO);
        return ResponseEntity.created(new URI("/api/event-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /event-logs : Updates an existing eventLog.
     *
     * @param eventLogDTO the eventLogDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated eventLogDTO,
     * or with status 400 (Bad Request) if the eventLogDTO is not valid,
     * or with status 500 (Internal Server Error) if the eventLogDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/event-logs")
    @Timed
    public ResponseEntity<EventLogDTO> updateEventLog(@Valid @RequestBody EventLogDTO eventLogDTO) throws URISyntaxException {
        log.debug("REST request to update EventLog : {}", eventLogDTO);
        if (eventLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EventLogDTO result = eventLogService.save(eventLogDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, eventLogDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /event-logs : get all the eventLogs.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of eventLogs in body
     */
    @GetMapping("/event-logs")
    @Timed
    public ResponseEntity<List<EventLogDTO>> getAllEventLogs(Pageable pageable) {
        log.debug("REST request to get a page of EventLogs");
        Page<EventLogDTO> page = eventLogService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/event-logs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /event-logs/:id : get the "id" eventLog.
     *
     * @param id the id of the eventLogDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the eventLogDTO, or with status 404 (Not Found)
     */
    @GetMapping("/event-logs/{id}")
    @Timed
    public ResponseEntity<EventLogDTO> getEventLog(@PathVariable Long id) {
        log.debug("REST request to get EventLog : {}", id);
        Optional<EventLogDTO> eventLogDTO = eventLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(eventLogDTO);
    }

    /**
     * DELETE  /event-logs/:id : delete the "id" eventLog.
     *
     * @param id the id of the eventLogDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/event-logs/{id}")
    @Timed
    public ResponseEntity<Void> deleteEventLog(@PathVariable Long id) {
        log.debug("REST request to delete EventLog : {}", id);
        eventLogService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
