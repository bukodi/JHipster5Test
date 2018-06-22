package com.bukodi.jh5.gerbera.web.rest;

import com.bukodi.jh5.gerbera.JHipster5TestApp;

import com.bukodi.jh5.gerbera.domain.PhysicalCard;
import com.bukodi.jh5.gerbera.domain.CardType;
import com.bukodi.jh5.gerbera.repository.PhysicalCardRepository;
import com.bukodi.jh5.gerbera.service.PhysicalCardService;
import com.bukodi.jh5.gerbera.service.dto.PhysicalCardDTO;
import com.bukodi.jh5.gerbera.service.mapper.PhysicalCardMapper;
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
import java.util.List;


import static com.bukodi.jh5.gerbera.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the PhysicalCardResource REST controller.
 *
 * @see PhysicalCardResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JHipster5TestApp.class)
public class PhysicalCardResourceIntTest {

    private static final String DEFAULT_VISUAL_ID = "AAAAAAAAAA";
    private static final String UPDATED_VISUAL_ID = "BBBBBBBBBB";

    @Autowired
    private PhysicalCardRepository physicalCardRepository;


    @Autowired
    private PhysicalCardMapper physicalCardMapper;
    

    @Autowired
    private PhysicalCardService physicalCardService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPhysicalCardMockMvc;

    private PhysicalCard physicalCard;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PhysicalCardResource physicalCardResource = new PhysicalCardResource(physicalCardService);
        this.restPhysicalCardMockMvc = MockMvcBuilders.standaloneSetup(physicalCardResource)
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
    public static PhysicalCard createEntity(EntityManager em) {
        PhysicalCard physicalCard = new PhysicalCard()
            .visualId(DEFAULT_VISUAL_ID);
        // Add required entity
        CardType cardType = CardTypeResourceIntTest.createEntity(em);
        em.persist(cardType);
        em.flush();
        physicalCard.setType(cardType);
        return physicalCard;
    }

    @Before
    public void initTest() {
        physicalCard = createEntity(em);
    }

