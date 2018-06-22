package com.bukodi.jh5.gerbera.web.rest;

import com.bukodi.jh5.gerbera.JHipster5TestApp;

import com.bukodi.jh5.gerbera.domain.IdentityProfile;
import com.bukodi.jh5.gerbera.repository.IdentityProfileRepository;
import com.bukodi.jh5.gerbera.service.IdentityProfileService;
import com.bukodi.jh5.gerbera.service.dto.IdentityProfileDTO;
import com.bukodi.jh5.gerbera.service.mapper.IdentityProfileMapper;
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
 * Test class for the IdentityProfileResource REST controller.
 *
 * @see IdentityProfileResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JHipster5TestApp.class)
public class IdentityProfileResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private IdentityProfileRepository identityProfileRepository;


    @Autowired
    private IdentityProfileMapper identityProfileMapper;
    

    @Autowired
    private IdentityProfileService identityProfileService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restIdentityProfileMockMvc;

    private IdentityProfile identityProfile;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final IdentityProfileResource identityProfileResource = new IdentityProfileResource(identityProfileService);
        this.restIdentityProfileMockMvc = MockMvcBuilders.standaloneSetup(identityProfileResource)
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
    public static IdentityProfile createEntity(EntityManager em) {
        IdentityProfile identityProfile = new IdentityProfile()
            .name(DEFAULT_NAME);
        return identityProfile;
    }

    @Before
    public void initTest() {
        identityProfile = createEntity(em);
    }

    @Test
    @Transactional
    public void createIdentityProfile() throws Exception {
        int databaseSizeBeforeCreate = identityProfileRepository.findAll().size();

        // Create the IdentityProfile
        IdentityProfileDTO identityProfileDTO = identityProfileMapper.toDto(identityProfile);
        restIdentityProfileMockMvc.perform(post("/api/identity-profiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(identityProfileDTO)))
            .andExpect(status().isCreated());

        // Validate the IdentityProfile in the database
        List<IdentityProfile> identityProfileList = identityProfileRepository.findAll();
        assertThat(identityProfileList).hasSize(databaseSizeBeforeCreate + 1);
        IdentityProfile testIdentityProfile = identityProfileList.get(identityProfileList.size() - 1);
        assertThat(testIdentityProfile.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createIdentityProfileWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = identityProfileRepository.findAll().size();

        // Create the IdentityProfile with an existing ID
        identityProfile.setId(1L);
        IdentityProfileDTO identityProfileDTO = identityProfileMapper.toDto(identityProfile);

        // An entity with an existing ID cannot be created, so this API call must fail
        restIdentityProfileMockMvc.perform(post("/api/identity-profiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(identityProfileDTO)))
            .andExpect(status().isBadRequest());

        // Validate the IdentityProfile in the database
        List<IdentityProfile> identityProfileList = identityProfileRepository.findAll();
        assertThat(identityProfileList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = identityProfileRepository.findAll().size();
        // set the field null
        identityProfile.setName(null);

        // Create the IdentityProfile, which fails.
        IdentityProfileDTO identityProfileDTO = identityProfileMapper.toDto(identityProfile);

        restIdentityProfileMockMvc.perform(post("/api/identity-profiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(identityProfileDTO)))
            .andExpect(status().isBadRequest());

        List<IdentityProfile> identityProfileList = identityProfileRepository.findAll();
        assertThat(identityProfileList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllIdentityProfiles() throws Exception {
        // Initialize the database
        identityProfileRepository.saveAndFlush(identityProfile);

        // Get all the identityProfileList
        restIdentityProfileMockMvc.perform(get("/api/identity-profiles?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(identityProfile.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())));
    }
    

    @Test
    @Transactional
    public void getIdentityProfile() throws Exception {
        // Initialize the database
        identityProfileRepository.saveAndFlush(identityProfile);

        // Get the identityProfile
        restIdentityProfileMockMvc.perform(get("/api/identity-profiles/{id}", identityProfile.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(identityProfile.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingIdentityProfile() throws Exception {
        // Get the identityProfile
        restIdentityProfileMockMvc.perform(get("/api/identity-profiles/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateIdentityProfile() throws Exception {
        // Initialize the database
        identityProfileRepository.saveAndFlush(identityProfile);

        int databaseSizeBeforeUpdate = identityProfileRepository.findAll().size();

        // Update the identityProfile
        IdentityProfile updatedIdentityProfile = identityProfileRepository.findById(identityProfile.getId()).get();
        // Disconnect from session so that the updates on updatedIdentityProfile are not directly saved in db
        em.detach(updatedIdentityProfile);
        updatedIdentityProfile
            .name(UPDATED_NAME);
        IdentityProfileDTO identityProfileDTO = identityProfileMapper.toDto(updatedIdentityProfile);

        restIdentityProfileMockMvc.perform(put("/api/identity-profiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(identityProfileDTO)))
            .andExpect(status().isOk());

        // Validate the IdentityProfile in the database
        List<IdentityProfile> identityProfileList = identityProfileRepository.findAll();
        assertThat(identityProfileList).hasSize(databaseSizeBeforeUpdate);
        IdentityProfile testIdentityProfile = identityProfileList.get(identityProfileList.size() - 1);
        assertThat(testIdentityProfile.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingIdentityProfile() throws Exception {
        int databaseSizeBeforeUpdate = identityProfileRepository.findAll().size();

        // Create the IdentityProfile
        IdentityProfileDTO identityProfileDTO = identityProfileMapper.toDto(identityProfile);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restIdentityProfileMockMvc.perform(put("/api/identity-profiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(identityProfileDTO)))
            .andExpect(status().isBadRequest());

        // Validate the IdentityProfile in the database
        List<IdentityProfile> identityProfileList = identityProfileRepository.findAll();
        assertThat(identityProfileList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteIdentityProfile() throws Exception {
        // Initialize the database
        identityProfileRepository.saveAndFlush(identityProfile);

        int databaseSizeBeforeDelete = identityProfileRepository.findAll().size();

        // Get the identityProfile
        restIdentityProfileMockMvc.perform(delete("/api/identity-profiles/{id}", identityProfile.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<IdentityProfile> identityProfileList = identityProfileRepository.findAll();
        assertThat(identityProfileList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(IdentityProfile.class);
        IdentityProfile identityProfile1 = new IdentityProfile();
        identityProfile1.setId(1L);
        IdentityProfile identityProfile2 = new IdentityProfile();
        identityProfile2.setId(identityProfile1.getId());
        assertThat(identityProfile1).isEqualTo(identityProfile2);
        identityProfile2.setId(2L);
        assertThat(identityProfile1).isNotEqualTo(identityProfile2);
        identityProfile1.setId(null);
        assertThat(identityProfile1).isNotEqualTo(identityProfile2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(IdentityProfileDTO.class);
        IdentityProfileDTO identityProfileDTO1 = new IdentityProfileDTO();
        identityProfileDTO1.setId(1L);
        IdentityProfileDTO identityProfileDTO2 = new IdentityProfileDTO();
        assertThat(identityProfileDTO1).isNotEqualTo(identityProfileDTO2);
        identityProfileDTO2.setId(identityProfileDTO1.getId());
        assertThat(identityProfileDTO1).isEqualTo(identityProfileDTO2);
        identityProfileDTO2.setId(2L);
        assertThat(identityProfileDTO1).isNotEqualTo(identityProfileDTO2);
        identityProfileDTO1.setId(null);
        assertThat(identityProfileDTO1).isNotEqualTo(identityProfileDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(identityProfileMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(identityProfileMapper.fromId(null)).isNull();
    }
}
