package com.bukodi.jh5.gerbera.web.rest;

import com.bukodi.jh5.gerbera.JHipster5TestApp;

import com.bukodi.jh5.gerbera.domain.CertificateTemplate;
import com.bukodi.jh5.gerbera.domain.CertificateAuthority;
import com.bukodi.jh5.gerbera.repository.CertificateTemplateRepository;
import com.bukodi.jh5.gerbera.service.CertificateTemplateService;
import com.bukodi.jh5.gerbera.service.dto.CertificateTemplateDTO;
import com.bukodi.jh5.gerbera.service.mapper.CertificateTemplateMapper;
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

import com.bukodi.jh5.gerbera.domain.enumeration.CertificateType;
import com.bukodi.jh5.gerbera.domain.enumeration.PKILocation;
import com.bukodi.jh5.gerbera.domain.enumeration.PKILocation;
/**
 * Test class for the CertificateTemplateResource REST controller.
 *
 * @see CertificateTemplateResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JHipster5TestApp.class)
public class CertificateTemplateResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final CertificateType DEFAULT_TYPE = CertificateType.SIGN;
    private static final CertificateType UPDATED_TYPE = CertificateType.AUTH;

    private static final PKILocation DEFAULT_KEY_GENERATION = PKILocation.ENDENTITY;
    private static final PKILocation UPDATED_KEY_GENERATION = PKILocation.CMS;

    private static final PKILocation DEFAULT_KEY_ARCHIVATION = PKILocation.ENDENTITY;
    private static final PKILocation UPDATED_KEY_ARCHIVATION = PKILocation.CMS;

    @Autowired
    private CertificateTemplateRepository certificateTemplateRepository;


    @Autowired
    private CertificateTemplateMapper certificateTemplateMapper;
    

    @Autowired
    private CertificateTemplateService certificateTemplateService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCertificateTemplateMockMvc;

    private CertificateTemplate certificateTemplate;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CertificateTemplateResource certificateTemplateResource = new CertificateTemplateResource(certificateTemplateService);
        this.restCertificateTemplateMockMvc = MockMvcBuilders.standaloneSetup(certificateTemplateResource)
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
    public static CertificateTemplate createEntity(EntityManager em) {
        CertificateTemplate certificateTemplate = new CertificateTemplate()
            .name(DEFAULT_NAME)
            .type(DEFAULT_TYPE)
            .keyGeneration(DEFAULT_KEY_GENERATION)
            .keyArchivation(DEFAULT_KEY_ARCHIVATION);
        // Add required entity
        CertificateAuthority certificateAuthority = CertificateAuthorityResourceIntTest.createEntity(em);
        em.persist(certificateAuthority);
        em.flush();
        certificateTemplate.setCa(certificateAuthority);
        return certificateTemplate;
    }

    @Before
    public void initTest() {
        certificateTemplate = createEntity(em);
    }

    @Test
    @Transactional
    public void createCertificateTemplate() throws Exception {
        int databaseSizeBeforeCreate = certificateTemplateRepository.findAll().size();

        // Create the CertificateTemplate
        CertificateTemplateDTO certificateTemplateDTO = certificateTemplateMapper.toDto(certificateTemplate);
        restCertificateTemplateMockMvc.perform(post("/api/certificate-templates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(certificateTemplateDTO)))
            .andExpect(status().isCreated());

        // Validate the CertificateTemplate in the database
        List<CertificateTemplate> certificateTemplateList = certificateTemplateRepository.findAll();
        assertThat(certificateTemplateList).hasSize(databaseSizeBeforeCreate + 1);
        CertificateTemplate testCertificateTemplate = certificateTemplateList.get(certificateTemplateList.size() - 1);
        assertThat(testCertificateTemplate.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testCertificateTemplate.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testCertificateTemplate.getKeyGeneration()).isEqualTo(DEFAULT_KEY_GENERATION);
        assertThat(testCertificateTemplate.getKeyArchivation()).isEqualTo(DEFAULT_KEY_ARCHIVATION);
    }

    @Test
    @Transactional
    public void createCertificateTemplateWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = certificateTemplateRepository.findAll().size();

        // Create the CertificateTemplate with an existing ID
        certificateTemplate.setId(1L);
        CertificateTemplateDTO certificateTemplateDTO = certificateTemplateMapper.toDto(certificateTemplate);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCertificateTemplateMockMvc.perform(post("/api/certificate-templates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(certificateTemplateDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CertificateTemplate in the database
        List<CertificateTemplate> certificateTemplateList = certificateTemplateRepository.findAll();
        assertThat(certificateTemplateList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = certificateTemplateRepository.findAll().size();
        // set the field null
        certificateTemplate.setName(null);

        // Create the CertificateTemplate, which fails.
        CertificateTemplateDTO certificateTemplateDTO = certificateTemplateMapper.toDto(certificateTemplate);

        restCertificateTemplateMockMvc.perform(post("/api/certificate-templates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(certificateTemplateDTO)))
            .andExpect(status().isBadRequest());

        List<CertificateTemplate> certificateTemplateList = certificateTemplateRepository.findAll();
        assertThat(certificateTemplateList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = certificateTemplateRepository.findAll().size();
        // set the field null
        certificateTemplate.setType(null);

        // Create the CertificateTemplate, which fails.
        CertificateTemplateDTO certificateTemplateDTO = certificateTemplateMapper.toDto(certificateTemplate);

        restCertificateTemplateMockMvc.perform(post("/api/certificate-templates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(certificateTemplateDTO)))
            .andExpect(status().isBadRequest());

        List<CertificateTemplate> certificateTemplateList = certificateTemplateRepository.findAll();
        assertThat(certificateTemplateList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkKeyGenerationIsRequired() throws Exception {
        int databaseSizeBeforeTest = certificateTemplateRepository.findAll().size();
        // set the field null
        certificateTemplate.setKeyGeneration(null);

        // Create the CertificateTemplate, which fails.
        CertificateTemplateDTO certificateTemplateDTO = certificateTemplateMapper.toDto(certificateTemplate);

        restCertificateTemplateMockMvc.perform(post("/api/certificate-templates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(certificateTemplateDTO)))
            .andExpect(status().isBadRequest());

        List<CertificateTemplate> certificateTemplateList = certificateTemplateRepository.findAll();
        assertThat(certificateTemplateList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkKeyArchivationIsRequired() throws Exception {
        int databaseSizeBeforeTest = certificateTemplateRepository.findAll().size();
        // set the field null
        certificateTemplate.setKeyArchivation(null);

        // Create the CertificateTemplate, which fails.
        CertificateTemplateDTO certificateTemplateDTO = certificateTemplateMapper.toDto(certificateTemplate);

        restCertificateTemplateMockMvc.perform(post("/api/certificate-templates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(certificateTemplateDTO)))
            .andExpect(status().isBadRequest());

        List<CertificateTemplate> certificateTemplateList = certificateTemplateRepository.findAll();
        assertThat(certificateTemplateList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCertificateTemplates() throws Exception {
        // Initialize the database
        certificateTemplateRepository.saveAndFlush(certificateTemplate);

        // Get all the certificateTemplateList
        restCertificateTemplateMockMvc.perform(get("/api/certificate-templates?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(certificateTemplate.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].keyGeneration").value(hasItem(DEFAULT_KEY_GENERATION.toString())))
            .andExpect(jsonPath("$.[*].keyArchivation").value(hasItem(DEFAULT_KEY_ARCHIVATION.toString())));
    }
    

    @Test
    @Transactional
    public void getCertificateTemplate() throws Exception {
        // Initialize the database
        certificateTemplateRepository.saveAndFlush(certificateTemplate);

        // Get the certificateTemplate
        restCertificateTemplateMockMvc.perform(get("/api/certificate-templates/{id}", certificateTemplate.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(certificateTemplate.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.keyGeneration").value(DEFAULT_KEY_GENERATION.toString()))
            .andExpect(jsonPath("$.keyArchivation").value(DEFAULT_KEY_ARCHIVATION.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingCertificateTemplate() throws Exception {
        // Get the certificateTemplate
        restCertificateTemplateMockMvc.perform(get("/api/certificate-templates/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCertificateTemplate() throws Exception {
        // Initialize the database
        certificateTemplateRepository.saveAndFlush(certificateTemplate);

        int databaseSizeBeforeUpdate = certificateTemplateRepository.findAll().size();

        // Update the certificateTemplate
        CertificateTemplate updatedCertificateTemplate = certificateTemplateRepository.findById(certificateTemplate.getId()).get();
        // Disconnect from session so that the updates on updatedCertificateTemplate are not directly saved in db
        em.detach(updatedCertificateTemplate);
        updatedCertificateTemplate
            .name(UPDATED_NAME)
            .type(UPDATED_TYPE)
            .keyGeneration(UPDATED_KEY_GENERATION)
            .keyArchivation(UPDATED_KEY_ARCHIVATION);
        CertificateTemplateDTO certificateTemplateDTO = certificateTemplateMapper.toDto(updatedCertificateTemplate);

        restCertificateTemplateMockMvc.perform(put("/api/certificate-templates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(certificateTemplateDTO)))
            .andExpect(status().isOk());

        // Validate the CertificateTemplate in the database
        List<CertificateTemplate> certificateTemplateList = certificateTemplateRepository.findAll();
        assertThat(certificateTemplateList).hasSize(databaseSizeBeforeUpdate);
        CertificateTemplate testCertificateTemplate = certificateTemplateList.get(certificateTemplateList.size() - 1);
        assertThat(testCertificateTemplate.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testCertificateTemplate.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testCertificateTemplate.getKeyGeneration()).isEqualTo(UPDATED_KEY_GENERATION);
        assertThat(testCertificateTemplate.getKeyArchivation()).isEqualTo(UPDATED_KEY_ARCHIVATION);
    }

    @Test
    @Transactional
    public void updateNonExistingCertificateTemplate() throws Exception {
        int databaseSizeBeforeUpdate = certificateTemplateRepository.findAll().size();

        // Create the CertificateTemplate
        CertificateTemplateDTO certificateTemplateDTO = certificateTemplateMapper.toDto(certificateTemplate);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCertificateTemplateMockMvc.perform(put("/api/certificate-templates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(certificateTemplateDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CertificateTemplate in the database
        List<CertificateTemplate> certificateTemplateList = certificateTemplateRepository.findAll();
        assertThat(certificateTemplateList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCertificateTemplate() throws Exception {
        // Initialize the database
        certificateTemplateRepository.saveAndFlush(certificateTemplate);

        int databaseSizeBeforeDelete = certificateTemplateRepository.findAll().size();

        // Get the certificateTemplate
        restCertificateTemplateMockMvc.perform(delete("/api/certificate-templates/{id}", certificateTemplate.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CertificateTemplate> certificateTemplateList = certificateTemplateRepository.findAll();
        assertThat(certificateTemplateList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CertificateTemplate.class);
        CertificateTemplate certificateTemplate1 = new CertificateTemplate();
        certificateTemplate1.setId(1L);
        CertificateTemplate certificateTemplate2 = new CertificateTemplate();
        certificateTemplate2.setId(certificateTemplate1.getId());
        assertThat(certificateTemplate1).isEqualTo(certificateTemplate2);
        certificateTemplate2.setId(2L);
        assertThat(certificateTemplate1).isNotEqualTo(certificateTemplate2);
        certificateTemplate1.setId(null);
        assertThat(certificateTemplate1).isNotEqualTo(certificateTemplate2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CertificateTemplateDTO.class);
        CertificateTemplateDTO certificateTemplateDTO1 = new CertificateTemplateDTO();
        certificateTemplateDTO1.setId(1L);
        CertificateTemplateDTO certificateTemplateDTO2 = new CertificateTemplateDTO();
        assertThat(certificateTemplateDTO1).isNotEqualTo(certificateTemplateDTO2);
        certificateTemplateDTO2.setId(certificateTemplateDTO1.getId());
        assertThat(certificateTemplateDTO1).isEqualTo(certificateTemplateDTO2);
        certificateTemplateDTO2.setId(2L);
        assertThat(certificateTemplateDTO1).isNotEqualTo(certificateTemplateDTO2);
        certificateTemplateDTO1.setId(null);
        assertThat(certificateTemplateDTO1).isNotEqualTo(certificateTemplateDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(certificateTemplateMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(certificateTemplateMapper.fromId(null)).isNull();
    }
}
