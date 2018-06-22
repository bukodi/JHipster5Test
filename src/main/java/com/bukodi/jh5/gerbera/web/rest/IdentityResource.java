package com.bukodi.jh5.gerbera.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.bukodi.jh5.gerbera.service.IdentityService;
import com.bukodi.jh5.gerbera.web.rest.errors.BadRequestAlertException;
import com.bukodi.jh5.gerbera.web.rest.util.HeaderUtil;
import com.bukodi.jh5.gerbera.web.rest.util.PaginationUtil;
import com.bukodi.jh5.gerbera.service.dto.IdentityDTO;
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
 * REST controller for managing Identity.
 */
@RestController
@RequestMapping("/api")
public class IdentityResource {

    private final Logger log = LoggerFactory.getLogger(IdentityResource.class);

    private static final String ENTITY_NAME = "identity";

    private final IdentityService identityService;

    public IdentityResource(IdentityService identityService) {
        this.identityService = identityService;
    }

    /**
     * POST  /identities : Create a new identity.
     *
     * @param identityDTO the identityDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new identityDTO, or with status 400 (Bad Request) if the identity has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/identities")
    @Timed
    public ResponseEntity<IdentityDTO> createIdentity(@Valid @RequestBody IdentityDTO identityDTO) throws URISyntaxException {
        log.debug("REST request to save Identity : {}", identityDTO);
        if (identityDTO.getId() != null) {
            throw new BadRequestAlertException("A new identity cannot already have an ID", ENTITY_NAME, "idexists");
        }
        IdentityDTO result = identityService.save(identityDTO);
        return ResponseEntity.created(new URI("/api/identities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /identities : Updates an existing identity.
     *
     * @param identityDTO the identityDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated identityDTO,
     * or with status 400 (Bad Request) if the identityDTO is not valid,
     * or with status 500 (Internal Server Error) if the identityDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/identities")
    @Timed
    public ResponseEntity<IdentityDTO> updateIdentity(@Valid @RequestBody IdentityDTO identityDTO) throws URISyntaxException {
        log.debug("REST request to update Identity : {}", identityDTO);
        if (identityDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        IdentityDTO result = identityService.save(identityDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, identityDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /identities : get all the identities.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of identities in body
     */
    @GetMapping("/identities")
    @Timed
    public ResponseEntity<List<IdentityDTO>> getAllIdentities(Pageable pageable) {
        log.debug("REST request to get a page of Identities");
        Page<IdentityDTO> page = identityService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/identities");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /identities/:id : get the "id" identity.
     *
     * @param id the id of the identityDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the identityDTO, or with status 404 (Not Found)
     */
    @GetMapping("/identities/{id}")
    @Timed
    public ResponseEntity<IdentityDTO> getIdentity(@PathVariable Long id) {
        log.debug("REST request to get Identity : {}", id);
        Optional<IdentityDTO> identityDTO = identityService.findOne(id);
        return ResponseUtil.wrapOrNotFound(identityDTO);
    }

    /**
     * DELETE  /identities/:id : delete the "id" identity.
     *
     * @param id the id of the identityDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/identities/{id}")
    @Timed
    public ResponseEntity<Void> deleteIdentity(@PathVariable Long id) {
        log.debug("REST request to delete Identity : {}", id);
        identityService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
