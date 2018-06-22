package com.bukodi.jh5.gerbera.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.bukodi.jh5.gerbera.service.CertificateAuthorityService;
import com.bukodi.jh5.gerbera.web.rest.errors.BadRequestAlertException;
import com.bukodi.jh5.gerbera.web.rest.util.HeaderUtil;
import com.bukodi.jh5.gerbera.web.rest.util.PaginationUtil;
import com.bukodi.jh5.gerbera.service.dto.CertificateAuthorityDTO;
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
 * REST controller for managing CertificateAuthority.
 */
@RestController
@RequestMapping("/api")
public class CertificateAuthorityResource {

    private final Logger log = LoggerFactory.getLogger(CertificateAuthorityResource.class);

    private static final String ENTITY_NAME = "certificateAuthority";

    private final CertificateAuthorityService certificateAuthorityService;

    public CertificateAuthorityResource(CertificateAuthorityService certificateAuthorityService) {
        this.certificateAuthorityService = certificateAuthorityService;
    }

    /**
     * POST  /certificate-authorities : Create a new certificateAuthority.
     *
     * @param certificateAuthorityDTO the certificateAuthorityDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new certificateAuthorityDTO, or with status 400 (Bad Request) if the certificateAuthority has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/certificate-authorities")
    @Timed
    public ResponseEntity<CertificateAuthorityDTO> createCertificateAuthority(@Valid @RequestBody CertificateAuthorityDTO certificateAuthorityDTO) throws URISyntaxException {
        log.debug("REST request to save CertificateAuthority : {}", certificateAuthorityDTO);
        if (certificateAuthorityDTO.getId() != null) {
            throw new BadRequestAlertException("A new certificateAuthority cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CertificateAuthorityDTO result = certificateAuthorityService.save(certificateAuthorityDTO);
        return ResponseEntity.created(new URI("/api/certificate-authorities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /certificate-authorities : Updates an existing certificateAuthority.
     *
     * @param certificateAuthorityDTO the certificateAuthorityDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated certificateAuthorityDTO,
     * or with status 400 (Bad Request) if the certificateAuthorityDTO is not valid,
     * or with status 500 (Internal Server Error) if the certificateAuthorityDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/certificate-authorities")
    @Timed
    public ResponseEntity<CertificateAuthorityDTO> updateCertificateAuthority(@Valid @RequestBody CertificateAuthorityDTO certificateAuthorityDTO) throws URISyntaxException {
        log.debug("REST request to update CertificateAuthority : {}", certificateAuthorityDTO);
        if (certificateAuthorityDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CertificateAuthorityDTO result = certificateAuthorityService.save(certificateAuthorityDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, certificateAuthorityDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /certificate-authorities : get all the certificateAuthorities.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of certificateAuthorities in body
     */
    @GetMapping("/certificate-authorities")
    @Timed
    public ResponseEntity<List<CertificateAuthorityDTO>> getAllCertificateAuthorities(Pageable pageable) {
        log.debug("REST request to get a page of CertificateAuthorities");
        Page<CertificateAuthorityDTO> page = certificateAuthorityService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/certificate-authorities");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /certificate-authorities/:id : get the "id" certificateAuthority.
     *
     * @param id the id of the certificateAuthorityDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the certificateAuthorityDTO, or with status 404 (Not Found)
     */
    @GetMapping("/certificate-authorities/{id}")
    @Timed
    public ResponseEntity<CertificateAuthorityDTO> getCertificateAuthority(@PathVariable Long id) {
        log.debug("REST request to get CertificateAuthority : {}", id);
        Optional<CertificateAuthorityDTO> certificateAuthorityDTO = certificateAuthorityService.findOne(id);
        return ResponseUtil.wrapOrNotFound(certificateAuthorityDTO);
    }

    /**
     * DELETE  /certificate-authorities/:id : delete the "id" certificateAuthority.
     *
     * @param id the id of the certificateAuthorityDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/certificate-authorities/{id}")
    @Timed
    public ResponseEntity<Void> deleteCertificateAuthority(@PathVariable Long id) {
        log.debug("REST request to delete CertificateAuthority : {}", id);
        certificateAuthorityService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
