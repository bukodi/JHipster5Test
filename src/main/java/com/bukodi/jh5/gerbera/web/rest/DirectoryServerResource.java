package com.bukodi.jh5.gerbera.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.bukodi.jh5.gerbera.service.DirectoryServerService;
import com.bukodi.jh5.gerbera.web.rest.errors.BadRequestAlertException;
import com.bukodi.jh5.gerbera.web.rest.util.HeaderUtil;
import com.bukodi.jh5.gerbera.web.rest.util.PaginationUtil;
import com.bukodi.jh5.gerbera.service.dto.DirectoryServerDTO;
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
 * REST controller for managing DirectoryServer.
 */
@RestController
@RequestMapping("/api")
public class DirectoryServerResource {

    private final Logger log = LoggerFactory.getLogger(DirectoryServerResource.class);

    private static final String ENTITY_NAME = "directoryServer";

    private final DirectoryServerService directoryServerService;

    public DirectoryServerResource(DirectoryServerService directoryServerService) {
        this.directoryServerService = directoryServerService;
    }

    /**
     * POST  /directory-servers : Create a new directoryServer.
     *
     * @param directoryServerDTO the directoryServerDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new directoryServerDTO, or with status 400 (Bad Request) if the directoryServer has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/directory-servers")
    @Timed
    public ResponseEntity<DirectoryServerDTO> createDirectoryServer(@Valid @RequestBody DirectoryServerDTO directoryServerDTO) throws URISyntaxException {
        log.debug("REST request to save DirectoryServer : {}", directoryServerDTO);
        if (directoryServerDTO.getId() != null) {
            throw new BadRequestAlertException("A new directoryServer cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DirectoryServerDTO result = directoryServerService.save(directoryServerDTO);
        return ResponseEntity.created(new URI("/api/directory-servers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /directory-servers : Updates an existing directoryServer.
     *
     * @param directoryServerDTO the directoryServerDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated directoryServerDTO,
     * or with status 400 (Bad Request) if the directoryServerDTO is not valid,
     * or with status 500 (Internal Server Error) if the directoryServerDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/directory-servers")
    @Timed
    public ResponseEntity<DirectoryServerDTO> updateDirectoryServer(@Valid @RequestBody DirectoryServerDTO directoryServerDTO) throws URISyntaxException {
        log.debug("REST request to update DirectoryServer : {}", directoryServerDTO);
        if (directoryServerDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DirectoryServerDTO result = directoryServerService.save(directoryServerDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, directoryServerDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /directory-servers : get all the directoryServers.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of directoryServers in body
     */
    @GetMapping("/directory-servers")
    @Timed
    public ResponseEntity<List<DirectoryServerDTO>> getAllDirectoryServers(Pageable pageable) {
        log.debug("REST request to get a page of DirectoryServers");
        Page<DirectoryServerDTO> page = directoryServerService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/directory-servers");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /directory-servers/:id : get the "id" directoryServer.
     *
     * @param id the id of the directoryServerDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the directoryServerDTO, or with status 404 (Not Found)
     */
    @GetMapping("/directory-servers/{id}")
    @Timed
    public ResponseEntity<DirectoryServerDTO> getDirectoryServer(@PathVariable Long id) {
        log.debug("REST request to get DirectoryServer : {}", id);
        Optional<DirectoryServerDTO> directoryServerDTO = directoryServerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(directoryServerDTO);
    }

    /**
     * DELETE  /directory-servers/:id : delete the "id" directoryServer.
     *
     * @param id the id of the directoryServerDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/directory-servers/{id}")
    @Timed
    public ResponseEntity<Void> deleteDirectoryServer(@PathVariable Long id) {
        log.debug("REST request to delete DirectoryServer : {}", id);
        directoryServerService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
