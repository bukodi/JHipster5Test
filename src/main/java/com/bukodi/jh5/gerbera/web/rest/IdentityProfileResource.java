package com.bukodi.jh5.gerbera.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.bukodi.jh5.gerbera.service.IdentityProfileService;
import com.bukodi.jh5.gerbera.web.rest.errors.BadRequestAlertException;
import com.bukodi.jh5.gerbera.web.rest.util.HeaderUtil;
import com.bukodi.jh5.gerbera.web.rest.util.PaginationUtil;
import com.bukodi.jh5.gerbera.service.dto.IdentityProfileDTO;
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
 * REST controller for managing IdentityProfile.
 */
@RestController
@RequestMapping("/api")
public class IdentityProfileResource {

    private final Logger log = LoggerFactory.getLogger(IdentityProfileResource.class);

    private static final String ENTITY_NAME = "identityProfile";

    private final IdentityProfileService identityProfileService;

    public IdentityProfileResource(IdentityProfileService identityProfileService) {
        this.identityProfileService = identityProfileService;
    }

    /**
     * POST  /identity-profiles : Create a new identityProfile.
     *
     * @param identityProfileDTO the identityProfileDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new identityProfileDTO, or with status 400 (Bad Request) if the identityProfile has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/identity-profiles")
    @Timed
    public ResponseEntity<IdentityProfileDTO> createIdentityProfile(@Valid @RequestBody IdentityProfileDTO identityProfileDTO) throws URISyntaxException {
        log.debug("REST request to save IdentityProfile : {}", identityProfileDTO);
        if (identityProfileDTO.getId() != null) {
            throw new BadRequestAlertException("A new identityProfile cannot already have an ID", ENTITY_NAME, "idexists");
        }
        IdentityProfileDTO result = identityProfileService.save(identityProfileDTO);
        return ResponseEntity.created(new URI("/api/identity-profiles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /identity-profiles : Updates an existing identityProfile.
     *
     * @param identityProfileDTO the identityProfileDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated identityProfileDTO,
     * or with status 400 (Bad Request) if the identityProfileDTO is not valid,
     * or with status 500 (Internal Server Error) if the identityProfileDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/identity-profiles")
    @Timed
    public ResponseEntity<IdentityProfileDTO> updateIdentityProfile(@Valid @RequestBody IdentityProfileDTO identityProfileDTO) throws URISyntaxException {
        log.debug("REST request to update IdentityProfile : {}", identityProfileDTO);
        if (identityProfileDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        IdentityProfileDTO result = identityProfileService.save(identityProfileDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, identityProfileDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /identity-profiles : get all the identityProfiles.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of identityProfiles in body
     */
    @GetMapping("/identity-profiles")
    @Timed
    public ResponseEntity<List<IdentityProfileDTO>> getAllIdentityProfiles(Pageable pageable) {
        log.debug("REST request to get a page of IdentityProfiles");
        Page<IdentityProfileDTO> page = identityProfileService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/identity-profiles");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /identity-profiles/:id : get the "id" identityProfile.
     *
     * @param id the id of the identityProfileDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the identityProfileDTO, or with status 404 (Not Found)
     */
    @GetMapping("/identity-profiles/{id}")
    @Timed
    public ResponseEntity<IdentityProfileDTO> getIdentityProfile(@PathVariable Long id) {
        log.debug("REST request to get IdentityProfile : {}", id);
        Optional<IdentityProfileDTO> identityProfileDTO = identityProfileService.findOne(id);
        return ResponseUtil.wrapOrNotFound(identityProfileDTO);
    }

    /**
     * DELETE  /identity-profiles/:id : delete the "id" identityProfile.
     *
     * @param id the id of the identityProfileDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/identity-profiles/{id}")
    @Timed
    public ResponseEntity<Void> deleteIdentityProfile(@PathVariable Long id) {
        log.debug("REST request to delete IdentityProfile : {}", id);
        identityProfileService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
