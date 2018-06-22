package com.bukodi.jh5.gerbera.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.bukodi.jh5.gerbera.service.LogicalCardService;
import com.bukodi.jh5.gerbera.web.rest.errors.BadRequestAlertException;
import com.bukodi.jh5.gerbera.web.rest.util.HeaderUtil;
import com.bukodi.jh5.gerbera.web.rest.util.PaginationUtil;
import com.bukodi.jh5.gerbera.service.dto.LogicalCardDTO;
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
 * REST controller for managing LogicalCard.
 */
@RestController
@RequestMapping("/api")
public class LogicalCardResource {

    private final Logger log = LoggerFactory.getLogger(LogicalCardResource.class);

    private static final String ENTITY_NAME = "logicalCard";

    private final LogicalCardService logicalCardService;

    public LogicalCardResource(LogicalCardService logicalCardService) {
        this.logicalCardService = logicalCardService;
    }

    /**
     * POST  /logical-cards : Create a new logicalCard.
     *
     * @param logicalCardDTO the logicalCardDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new logicalCardDTO, or with status 400 (Bad Request) if the logicalCard has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/logical-cards")
    @Timed
    public ResponseEntity<LogicalCardDTO> createLogicalCard(@Valid @RequestBody LogicalCardDTO logicalCardDTO) throws URISyntaxException {
        log.debug("REST request to save LogicalCard : {}", logicalCardDTO);
        if (logicalCardDTO.getId() != null) {
            throw new BadRequestAlertException("A new logicalCard cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LogicalCardDTO result = logicalCardService.save(logicalCardDTO);
        return ResponseEntity.created(new URI("/api/logical-cards/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /logical-cards : Updates an existing logicalCard.
     *
     * @param logicalCardDTO the logicalCardDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated logicalCardDTO,
     * or with status 400 (Bad Request) if the logicalCardDTO is not valid,
     * or with status 500 (Internal Server Error) if the logicalCardDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/logical-cards")
    @Timed
    public ResponseEntity<LogicalCardDTO> updateLogicalCard(@Valid @RequestBody LogicalCardDTO logicalCardDTO) throws URISyntaxException {
        log.debug("REST request to update LogicalCard : {}", logicalCardDTO);
        if (logicalCardDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LogicalCardDTO result = logicalCardService.save(logicalCardDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, logicalCardDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /logical-cards : get all the logicalCards.
     *
     * @param pageable the pagination information
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many)
     * @return the ResponseEntity with status 200 (OK) and the list of logicalCards in body
     */
    @GetMapping("/logical-cards")
    @Timed
    public ResponseEntity<List<LogicalCardDTO>> getAllLogicalCards(Pageable pageable, @RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get a page of LogicalCards");
        Page<LogicalCardDTO> page;
        if (eagerload) {
            page = logicalCardService.findAllWithEagerRelationships(pageable);
        } else {
            page = logicalCardService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, String.format("/api/logical-cards?eagerload=%b", eagerload));
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /logical-cards/:id : get the "id" logicalCard.
     *
     * @param id the id of the logicalCardDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the logicalCardDTO, or with status 404 (Not Found)
     */
    @GetMapping("/logical-cards/{id}")
    @Timed
    public ResponseEntity<LogicalCardDTO> getLogicalCard(@PathVariable Long id) {
        log.debug("REST request to get LogicalCard : {}", id);
        Optional<LogicalCardDTO> logicalCardDTO = logicalCardService.findOne(id);
        return ResponseUtil.wrapOrNotFound(logicalCardDTO);
    }

    /**
     * DELETE  /logical-cards/:id : delete the "id" logicalCard.
     *
     * @param id the id of the logicalCardDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/logical-cards/{id}")
    @Timed
    public ResponseEntity<Void> deleteLogicalCard(@PathVariable Long id) {
        log.debug("REST request to delete LogicalCard : {}", id);
        logicalCardService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
