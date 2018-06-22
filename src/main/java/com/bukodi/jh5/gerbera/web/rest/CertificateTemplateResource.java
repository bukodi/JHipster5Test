package com.bukodi.jh5.gerbera.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.bukodi.jh5.gerbera.service.CertificateTemplateService;
import com.bukodi.jh5.gerbera.web.rest.errors.BadRequestAlertException;
import com.bukodi.jh5.gerbera.web.rest.util.HeaderUtil;
import com.bukodi.jh5.gerbera.web.rest.util.PaginationUtil;
import com.bukodi.jh5.gerbera.service.dto.CertificateTemplateDTO;
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
 * REST controller for managing CertificateTemplate.
 */
@RestController
@RequestMapping("/api")
public class CertificateTemplateResource {

    private final Logger log = LoggerFactory.getLogger(CertificateTemplateResource.class);

    private static final String ENTITY_NAME = "certificateTemplate";

    private final CertificateTemplateService certificateTemplateService;

    public CertificateTemplateResource(CertificateTemplateService certificateTemplateService) {
        this.certificateTemplateService = certificateTemplateService;
    }

    /**
     * POST  /certificate-templates : Create a new certificateTemplate.
     *
     * @param certificateTemplateDTO the certificateTemplateDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new certificateTemplateDTO, or with status 400 (Bad Request) if the certificateTemplate has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/certificate-templates")
    @Timed
    public ResponseEntity<CertificateTemplateDTO> createCertificateTemplate(@Valid @RequestBody CertificateTemplateDTO certificateTemplateDTO) throws URISyntaxException {
        log.debug("REST request to save CertificateTemplate : {}", certificateTemplateDTO);
        if (certificateTemplateDTO.getId() != null) {
            throw new BadRequestAlertException("A new certificateTemplate cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CertificateTemplateDTO result = certificateTemplateService.save(certificateTemplateDTO);
        return ResponseEntity.created(new URI("/api/certificate-templates/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /certificate-templates : Updates an existing certificateTemplate.
     *
     * @param certificateTemplateDTO the certificateTemplateDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated certificateTemplateDTO,
     * or with status 400 (Bad Request) if the certificateTemplateDTO is not valid,
     * or with status 500 (Internal Server Error) if the certificateTemplateDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/certificate-templates")
    @Timed
    public ResponseEntity<CertificateTemplateDTO> updateCertificateTemplate(@Valid @RequestBody CertificateTemplateDTO certificateTemplateDTO) throws URISyntaxException {
        log.debug("REST request to update CertificateTemplate : {}", certificateTemplateDTO);
        if (certificateTemplateDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CertificateTemplateDTO result = certificateTemplateService.save(certificateTemplateDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, certificateTemplateDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /certificate-templates : get all the certificateTemplates.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of certificateTemplates in body
     */
    @GetMapping("/certificate-templates")
    @Timed
    public ResponseEntity<List<CertificateTemplateDTO>> getAllCertificateTemplates(Pageable pageable) {
        log.debug("REST request to get a page of CertificateTemplates");
        Page<CertificateTemplateDTO> page = certificateTemplateService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/certificate-templates");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /certificate-templates/:id : get the "id" certificateTemplate.
     *
     * @param id the id of the certificateTemplateDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the certificateTemplateDTO, or with status 404 (Not Found)
     */
    @GetMapping("/certificate-templates/{id}")
    @Timed
    public ResponseEntity<CertificateTemplateDTO> getCertificateTemplate(@PathVariable Long id) {
        log.debug("REST request to get CertificateTemplate : {}", id);
        Optional<CertificateTemplateDTO> certificateTemplateDTO = certificateTemplateService.findOne(id);
        return ResponseUtil.wrapOrNotFound(certificateTemplateDTO);
    }

    /**
     * DELETE  /certificate-templates/:id : delete the "id" certificateTemplate.
     *
     * @param id the id of the certificateTemplateDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/certificate-templates/{id}")
    @Timed
    public ResponseEntity<Void> deleteCertificateTemplate(@PathVariable Long id) {
        log.debug("REST request to delete CertificateTemplate : {}", id);
        certificateTemplateService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
