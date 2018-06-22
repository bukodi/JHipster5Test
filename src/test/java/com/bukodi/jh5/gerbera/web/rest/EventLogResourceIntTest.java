package com.bukodi.jh5.gerbera.web.rest;

import com.bukodi.jh5.gerbera.JHipster5TestApp;

import com.bukodi.jh5.gerbera.domain.EventLog;
import com.bukodi.jh5.gerbera.repository.EventLogRepository;
import com.bukodi.jh5.gerbera.service.EventLogService;
import com.bukodi.jh5.gerbera.service.dto.EventLogDTO;
import com.bukodi.jh5.gerbera.service.mapper.EventLogMapper;
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
 * Test class for the EventLogResource REST controller.
 *
 * @see EventLogResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JHipster5TestApp.class)
public class EventLogResourceIntTest {

    private static final Instant DEFAULT_INSTANT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_INSTANT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_EVENT_TYPE_FQN = "AAAAAAAAAA";
    private static final String UPDATED_EVENT_TYPE_FQN = "BBBBBBBBBB";

    private static final String DEFAULT_NODE = "AAAAAAAAAA";
    private static final String UPDATED_NODE = "BBBBBBBBBB";

    private static final String DEFAULT_SERACH_KEY = "AAAAAAAAAA";
    private static final String UPDATED_SERACH_KEY = "BBBBBBBBBB";

    private static final String DEFAULT_PAYLOAD = "AAAAAAAAAA";
    private static final String UPDATED_PAYLOAD = "BBBBBBBBBB";

    @Autowired
    private EventLogRepository eventLogRepository;


    @Autowired
    private EventLogMapper eventLogMapper;
    

    @Autowired
    private EventLogService eventLogService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restEventLogMockMvc;

    private EventLog eventLog;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EventLogResource eventLogResource = new EventLogResource(eventLogService);
        this.restEventLogMockMvc = MockMvcBuilders.standaloneSetup(eventLogResource)
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
    public static EventLog createEntity(EntityManager em) {
        EventLog eventLog = new EventLog()
            .instant(DEFAULT_INSTANT)
            .eventTypeFqn(DEFAULT_EVENT_TYPE_FQN)
            .node(DEFAULT_NODE)
            .serachKey(DEFAULT_SERACH_KEY)
            .payload(DEFAULT_PAYLOAD);
        return eventLog;
    }

    @Before
    public void initTest() {
        eventLog = createEntity(em);
    }

