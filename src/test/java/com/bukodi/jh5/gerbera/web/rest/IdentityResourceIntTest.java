package com.bukodi.jh5.gerbera.web.rest;

import com.bukodi.jh5.gerbera.JHipster5TestApp;

import com.bukodi.jh5.gerbera.domain.Identity;
import com.bukodi.jh5.gerbera.repository.IdentityRepository;
import com.bukodi.jh5.gerbera.service.IdentityService;
import com.bukodi.jh5.gerbera.service.dto.IdentityDTO;
import com.bukodi.jh5.gerbera.service.mapper.IdentityMapper;
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
 * Test class for the IdentityResource REST controller.
 *
 * @see IdentityResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JHipster5TestApp.class)
public class IdentityResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EXTERNAL_NAME = "AAAAAAAAAA";
    private static final String UPDATED_EXTERNAL_NAME = "BBBBBBBBBB";

    @Autowired
    private IdentityRepository identityRepository;


    @Autowired
    private IdentityMapper identityMapper;
    

    @Autowired
    private IdentityService identityService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restIdentityMockMvc;

    private Identity identity;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final IdentityResource identityResource = new IdentityResource(identityService);
        this.restIdentityMockMvc = MockMvcBuilders.standaloneSetup(identityResource)
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
    public static Identity createEntity(EntityManager em) {
        Identity identity = new Identity()
            .name(DEFAULT_NAME)
            .externalName(DEFAULT_EXTERNAL_NAME);
        return identity;
    }

    @Before
    public void initTest() {
        identity = createEntity(em);
    }

    @Test
    @Transactional
    public void createIdentity() throws Exception {
        int databaseSizeBeforeCreate = identityRepository.findAll().size();

        // Create the Identity
        IdentityDTO identityDTO = identityMapper.toDto(identity);
        restIdentityMockMvc.perform(post("/api/identities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(identityDTO)))
            .andExpect(status().isCreated());

        // Validate the Identity in the database
        List<Identity> identityList = identityRepository.findAll();
        assertThat(identityList).hasSize(databaseSizeBeforeCreate + 1);
        Identity testIdentity = identityList.get(identityList.size() - 1);
        assertThat(testIdentity.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testIdentity.getExternalName()).isEqualTo(DEFAULT_EXTERNAL_NAME);
    }

    @Test
    @Transactional
    public void createIdentityWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = identityRepository.findAll().size();

        // Create the Identity with an existing ID
        identity.setId(1L);
        IdentityDTO identityDTO = identityMapper.toDto(identity);

        // An entity with an existing ID cannot be created, so this API call must fail
        restIdentityMockMvc.perform(post("/api/identities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(identityDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Identity in the database
        List<Identity> identityList = identityRepository.findAll();
        assertThat(identityList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = identityRepository.findAll().size();
        // set the field null
        identity.setName(null);

        // Create the Identity, which fails.
        IdentityDTO identityDTO = identityMapper.toDto(identity);

        restIdentityMockMvc.perform(post("/api/identities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(identityDTO)))
            .andExpect(status().isBadRequest());

        List<Identity> identityList = identityRepository.findAll();
        assertThat(identityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkExternalNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = identityRepository.findAll().size();
        // set the field null
        identity.setExternalName(null);

        // Create the Identity, which fails.
        IdentityDTO identityDTO = identityMapper.toDto(identity);

        restIdentityMockMvc.perform(post("/api/identities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(identityDTO)))
            .andExpect(status().isBadRequest());

        List<Identity> identityList = identityRepository.findAll();
        assertThat(identityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllIdentities() throws Exception {
        // Initialize the database
        identityRepository.saveAndFlush(identity);

        // Get all the identityList
        restIdentityMockMvc.perform(get("/api/identities?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(identity.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].externalName").value(hasItem(DEFAULT_EXTERNAL_NAME.toString())));
    }
    

    @Test
    @Transactional
    public void getIdentity() throws Exception {
        // Initialize the database
        identityRepository.saveAndFlush(identity);

        // Get the identity
        restIdentityMockMvc.perform(get("/api/identities/{id}", identity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(identity.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.externalName").value(DEFAULT_EXTERNAL_NAME.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingIdentity() throws Exception {
        // Get the identity
        restIdentityMockMvc.perform(get("/api/identities/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateIdentity() throws Exception {
        // Initialize the database
        identityRepository.saveAndFlush(identity);

        int databaseSizeBeforeUpdate = identityRepository.findAll().size();

        // Update the identity
        Identity updatedIdentity = identityRepository.findById(identity.getId()).get();
        // Disconnect from session so that the updates on updatedIdentity are not directly saved in db
        em.detach(updatedIdentity);
        updatedIdentity
            .name(UPDATED_NAME)
            .externalName(UPDATED_EXTERNAL_NAME);
        IdentityDTO identityDTO = identityMapper.toDto(updatedIdentity);

        restIdentityMockMvc.perform(put("/api/identities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(identityDTO)))
            .andExpect(status().isOk());

        // Validate the Identity in the database
        List<Identity> identityList = identityRepository.findAll();
        assertThat(identityList).hasSize(databaseSizeBeforeUpdate);
        Identity testIdentity = identityList.get(identityList.size() - 1);
        assertThat(testIdentity.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testIdentity.getExternalName()).isEqualTo(UPDATED_EXTERNAL_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingIdentity() throws Exception {
        int databaseSizeBeforeUpdate = identityRepository.findAll().size();

        // Create the Identity
        IdentityDTO identityDTO = identityMapper.toDto(identity);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restIdentityMockMvc.perform(put("/api/identities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(identityDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Identity in the database
        List<Identity> identityList = identityRepository.findAll();
        assertThat(identityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteIdentity() throws Exception {
        // Initialize the database
        identityRepository.saveAndFlush(identity);

        int databaseSizeBeforeDelete = identityRepository.findAll().size();

        // Get the identity
        restIdentityMockMvc.perform(delete("/api/identities/{id}", identity.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Identity> identityList = identityRepository.findAll();
        assertThat(identityList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Identity.class);
        Identity identity1 = new Identity();
        identity1.setId(1L);
        Identity identity2 = new Identity();
        identity2.setId(identity1.getId());
        assertThat(identity1).isEqualTo(identity2);
        identity2.setId(2L);
        assertThat(identity1).isNotEqualTo(identity2);
        identity1.setId(null);
        assertThat(identity1).isNotEqualTo(identity2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(IdentityDTO.class);
        IdentityDTO identityDTO1 = new IdentityDTO();
        identityDTO1.setId(1L);
        IdentityDTO identityDTO2 = new IdentityDTO();
        assertThat(identityDTO1).isNotEqualTo(identityDTO2);
        identityDTO2.setId(identityDTO1.getId());
        assertThat(identityDTO1).isEqualTo(identityDTO2);
        identityDTO2.setId(2L);
        assertThat(identityDTO1).isNotEqualTo(identityDTO2);
        identityDTO1.setId(null);
        assertThat(identityDTO1).isNotEqualTo(identityDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(identityMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(identityMapper.fromId(null)).isNull();
    }
}
