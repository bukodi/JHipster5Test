package com.bukodi.jh5.gerbera.web.rest;

import com.bukodi.jh5.gerbera.JHipster5TestApp;

import com.bukodi.jh5.gerbera.domain.MessageTemplate;
import com.bukodi.jh5.gerbera.repository.MessageTemplateRepository;
import com.bukodi.jh5.gerbera.service.MessageTemplateService;
import com.bukodi.jh5.gerbera.service.dto.MessageTemplateDTO;
import com.bukodi.jh5.gerbera.service.mapper.MessageTemplateMapper;
import com.bukodi.jh5.gerbera.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;


import static com.bukodi.jh5.gerbera.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the MessageTemplateResource REST controller.
 *
 * @see MessageTemplateResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JHipster5TestApp.class)
public class MessageTemplateResourceIntTest {

    private static final Instant DEFAULT_INSTANT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_INSTANT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_EVENT_TYPE_FQN = "AAAAAAAAAA";
    private static final String UPDATED_EVENT_TYPE_FQN = "BBBBBBBBBB";

    private static final Boolean DEFAULT_TEMPLATE = false;
    private static final Boolean UPDATED_TEMPLATE = true;

    private static final String DEFAULT_SOURCE = "AAAAAAAAAA";
    private static final String UPDATED_SOURCE = "BBBBBBBBBB";

    @Autowired
    private MessageTemplateRepository messageTemplateRepository;


    @Autowired
    private MessageTemplateMapper messageTemplateMapper;
    

    @Autowired
    private MessageTemplateService messageTemplateService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restMessageTemplateMockMvc;