    @Test
    @Transactional
    public void createEventLog() throws Exception {
        int databaseSizeBeforeCreate = eventLogRepository.findAll().size();

        // Create the EventLog
        EventLogDTO eventLogDTO = eventLogMapper.toDto(eventLog);
        restEventLogMockMvc.perform(post("/api/event-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eventLogDTO)))
            .andExpect(status().isCreated());

        // Validate the EventLog in the database
        List<EventLog> eventLogList = eventLogRepository.findAll();
        assertThat(eventLogList).hasSize(databaseSizeBeforeCreate + 1);
        EventLog testEventLog = eventLogList.get(eventLogList.size() - 1);
        assertThat(testEventLog.getInstant()).isEqualTo(DEFAULT_INSTANT);
        assertThat(testEventLog.getEventTypeFqn()).isEqualTo(DEFAULT_EVENT_TYPE_FQN);
        assertThat(testEventLog.getNode()).isEqualTo(DEFAULT_NODE);
        assertThat(testEventLog.getSerachKey()).isEqualTo(DEFAULT_SERACH_KEY);
        assertThat(testEventLog.getPayload()).isEqualTo(DEFAULT_PAYLOAD);
    }

    @Test
    @Transactional
    public void createEventLogWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = eventLogRepository.findAll().size();

        // Create the EventLog with an existing ID
        eventLog.setId(1L);
        EventLogDTO eventLogDTO = eventLogMapper.toDto(eventLog);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEventLogMockMvc.perform(post("/api/event-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eventLogDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EventLog in the database
        List<EventLog> eventLogList = eventLogRepository.findAll();
        assertThat(eventLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkInstantIsRequired() throws Exception {
        int databaseSizeBeforeTest = eventLogRepository.findAll().size();
        // set the field null
        eventLog.setInstant(null);

        // Create the EventLog, which fails.
        EventLogDTO eventLogDTO = eventLogMapper.toDto(eventLog);

        restEventLogMockMvc.perform(post("/api/event-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eventLogDTO)))
            .andExpect(status().isBadRequest());

        List<EventLog> eventLogList = eventLogRepository.findAll();
        assertThat(eventLogList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEventTypeFqnIsRequired() throws Exception {
        int databaseSizeBeforeTest = eventLogRepository.findAll().size();
        // set the field null
        eventLog.setEventTypeFqn(null);

        // Create the EventLog, which fails.
        EventLogDTO eventLogDTO = eventLogMapper.toDto(eventLog);

        restEventLogMockMvc.perform(post("/api/event-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eventLogDTO)))
            .andExpect(status().isBadRequest());

        List<EventLog> eventLogList = eventLogRepository.findAll();
        assertThat(eventLogList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = eventLogRepository.findAll().size();
        // set the field null
        eventLog.setNode(null);

        // Create the EventLog, which fails.
        EventLogDTO eventLogDTO = eventLogMapper.toDto(eventLog);

        restEventLogMockMvc.perform(post("/api/event-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eventLogDTO)))
            .andExpect(status().isBadRequest());

        List<EventLog> eventLogList = eventLogRepository.findAll();
        assertThat(eventLogList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSerachKeyIsRequired() throws Exception {
        int databaseSizeBeforeTest = eventLogRepository.findAll().size();
        // set the field null
        eventLog.setSerachKey(null);

        // Create the EventLog, which fails.
        EventLogDTO eventLogDTO = eventLogMapper.toDto(eventLog);

        restEventLogMockMvc.perform(post("/api/event-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eventLogDTO)))
            .andExpect(status().isBadRequest());

        List<EventLog> eventLogList = eventLogRepository.findAll();
        assertThat(eventLogList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEventLogs() throws Exception {
        // Initialize the database
        eventLogRepository.saveAndFlush(eventLog);

        // Get all the eventLogList
        restEventLogMockMvc.perform(get("/api/event-logs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(eventLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].instant").value(hasItem(DEFAULT_INSTANT.toString())))
            .andExpect(jsonPath("$.[*].eventTypeFqn").value(hasItem(DEFAULT_EVENT_TYPE_FQN.toString())))
            .andExpect(jsonPath("$.[*].node").value(hasItem(DEFAULT_NODE.toString())))
            .andExpect(jsonPath("$.[*].serachKey").value(hasItem(DEFAULT_SERACH_KEY.toString())))
            .andExpect(jsonPath("$.[*].payload").value(hasItem(DEFAULT_PAYLOAD.toString())));
    }
    

    @Test
    @Transactional
    public void getEventLog() throws Exception {
        // Initialize the database
        eventLogRepository.saveAndFlush(eventLog);

        // Get the eventLog
        restEventLogMockMvc.perform(get("/api/event-logs/{id}", eventLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(eventLog.getId().intValue()))
            .andExpect(jsonPath("$.instant").value(DEFAULT_INSTANT.toString()))
            .andExpect(jsonPath("$.eventTypeFqn").value(DEFAULT_EVENT_TYPE_FQN.toString()))
            .andExpect(jsonPath("$.node").value(DEFAULT_NODE.toString()))
            .andExpect(jsonPath("$.serachKey").value(DEFAULT_SERACH_KEY.toString()))
            .andExpect(jsonPath("$.payload").value(DEFAULT_PAYLOAD.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingEventLog() throws Exception {
        // Get the eventLog
        restEventLogMockMvc.perform(get("/api/event-logs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEventLog() throws Exception {
        // Initialize the database
        eventLogRepository.saveAndFlush(eventLog);

        int databaseSizeBeforeUpdate = eventLogRepository.findAll().size();

        // Update the eventLog
        EventLog updatedEventLog = eventLogRepository.findById(eventLog.getId()).get();
        // Disconnect from session so that the updates on updatedEventLog are not directly saved in db
        em.detach(updatedEventLog);
        updatedEventLog
            .instant(UPDATED_INSTANT)
            .eventTypeFqn(UPDATED_EVENT_TYPE_FQN)
            .node(UPDATED_NODE)
            .serachKey(UPDATED_SERACH_KEY)
            .payload(UPDATED_PAYLOAD);
        EventLogDTO eventLogDTO = eventLogMapper.toDto(updatedEventLog);

        restEventLogMockMvc.perform(put("/api/event-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eventLogDTO)))
            .andExpect(status().isOk());

        // Validate the EventLog in the database
        List<EventLog> eventLogList = eventLogRepository.findAll();
        assertThat(eventLogList).hasSize(databaseSizeBeforeUpdate);
        EventLog testEventLog = eventLogList.get(eventLogList.size() - 1);
        assertThat(testEventLog.getInstant()).isEqualTo(UPDATED_INSTANT);
        assertThat(testEventLog.getEventTypeFqn()).isEqualTo(UPDATED_EVENT_TYPE_FQN);
        assertThat(testEventLog.getNode()).isEqualTo(UPDATED_NODE);
        assertThat(testEventLog.getSerachKey()).isEqualTo(UPDATED_SERACH_KEY);
        assertThat(testEventLog.getPayload()).isEqualTo(UPDATED_PAYLOAD);
    }

    @Test
    @Transactional
    public void updateNonExistingEventLog() throws Exception {
        int databaseSizeBeforeUpdate = eventLogRepository.findAll().size();

        // Create the EventLog
        EventLogDTO eventLogDTO = eventLogMapper.toDto(eventLog);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restEventLogMockMvc.perform(put("/api/event-logs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(eventLogDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EventLog in the database
        List<EventLog> eventLogList = eventLogRepository.findAll();
        assertThat(eventLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEventLog() throws Exception {
        // Initialize the database
        eventLogRepository.saveAndFlush(eventLog);

        int databaseSizeBeforeDelete = eventLogRepository.findAll().size();

        // Get the eventLog
        restEventLogMockMvc.perform(delete("/api/event-logs/{id}", eventLog.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<EventLog> eventLogList = eventLogRepository.findAll();
        assertThat(eventLogList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EventLog.class);
        EventLog eventLog1 = new EventLog();
        eventLog1.setId(1L);
        EventLog eventLog2 = new EventLog();
        eventLog2.setId(eventLog1.getId());
        assertThat(eventLog1).isEqualTo(eventLog2);
        eventLog2.setId(2L);
        assertThat(eventLog1).isNotEqualTo(eventLog2);
        eventLog1.setId(null);
        assertThat(eventLog1).isNotEqualTo(eventLog2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EventLogDTO.class);
        EventLogDTO eventLogDTO1 = new EventLogDTO();
        eventLogDTO1.setId(1L);
        EventLogDTO eventLogDTO2 = new EventLogDTO();
        assertThat(eventLogDTO1).isNotEqualTo(eventLogDTO2);
        eventLogDTO2.setId(eventLogDTO1.getId());
        assertThat(eventLogDTO1).isEqualTo(eventLogDTO2);
        eventLogDTO2.setId(2L);
        assertThat(eventLogDTO1).isNotEqualTo(eventLogDTO2);
        eventLogDTO1.setId(null);
        assertThat(eventLogDTO1).isNotEqualTo(eventLogDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(eventLogMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(eventLogMapper.fromId(null)).isNull();
    }
}
