package com.bukodi.jh5.gerbera.web.rest;

import com.bukodi.jh5.gerbera.JHipster5TestApp;

import com.bukodi.jh5.gerbera.domain.LogicalCard;
import com.bukodi.jh5.gerbera.domain.PhysicalCard;
import com.bukodi.jh5.gerbera.repository.LogicalCardRepository;
import com.bukodi.jh5.gerbera.service.LogicalCardService;
import com.bukodi.jh5.gerbera.service.dto.LogicalCardDTO;
import com.bukodi.jh5.gerbera.service.mapper.LogicalCardMapper;
import com.bukodi.jh5.gerbera.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;


import static com.bukodi.jh5.gerbera.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the LogicalCardResource REST controller.
 *
 * @see LogicalCardResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JHipster5TestApp.class)
public class LogicalCardResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private LogicalCardRepository logicalCardRepository;
    @Mock
    private LogicalCardRepository logicalCardRepositoryMock;

    @Autowired
    private LogicalCardMapper logicalCardMapper;
    
    @Mock
    private LogicalCardService logicalCardServiceMock;

    @Autowired
    private LogicalCardService logicalCardService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restLogicalCardMockMvc;

    private LogicalCard logicalCard;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final LogicalCardResource logicalCardResource = new LogicalCardResource(logicalCardService);
        this.restLogicalCardMockMvc = MockMvcBuilders.standaloneSetup(logicalCardResource)
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
    public static LogicalCard createEntity(EntityManager em) {
        LogicalCard logicalCard = new LogicalCard()
            .name(DEFAULT_NAME);
        // Add required entity
        PhysicalCard physicalCard = PhysicalCardResourceIntTest.createEntity(em);
        em.persist(physicalCard);
        em.flush();
        logicalCard.setPhysicalCard(physicalCard);
        return logicalCard;
    }

    @Before
    public void initTest() {
        logicalCard = createEntity(em);
    }

    @Test
    @Transactional
    public void createLogicalCard() throws Exception {
        int databaseSizeBeforeCreate = logicalCardRepository.findAll().size();

        // Create the LogicalCard
        LogicalCardDTO logicalCardDTO = logicalCardMapper.toDto(logicalCard);
        restLogicalCardMockMvc.perform(post("/api/logical-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(logicalCardDTO)))
            .andExpect(status().isCreated());

        // Validate the LogicalCard in the database
        List<LogicalCard> logicalCardList = logicalCardRepository.findAll();
        assertThat(logicalCardList).hasSize(databaseSizeBeforeCreate + 1);
        LogicalCard testLogicalCard = logicalCardList.get(logicalCardList.size() - 1);
        assertThat(testLogicalCard.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createLogicalCardWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = logicalCardRepository.findAll().size();

        // Create the LogicalCard with an existing ID
        logicalCard.setId(1L);
        LogicalCardDTO logicalCardDTO = logicalCardMapper.toDto(logicalCard);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLogicalCardMockMvc.perform(post("/api/logical-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(logicalCardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LogicalCard in the database
        List<LogicalCard> logicalCardList = logicalCardRepository.findAll();
        assertThat(logicalCardList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = logicalCardRepository.findAll().size();
        // set the field null
        logicalCard.setName(null);

        // Create the LogicalCard, which fails.
        LogicalCardDTO logicalCardDTO = logicalCardMapper.toDto(logicalCard);

        restLogicalCardMockMvc.perform(post("/api/logical-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(logicalCardDTO)))
            .andExpect(status().isBadRequest());

        List<LogicalCard> logicalCardList = logicalCardRepository.findAll();
        assertThat(logicalCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllLogicalCards() throws Exception {
        // Initialize the database
        logicalCardRepository.saveAndFlush(logicalCard);

        // Get all the logicalCardList
        restLogicalCardMockMvc.perform(get("/api/logical-cards?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(logicalCard.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())));
    }
    
    public void getAllLogicalCardsWithEagerRelationshipsIsEnabled() throws Exception {
        LogicalCardResource logicalCardResource = new LogicalCardResource(logicalCardServiceMock);
        when(logicalCardServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        MockMvc restLogicalCardMockMvc = MockMvcBuilders.standaloneSetup(logicalCardResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restLogicalCardMockMvc.perform(get("/api/logical-cards?eagerload=true"))
        .andExpect(status().isOk());

        verify(logicalCardServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    public void getAllLogicalCardsWithEagerRelationshipsIsNotEnabled() throws Exception {
        LogicalCardResource logicalCardResource = new LogicalCardResource(logicalCardServiceMock);
            when(logicalCardServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
            MockMvc restLogicalCardMockMvc = MockMvcBuilders.standaloneSetup(logicalCardResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restLogicalCardMockMvc.perform(get("/api/logical-cards?eagerload=true"))
        .andExpect(status().isOk());

            verify(logicalCardServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getLogicalCard() throws Exception {
        // Initialize the database
        logicalCardRepository.saveAndFlush(logicalCard);

        // Get the logicalCard
        restLogicalCardMockMvc.perform(get("/api/logical-cards/{id}", logicalCard.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(logicalCard.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingLogicalCard() throws Exception {
        // Get the logicalCard
        restLogicalCardMockMvc.perform(get("/api/logical-cards/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLogicalCard() throws Exception {
        // Initialize the database
        logicalCardRepository.saveAndFlush(logicalCard);

        int databaseSizeBeforeUpdate = logicalCardRepository.findAll().size();

        // Update the logicalCard
        LogicalCard updatedLogicalCard = logicalCardRepository.findById(logicalCard.getId()).get();
        // Disconnect from session so that the updates on updatedLogicalCard are not directly saved in db
        em.detach(updatedLogicalCard);
        updatedLogicalCard
            .name(UPDATED_NAME);
        LogicalCardDTO logicalCardDTO = logicalCardMapper.toDto(updatedLogicalCard);

        restLogicalCardMockMvc.perform(put("/api/logical-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(logicalCardDTO)))
            .andExpect(status().isOk());

        // Validate the LogicalCard in the database
        List<LogicalCard> logicalCardList = logicalCardRepository.findAll();
        assertThat(logicalCardList).hasSize(databaseSizeBeforeUpdate);
        LogicalCard testLogicalCard = logicalCardList.get(logicalCardList.size() - 1);
        assertThat(testLogicalCard.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingLogicalCard() throws Exception {
        int databaseSizeBeforeUpdate = logicalCardRepository.findAll().size();

        // Create the LogicalCard
        LogicalCardDTO logicalCardDTO = logicalCardMapper.toDto(logicalCard);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restLogicalCardMockMvc.perform(put("/api/logical-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(logicalCardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LogicalCard in the database
        List<LogicalCard> logicalCardList = logicalCardRepository.findAll();
        assertThat(logicalCardList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLogicalCard() throws Exception {
        // Initialize the database
        logicalCardRepository.saveAndFlush(logicalCard);

        int databaseSizeBeforeDelete = logicalCardRepository.findAll().size();

        // Get the logicalCard
        restLogicalCardMockMvc.perform(delete("/api/logical-cards/{id}", logicalCard.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<LogicalCard> logicalCardList = logicalCardRepository.findAll();
        assertThat(logicalCardList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LogicalCard.class);
        LogicalCard logicalCard1 = new LogicalCard();
        logicalCard1.setId(1L);
        LogicalCard logicalCard2 = new LogicalCard();
        logicalCard2.setId(logicalCard1.getId());
        assertThat(logicalCard1).isEqualTo(logicalCard2);
        logicalCard2.setId(2L);
        assertThat(logicalCard1).isNotEqualTo(logicalCard2);
        logicalCard1.setId(null);
        assertThat(logicalCard1).isNotEqualTo(logicalCard2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LogicalCardDTO.class);
        LogicalCardDTO logicalCardDTO1 = new LogicalCardDTO();
        logicalCardDTO1.setId(1L);
        LogicalCardDTO logicalCardDTO2 = new LogicalCardDTO();
        assertThat(logicalCardDTO1).isNotEqualTo(logicalCardDTO2);
        logicalCardDTO2.setId(logicalCardDTO1.getId());
        assertThat(logicalCardDTO1).isEqualTo(logicalCardDTO2);
        logicalCardDTO2.setId(2L);
        assertThat(logicalCardDTO1).isNotEqualTo(logicalCardDTO2);
        logicalCardDTO1.setId(null);
        assertThat(logicalCardDTO1).isNotEqualTo(logicalCardDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(logicalCardMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(logicalCardMapper.fromId(null)).isNull();
    }
}