    @Test
    @Transactional
    public void createPhysicalCard() throws Exception {
        int databaseSizeBeforeCreate = physicalCardRepository.findAll().size();

        // Create the PhysicalCard
        PhysicalCardDTO physicalCardDTO = physicalCardMapper.toDto(physicalCard);
        restPhysicalCardMockMvc.perform(post("/api/physical-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(physicalCardDTO)))
            .andExpect(status().isCreated());

        // Validate the PhysicalCard in the database
        List<PhysicalCard> physicalCardList = physicalCardRepository.findAll();
        assertThat(physicalCardList).hasSize(databaseSizeBeforeCreate + 1);
        PhysicalCard testPhysicalCard = physicalCardList.get(physicalCardList.size() - 1);
        assertThat(testPhysicalCard.getVisualId()).isEqualTo(DEFAULT_VISUAL_ID);
    }

    @Test
    @Transactional
    public void createPhysicalCardWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = physicalCardRepository.findAll().size();

        // Create the PhysicalCard with an existing ID
        physicalCard.setId(1L);
        PhysicalCardDTO physicalCardDTO = physicalCardMapper.toDto(physicalCard);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPhysicalCardMockMvc.perform(post("/api/physical-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(physicalCardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PhysicalCard in the database
        List<PhysicalCard> physicalCardList = physicalCardRepository.findAll();
        assertThat(physicalCardList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkVisualIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = physicalCardRepository.findAll().size();
        // set the field null
        physicalCard.setVisualId(null);

        // Create the PhysicalCard, which fails.
        PhysicalCardDTO physicalCardDTO = physicalCardMapper.toDto(physicalCard);

        restPhysicalCardMockMvc.perform(post("/api/physical-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(physicalCardDTO)))
            .andExpect(status().isBadRequest());

        List<PhysicalCard> physicalCardList = physicalCardRepository.findAll();
        assertThat(physicalCardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPhysicalCards() throws Exception {
        // Initialize the database
        physicalCardRepository.saveAndFlush(physicalCard);

        // Get all the physicalCardList
        restPhysicalCardMockMvc.perform(get("/api/physical-cards?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(physicalCard.getId().intValue())))
            .andExpect(jsonPath("$.[*].visualId").value(hasItem(DEFAULT_VISUAL_ID.toString())));
    }
    

    @Test
    @Transactional
    public void getPhysicalCard() throws Exception {
        // Initialize the database
        physicalCardRepository.saveAndFlush(physicalCard);

        // Get the physicalCard
        restPhysicalCardMockMvc.perform(get("/api/physical-cards/{id}", physicalCard.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(physicalCard.getId().intValue()))
            .andExpect(jsonPath("$.visualId").value(DEFAULT_VISUAL_ID.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingPhysicalCard() throws Exception {
        // Get the physicalCard
        restPhysicalCardMockMvc.perform(get("/api/physical-cards/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePhysicalCard() throws Exception {
        // Initialize the database
        physicalCardRepository.saveAndFlush(physicalCard);

        int databaseSizeBeforeUpdate = physicalCardRepository.findAll().size();

        // Update the physicalCard
        PhysicalCard updatedPhysicalCard = physicalCardRepository.findById(physicalCard.getId()).get();
        // Disconnect from session so that the updates on updatedPhysicalCard are not directly saved in db
        em.detach(updatedPhysicalCard);
        updatedPhysicalCard
            .visualId(UPDATED_VISUAL_ID);
        PhysicalCardDTO physicalCardDTO = physicalCardMapper.toDto(updatedPhysicalCard);

        restPhysicalCardMockMvc.perform(put("/api/physical-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(physicalCardDTO)))
            .andExpect(status().isOk());

        // Validate the PhysicalCard in the database
        List<PhysicalCard> physicalCardList = physicalCardRepository.findAll();
        assertThat(physicalCardList).hasSize(databaseSizeBeforeUpdate);
        PhysicalCard testPhysicalCard = physicalCardList.get(physicalCardList.size() - 1);
        assertThat(testPhysicalCard.getVisualId()).isEqualTo(UPDATED_VISUAL_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingPhysicalCard() throws Exception {
        int databaseSizeBeforeUpdate = physicalCardRepository.findAll().size();

        // Create the PhysicalCard
        PhysicalCardDTO physicalCardDTO = physicalCardMapper.toDto(physicalCard);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restPhysicalCardMockMvc.perform(put("/api/physical-cards")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(physicalCardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PhysicalCard in the database
        List<PhysicalCard> physicalCardList = physicalCardRepository.findAll();
        assertThat(physicalCardList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePhysicalCard() throws Exception {
        // Initialize the database
        physicalCardRepository.saveAndFlush(physicalCard);

        int databaseSizeBeforeDelete = physicalCardRepository.findAll().size();

        // Get the physicalCard
        restPhysicalCardMockMvc.perform(delete("/api/physical-cards/{id}", physicalCard.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PhysicalCard> physicalCardList = physicalCardRepository.findAll();
        assertThat(physicalCardList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PhysicalCard.class);
        PhysicalCard physicalCard1 = new PhysicalCard();
        physicalCard1.setId(1L);
        PhysicalCard physicalCard2 = new PhysicalCard();
        physicalCard2.setId(physicalCard1.getId());
        assertThat(physicalCard1).isEqualTo(physicalCard2);
        physicalCard2.setId(2L);
        assertThat(physicalCard1).isNotEqualTo(physicalCard2);
        physicalCard1.setId(null);
        assertThat(physicalCard1).isNotEqualTo(physicalCard2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PhysicalCardDTO.class);
        PhysicalCardDTO physicalCardDTO1 = new PhysicalCardDTO();
        physicalCardDTO1.setId(1L);
        PhysicalCardDTO physicalCardDTO2 = new PhysicalCardDTO();
        assertThat(physicalCardDTO1).isNotEqualTo(physicalCardDTO2);
        physicalCardDTO2.setId(physicalCardDTO1.getId());
        assertThat(physicalCardDTO1).isEqualTo(physicalCardDTO2);
        physicalCardDTO2.setId(2L);
        assertThat(physicalCardDTO1).isNotEqualTo(physicalCardDTO2);
        physicalCardDTO1.setId(null);
        assertThat(physicalCardDTO1).isNotEqualTo(physicalCardDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(physicalCardMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(physicalCardMapper.fromId(null)).isNull();
    }
}
