package com.bukodi.jh5.gerbera.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.bukodi.jh5.gerbera.service.CustomProcessService;
import com.bukodi.jh5.gerbera.web.rest.errors.BadRequestAlertException;
import com.bukodi.jh5.gerbera.web.rest.util.HeaderUtil;
import com.bukodi.jh5.gerbera.web.rest.util.PaginationUtil;
import com.bukodi.jh5.gerbera.service.dto.CustomProcessDTO;
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
 * REST controller for managing CustomProcess.
 */
@RestController
@RequestMapping("/api")
public class CustomProcessResource {

    private final Logger log = LoggerFactory.getLogger(CustomProcessResource.class);

    private static final String ENTITY_NAME = "customProcess";

    private final CustomProcessService customProcessService;

    public CustomProcessResource(CustomProcessService customProcessService) {
        this.customProcessService = customProcessService;
    }

    /**
     * POST  /custom-processes : Create a new customProcess.
     *
     * @param customProcessDTO the customProcessDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new customProcessDTO, or with status 400 (Bad Request) if the customProcess has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/custom-processes")
    @Timed
    public ResponseEntity<CustomProcessDTO> createCustomProcess(@Valid @RequestBody CustomProcessDTO customProcessDTO) throws URISyntaxException {
        log.debug("REST request to save CustomProcess : {}", customProcessDTO);
        if (customProcessDTO.getId() != null) {
            throw new BadRequestAlertException("A new customProcess cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CustomProcessDTO result = customProcessService.save(customProcessDTO);
        return ResponseEntity.created(new URI("/api/custom-processes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /custom-processes : Updates an existing customProcess.
     *
     * @param customProcessDTO the customProcessDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated customProcessDTO,
     * or with status 400 (Bad Request) if the customProcessDTO is not valid,
     * or with status 500 (Internal Server Error) if the customProcessDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/custom-processes")
    @Timed
    public ResponseEntity<CustomProcessDTO> updateCustomProcess(@Valid @RequestBody CustomProcessDTO customProcessDTO) throws URISyntaxException {
        log.debug("REST request to update CustomProcess : {}", customProcessDTO);
        if (customProcessDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CustomProcessDTO result = customProcessService.save(customProcessDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, customProcessDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /custom-processes : get all the customProcesses.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of customProcesses in body
     */
    @GetMapping("/custom-processes")
    @Timed
    public ResponseEntity<List<CustomProcessDTO>> getAllCustomProcesses(Pageable pageable) {
        log.debug("REST request to get a page of CustomProcesses");
        Page<CustomProcessDTO> page = customProcessService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/custom-processes");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /custom-processes/:id : get the "id" customProcess.
     *
     * @param id the id of the customProcessDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the customProcessDTO, or with status 404 (Not Found)
     */
    @GetMapping("/custom-processes/{id}")
    @Timed
    public ResponseEntity<CustomProcessDTO> getCustomProcess(@PathVariable Long id) {
        log.debug("REST request to get CustomProcess : {}", id);
        Optional<CustomProcessDTO> customProcessDTO = customProcessService.findOne(id);
        return ResponseUtil.wrapOrNotFound(customProcessDTO);
    }

    /**
     * DELETE  /custom-processes/:id : delete the "id" customProcess.
     *
     * @param id the id of the customProcessDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/custom-processes/{id}")
    @Timed
    public ResponseEntity<Void> deleteCustomProcess(@PathVariable Long id) {
        log.debug("REST request to delete CustomProcess : {}", id);
        customProcessService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
