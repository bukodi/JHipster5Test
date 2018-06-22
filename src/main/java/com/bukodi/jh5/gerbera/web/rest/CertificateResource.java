package com.bukodi.jh5.gerbera.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.bukodi.jh5.gerbera.service.CertificateService;
import com.bukodi.jh5.gerbera.web.rest.errors.BadRequestAlertException;
import com.bukodi.jh5.gerbera.web.rest.util.HeaderUtil;
import com.bukodi.jh5.gerbera.web.rest.util.PaginationUtil;
import com.bukodi.jh5.gerbera.service.dto.CertificateDTO;
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
 * REST controller for managing Certificate.
 */
@RestController
@RequestMapping("/api")
public class CertificateResource {

    private final Logger log = LoggerFactory.getLogger(CertificateResource.class);

    private static final String ENTITY_NAME = "certificate";

    private final CertificateService certificateService;

    public CertificateResource(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    /**
     * POST  /certificates : Create a new certificate.
     *
     * @param certificateDTO the certificateDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new certificateDTO, or with status 400 (Bad Request) if the certificate has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/certificates")
    @Timed
    public ResponseEntity<CertificateDTO> createCertificate(@Valid @RequestBody CertificateDTO certificateDTO) throws URISyntaxException {
        log.debug("REST request to save Certificate : {}", certificateDTO);
        if (certificateDTO.getId() != null) {
            throw new BadRequestAlertException("A new certificate cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CertificateDTO result = certificateService.save(certificateDTO);
        return ResponseEntity.created(new URI("/api/certificates/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /certificates : Updates an existing certificate.
     *
     * @param certificateDTO the certificateDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated certificateDTO,
     * or with status 400 (Bad Request) if the certificateDTO is not valid,
     * or with status 500 (Internal Server Error) if the certificateDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/certificates")
    @Timed
    public ResponseEntity<CertificateDTO> updateCertificate(@Valid @RequestBody CertificateDTO certificateDTO) throws URISyntaxException {
        log.debug("REST request to update Certificate : {}", certificateDTO);
        if (certificateDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CertificateDTO result = certificateService.save(certificateDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, certificateDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /certificates : get all the certificates.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of certificates in body
     */
    @GetMapping("/certificates")
    @Timed
    public ResponseEntity<List<CertificateDTO>> getAllCertificates(Pageable pageable) {
        log.debug("REST request to get a page of Certificates");
        Page<CertificateDTO> page = certificateService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/certificates");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /certificates/:id : get the "id" certificate.
     *
     * @param id the id of the certificateDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the certificateDTO, or with status 404 (Not Found)
     */
    @GetMapping("/certificates/{id}")
    @Timed
    public ResponseEntity<CertificateDTO> getCertificate(@PathVariable Long id) {
        log.debug("REST request to get Certificate : {}", id);
        Optional<CertificateDTO> certificateDTO = certificateService.findOne(id);
        return ResponseUtil.wrapOrNotFound(certificateDTO);
    }

    /**
     * DELETE  /certificates/:id : delete the "id" certificate.
     *
     * @param id the id of the certificateDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/certificates/{id}")
    @Timed
    public ResponseEntity<Void> deleteCertificate(@PathVariable Long id) {
        log.debug("REST request to delete Certificate : {}", id);
        certificateService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