    private MessageTemplate messageTemplate;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MessageTemplateResource messageTemplateResource = new MessageTemplateResource(messageTemplateService);
        this.restMessageTemplateMockMvc = MockMvcBuilders.standaloneSetup(messageTemplateResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MessageTemplate createEntity(EntityManager em) {
        MessageTemplate messageTemplate = new MessageTemplate()
            .instant(DEFAULT_INSTANT)
            .eventTypeFqn(DEFAULT_EVENT_TYPE_FQN)
            .template(DEFAULT_TEMPLATE)
            .source(DEFAULT_SOURCE);
        return messageTemplate;
    }

    @Before
    public void initTest() {
        messageTemplate = createEntity(em);
    }

    @Test
    @Transactional
    public void createMessageTemplate() throws Exception {
        int databaseSizeBeforeCreate = messageTemplateRepository.findAll().size();

        // Create the MessageTemplate
        MessageTemplateDTO messageTemplateDTO = messageTemplateMapper.toDto(messageTemplate);
        restMessageTemplateMockMvc.perform(post("/api/message-templates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(messageTemplateDTO)))
            .andExpect(status().isCreated());

        // Validate the MessageTemplate in the database
        List<MessageTemplate> messageTemplateList = messageTemplateRepository.findAll();
        assertThat(messageTemplateList).hasSize(databaseSizeBeforeCreate + 1);
        MessageTemplate testMessageTemplate = messageTemplateList.get(messageTemplateList.size() - 1);
        assertThat(testMessageTemplate.getInstant()).isEqualTo(DEFAULT_INSTANT);
        assertThat(testMessageTemplate.getEventTypeFqn()).isEqualTo(DEFAULT_EVENT_TYPE_FQN);
        assertThat(testMessageTemplate.isTemplate()).isEqualTo(DEFAULT_TEMPLATE);
        assertThat(testMessageTemplate.getSource()).isEqualTo(DEFAULT_SOURCE);
    }

    @Test
    @Transactional
    public void createMessageTemplateWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = messageTemplateRepository.findAll().size();

        // Create the MessageTemplate with an existing ID
        messageTemplate.setId(1L);
        MessageTemplateDTO messageTemplateDTO = messageTemplateMapper.toDto(messageTemplate);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMessageTemplateMockMvc.perform(post("/api/message-templates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(messageTemplateDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MessageTemplate in the database
        List<MessageTemplate> messageTemplateList = messageTemplateRepository.findAll();
        assertThat(messageTemplateList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkInstantIsRequired() throws Exception {
        int databaseSizeBeforeTest = messageTemplateRepository.findAll().size();
        // set the field null
        messageTemplate.setInstant(null);

        // Create the MessageTemplate, which fails.
        MessageTemplateDTO messageTemplateDTO = messageTemplateMapper.toDto(messageTemplate);

        restMessageTemplateMockMvc.perform(post("/api/message-templates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(messageTemplateDTO)))
            .andExpect(status().isBadRequest());

        List<MessageTemplate> messageTemplateList = messageTemplateRepository.findAll();
        assertThat(messageTemplateList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEventTypeFqnIsRequired() throws Exception {
        int databaseSizeBeforeTest = messageTemplateRepository.findAll().size();
        // set the field null
        messageTemplate.setEventTypeFqn(null);

        // Create the MessageTemplate, which fails.
        MessageTemplateDTO messageTemplateDTO = messageTemplateMapper.toDto(messageTemplate);

        restMessageTemplateMockMvc.perform(post("/api/message-templates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(messageTemplateDTO)))
            .andExpect(status().isBadRequest());

        List<MessageTemplate> messageTemplateList = messageTemplateRepository.findAll();
        assertThat(messageTemplateList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMessageTemplates() throws Exception {
        // Initialize the database
        messageTemplateRepository.saveAndFlush(messageTemplate);

        // Get all the messageTemplateList
        restMessageTemplateMockMvc.perform(get("/api/message-templates?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(messageTemplate.getId().intValue())))
            .andExpect(jsonPath("$.[*].instant").value(hasItem(DEFAULT_INSTANT.toString())))
            .andExpect(jsonPath("$.[*].eventTypeFqn").value(hasItem(DEFAULT_EVENT_TYPE_FQN.toString())))
            .andExpect(jsonPath("$.[*].template").value(hasItem(DEFAULT_TEMPLATE.booleanValue())))
            .andExpect(jsonPath("$.[*].source").value(hasItem(DEFAULT_SOURCE.toString())));
    }
    

    @Test
    @Transactional
    public void getMessageTemplate() throws Exception {
        // Initialize the database
        messageTemplateRepository.saveAndFlush(messageTemplate);

        // Get the messageTemplate
        restMessageTemplateMockMvc.perform(get("/api/message-templates/{id}", messageTemplate.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(messageTemplate.getId().intValue()))
            .andExpect(jsonPath("$.instant").value(DEFAULT_INSTANT.toString()))
            .andExpect(jsonPath("$.eventTypeFqn").value(DEFAULT_EVENT_TYPE_FQN.toString()))
            .andExpect(jsonPath("$.template").value(DEFAULT_TEMPLATE.booleanValue()))
            .andExpect(jsonPath("$.source").value(DEFAULT_SOURCE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingMessageTemplate() throws Exception {
        // Get the messageTemplate
        restMessageTemplateMockMvc.perform(get("/api/message-templates/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMessageTemplate() throws Exception {
        // Initialize the database
        messageTemplateRepository.saveAndFlush(messageTemplate);

        int databaseSizeBeforeUpdate = messageTemplateRepository.findAll().size();

        // Update the messageTemplate
        MessageTemplate updatedMessageTemplate = messageTemplateRepository.findById(messageTemplate.getId()).get();
        // Disconnect from session so that the updates on updatedMessageTemplate are not directly saved in db
        em.detach(updatedMessageTemplate);
        updatedMessageTemplate
            .instant(UPDATED_INSTANT)
            .eventTypeFqn(UPDATED_EVENT_TYPE_FQN)
            .template(UPDATED_TEMPLATE)
            .source(UPDATED_SOURCE);
        MessageTemplateDTO messageTemplateDTO = messageTemplateMapper.toDto(updatedMessageTemplate);

        restMessageTemplateMockMvc.perform(put("/api/message-templates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(messageTemplateDTO)))
            .andExpect(status().isOk());

        // Validate the MessageTemplate in the database
        List<MessageTemplate> messageTemplateList = messageTemplateRepository.findAll();
        assertThat(messageTemplateList).hasSize(databaseSizeBeforeUpdate);
        MessageTemplate testMessageTemplate = messageTemplateList.get(messageTemplateList.size() - 1);
        assertThat(testMessageTemplate.getInstant()).isEqualTo(UPDATED_INSTANT);
        assertThat(testMessageTemplate.getEventTypeFqn()).isEqualTo(UPDATED_EVENT_TYPE_FQN);
        assertThat(testMessageTemplate.isTemplate()).isEqualTo(UPDATED_TEMPLATE);
        assertThat(testMessageTemplate.getSource()).isEqualTo(UPDATED_SOURCE);
    }

    @Test
    @Transactional
    public void updateNonExistingMessageTemplate() throws Exception {
        int databaseSizeBeforeUpdate = messageTemplateRepository.findAll().size();

        // Create the MessageTemplate
        MessageTemplateDTO messageTemplateDTO = messageTemplateMapper.toDto(messageTemplate);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restMessageTemplateMockMvc.perform(put("/api/message-templates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(messageTemplateDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MessageTemplate in the database
        List<MessageTemplate> messageTemplateList = messageTemplateRepository.findAll();
        assertThat(messageTemplateList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMessageTemplate() throws Exception {
        // Initialize the database
        messageTemplateRepository.saveAndFlush(messageTemplate);

        int databaseSizeBeforeDelete = messageTemplateRepository.findAll().size();

        // Get the messageTemplate
        restMessageTemplateMockMvc.perform(delete("/api/message-templates/{id}", messageTemplate.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<MessageTemplate> messageTemplateList = messageTemplateRepository.findAll();
        assertThat(messageTemplateList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MessageTemplate.class);
        MessageTemplate messageTemplate1 = new MessageTemplate();
        messageTemplate1.setId(1L);
        MessageTemplate messageTemplate2 = new MessageTemplate();
        messageTemplate2.setId(messageTemplate1.getId());
        assertThat(messageTemplate1).isEqualTo(messageTemplate2);
        messageTemplate2.setId(2L);
        assertThat(messageTemplate1).isNotEqualTo(messageTemplate2);
        messageTemplate1.setId(null);
        assertThat(messageTemplate1).isNotEqualTo(messageTemplate2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MessageTemplateDTO.class);
        MessageTemplateDTO messageTemplateDTO1 = new MessageTemplateDTO();
        messageTemplateDTO1.setId(1L);
        MessageTemplateDTO messageTemplateDTO2 = new MessageTemplateDTO();
        assertThat(messageTemplateDTO1).isNotEqualTo(messageTemplateDTO2);
        messageTemplateDTO2.setId(messageTemplateDTO1.getId());
        assertThat(messageTemplateDTO1).isEqualTo(messageTemplateDTO2);
        messageTemplateDTO2.setId(2L);
        assertThat(messageTemplateDTO1).isNotEqualTo(messageTemplateDTO2);
        messageTemplateDTO1.setId(null);
        assertThat(messageTemplateDTO1).isNotEqualTo(messageTemplateDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(messageTemplateMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(messageTemplateMapper.fromId(null)).isNull();
    }
}
