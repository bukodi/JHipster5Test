package com.bukodi.jh5.gerbera.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.bukodi.jh5.gerbera.service.PhysicalCardService;
import com.bukodi.jh5.gerbera.web.rest.errors.BadRequestAlertException;
import com.bukodi.jh5.gerbera.web.rest.util.HeaderUtil;
import com.bukodi.jh5.gerbera.web.rest.util.PaginationUtil;
import com.bukodi.jh5.gerbera.service.dto.PhysicalCardDTO;
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
 * REST controller for managing PhysicalCard.
 */
@RestController
@RequestMapping("/api")
public class PhysicalCardResource {

    private final Logger log = LoggerFactory.getLogger(PhysicalCardResource.class);

    private static final String ENTITY_NAME = "physicalCard";

    private final PhysicalCardService physicalCardService;

    public PhysicalCardResource(PhysicalCardService physicalCardService) {
        this.physicalCardService = physicalCardService;
    }

    /**
     * POST  /physical-cards : Create a new physicalCard.
     *
     * @param physicalCardDTO the physicalCardDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new physicalCardDTO, or with status 400 (Bad Request) if the physicalCard has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/physical-cards")
    @Timed
    public ResponseEntity<PhysicalCardDTO> createPhysicalCard(@Valid @RequestBody PhysicalCardDTO physicalCardDTO) throws URISyntaxException {
        log.debug("REST request to save PhysicalCard : {}", physicalCardDTO);
        if (physicalCardDTO.getId() != null) {
            throw new BadRequestAlertException("A new physicalCard cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PhysicalCardDTO result = physicalCardService.save(physicalCardDTO);
        return ResponseEntity.created(new URI("/api/physical-cards/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /physical-cards : Updates an existing physicalCard.
     *
     * @param physicalCardDTO the physicalCardDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated physicalCardDTO,
     * or with status 400 (Bad Request) if the physicalCardDTO is not valid,
     * or with status 500 (Internal Server Error) if the physicalCardDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/physical-cards")
    @Timed
    public ResponseEntity<PhysicalCardDTO> updatePhysicalCard(@Valid @RequestBody PhysicalCardDTO physicalCardDTO) throws URISyntaxException {
        log.debug("REST request to update PhysicalCard : {}", physicalCardDTO);
        if (physicalCardDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PhysicalCardDTO result = physicalCardService.save(physicalCardDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, physicalCardDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /physical-cards : get all the physicalCards.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of physicalCards in body
     */
    @GetMapping("/physical-cards")
    @Timed
    public ResponseEntity<List<PhysicalCardDTO>> getAllPhysicalCards(Pageable pageable) {
        log.debug("REST request to get a page of PhysicalCards");
        Page<PhysicalCardDTO> page = physicalCardService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/physical-cards");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /physical-cards/:id : get the "id" physicalCard.
     *
     * @param id the id of the physicalCardDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the physicalCardDTO, or with status 404 (Not Found)
     */
    @GetMapping("/physical-cards/{id}")
    @Timed
    public ResponseEntity<PhysicalCardDTO> getPhysicalCard(@PathVariable Long id) {
        log.debug("REST request to get PhysicalCard : {}", id);
        Optional<PhysicalCardDTO> physicalCardDTO = physicalCardService.findOne(id);
        return ResponseUtil.wrapOrNotFound(physicalCardDTO);
    }

    /**
     * DELETE  /physical-cards/:id : delete the "id" physicalCard.
     *
     * @param id the id of the physicalCardDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/physical-cards/{id}")
    @Timed
    public ResponseEntity<Void> deletePhysicalCard(@PathVariable Long id) {
        log.debug("REST request to delete PhysicalCard : {}", id);
        physicalCardService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
