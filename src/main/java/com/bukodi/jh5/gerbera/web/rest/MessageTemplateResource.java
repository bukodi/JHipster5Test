package com.bukodi.jh5.gerbera.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.bukodi.jh5.gerbera.service.MessageTemplateService;
import com.bukodi.jh5.gerbera.web.rest.errors.BadRequestAlertException;
import com.bukodi.jh5.gerbera.web.rest.util.HeaderUtil;
import com.bukodi.jh5.gerbera.web.rest.util.PaginationUtil;
import com.bukodi.jh5.gerbera.service.dto.MessageTemplateDTO;
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
 * REST controller for managing MessageTemplate.
 */
@RestController
@RequestMapping("/api")
public class MessageTemplateResource {

    private final Logger log = LoggerFactory.getLogger(MessageTemplateResource.class);

    private static final String ENTITY_NAME = "messageTemplate";

    private final MessageTemplateService messageTemplateService;

    public MessageTemplateResource(MessageTemplateService messageTemplateService) {
        this.messageTemplateService = messageTemplateService;
    }

    /**
     * POST  /message-templates : Create a new messageTemplate.
     *
     * @param messageTemplateDTO the messageTemplateDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new messageTemplateDTO, or with status 400 (Bad Request) if the messageTemplate has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/message-templates")
    @Timed
    public ResponseEntity<MessageTemplateDTO> createMessageTemplate(@Valid @RequestBody MessageTemplateDTO messageTemplateDTO) throws URISyntaxException {
        log.debug("REST request to save MessageTemplate : {}", messageTemplateDTO);
        if (messageTemplateDTO.getId() != null) {
            throw new BadRequestAlertException("A new messageTemplate cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MessageTemplateDTO result = messageTemplateService.save(messageTemplateDTO);
        return ResponseEntity.created(new URI("/api/message-templates/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /message-templates : Updates an existing messageTemplate.
     *
     * @param messageTemplateDTO the messageTemplateDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated messageTemplateDTO,
     * or with status 400 (Bad Request) if the messageTemplateDTO is not valid,
     * or with status 500 (Internal Server Error) if the messageTemplateDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/message-templates")
    @Timed
    public ResponseEntity<MessageTemplateDTO> updateMessageTemplate(@Valid @RequestBody MessageTemplateDTO messageTemplateDTO) throws URISyntaxException {
        log.debug("REST request to update MessageTemplate : {}", messageTemplateDTO);
        if (messageTemplateDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MessageTemplateDTO result = messageTemplateService.save(messageTemplateDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, messageTemplateDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /message-templates : get all the messageTemplates.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of messageTemplates in body
     */
    @GetMapping("/message-templates")
    @Timed
    public ResponseEntity<List<MessageTemplateDTO>> getAllMessageTemplates(Pageable pageable) {
        log.debug("REST request to get a page of MessageTemplates");
        Page<MessageTemplateDTO> page = messageTemplateService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/message-templates");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /message-templates/:id : get the "id" messageTemplate.
     *
     * @param id the id of the messageTemplateDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the messageTemplateDTO, or with status 404 (Not Found)
     */
    @GetMapping("/message-templates/{id}")
    @Timed
    public ResponseEntity<MessageTemplateDTO> getMessageTemplate(@PathVariable Long id) {
        log.debug("REST request to get MessageTemplate : {}", id);
        Optional<MessageTemplateDTO> messageTemplateDTO = messageTemplateService.findOne(id);
        return ResponseUtil.wrapOrNotFound(messageTemplateDTO);
    }

    /**
     * DELETE  /message-templates/:id : delete the "id" messageTemplate.
     *
     * @param id the id of the messageTemplateDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/message-templates/{id}")
    @Timed
    public ResponseEntity<Void> deleteMessageTemplate(@PathVariable Long id) {
        log.debug("REST request to delete MessageTemplate : {}", id);
        messageTemplateService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
