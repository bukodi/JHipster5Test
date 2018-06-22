package com.bukodi.jh5.gerbera.web.rest;

import com.bukodi.jh5.gerbera.JHipster5TestApp;

import com.bukodi.jh5.gerbera.domain.CertificateAuthority;
import com.bukodi.jh5.gerbera.repository.CertificateAuthorityRepository;
import com.bukodi.jh5.gerbera.service.CertificateAuthorityService;
import com.bukodi.jh5.gerbera.service.dto.CertificateAuthorityDTO;
import com.bukodi.jh5.gerbera.service.mapper.CertificateAuthorityMapper;
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
import org.springframework.util.Base64Utils;

import javax.persistence.EntityManager;
import java.util.List;


import static com.bukodi.jh5.gerbera.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the CertificateAuthorityResource REST controller.
 *
 * @see CertificateAuthorityResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JHipster5TestApp.class)
public class CertificateAuthorityResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_HOST = "AAAAAAAAAA";
    private static final String UPDATED_HOST = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOMIZATION_SOURCE = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMIZATION_SOURCE = "BBBBBBBBBB";

    private static final byte[] DEFAULT_CUSTOMIZATION_CLASS = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_CUSTOMIZATION_CLASS = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_CUSTOMIZATION_CLASS_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_CUSTOMIZATION_CLASS_CONTENT_TYPE = "image/png";

    @Autowired
    private CertificateAuthorityRepository certificateAuthorityRepository;


    @Autowired
    private CertificateAuthorityMapper certificateAuthorityMapper;
    

    @Autowired
    private CertificateAuthorityService certificateAuthorityService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCertificateAuthorityMockMvc;

    private CertificateAuthority certificateAuthority;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CertificateAuthorityResource certificateAuthorityResource = new CertificateAuthorityResource(certificateAuthorityService);
        this.restCertificateAuthorityMockMvc = MockMvcBuilders.standaloneSetup(certificateAuthorityResource)
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
    public static CertificateAuthority createEntity(EntityManager em) {
        CertificateAuthority certificateAuthority = new CertificateAuthority()
            .name(DEFAULT_NAME)
            .host(DEFAULT_HOST)
            .customizationSource(DEFAULT_CUSTOMIZATION_SOURCE)
            .customizationClass(DEFAULT_CUSTOMIZATION_CLASS)
            .customizationClassContentType(DEFAULT_CUSTOMIZATION_CLASS_CONTENT_TYPE);
        return certificateAuthority;
    }

    @Before
    public void initTest() {
        certificateAuthority = createEntity(em);
    }

    @Test
    @Transactional
    public void createCertificateAuthority() throws Exception {
        int databaseSizeBeforeCreate = certificateAuthorityRepository.findAll().size();

        // Create the CertificateAuthority
        CertificateAuthorityDTO certificateAuthorityDTO = certificateAuthorityMapper.toDto(certificateAuthority);
        restCertificateAuthorityMockMvc.perform(post("/api/certificate-authorities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(certificateAuthorityDTO)))
            .andExpect(status().isCreated());

        // Validate the CertificateAuthority in the database
        List<CertificateAuthority> certificateAuthorityList = certificateAuthorityRepository.findAll();
        assertThat(certificateAuthorityList).hasSize(databaseSizeBeforeCreate + 1);
        CertificateAuthority testCertificateAuthority = certificateAuthorityList.get(certificateAuthorityList.size() - 1);
        assertThat(testCertificateAuthority.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testCertificateAuthority.getHost()).isEqualTo(DEFAULT_HOST);
        assertThat(testCertificateAuthority.getCustomizationSource()).isEqualTo(DEFAULT_CUSTOMIZATION_SOURCE);
        assertThat(testCertificateAuthority.getCustomizationClass()).isEqualTo(DEFAULT_CUSTOMIZATION_CLASS);
        assertThat(testCertificateAuthority.getCustomizationClassContentType()).isEqualTo(DEFAULT_CUSTOMIZATION_CLASS_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void createCertificateAuthorityWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = certificateAuthorityRepository.findAll().size();

        // Create the CertificateAuthority with an existing ID
        certificateAuthority.setId(1L);
        CertificateAuthorityDTO certificateAuthorityDTO = certificateAuthorityMapper.toDto(certificateAuthority);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCertificateAuthorityMockMvc.perform(post("/api/certificate-authorities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(certificateAuthorityDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CertificateAuthority in the database
        List<CertificateAuthority> certificateAuthorityList = certificateAuthorityRepository.findAll();
        assertThat(certificateAuthorityList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = certificateAuthorityRepository.findAll().size();
        // set the field null
        certificateAuthority.setName(null);

        // Create the CertificateAuthority, which fails.
        CertificateAuthorityDTO certificateAuthorityDTO = certificateAuthorityMapper.toDto(certificateAuthority);

        restCertificateAuthorityMockMvc.perform(post("/api/certificate-authorities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(certificateAuthorityDTO)))
            .andExpect(status().isBadRequest());

        List<CertificateAuthority> certificateAuthorityList = certificateAuthorityRepository.findAll();
        assertThat(certificateAuthorityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkHostIsRequired() throws Exception {
        int databaseSizeBeforeTest = certificateAuthorityRepository.findAll().size();
        // set the field null
        certificateAuthority.setHost(null);

        // Create the CertificateAuthority, which fails.
        CertificateAuthorityDTO certificateAuthorityDTO = certificateAuthorityMapper.toDto(certificateAuthority);

        restCertificateAuthorityMockMvc.perform(post("/api/certificate-authorities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(certificateAuthorityDTO)))
            .andExpect(status().isBadRequest());

        List<CertificateAuthority> certificateAuthorityList = certificateAuthorityRepository.findAll();
        assertThat(certificateAuthorityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCertificateAuthorities() throws Exception {
        // Initialize the database
        certificateAuthorityRepository.saveAndFlush(certificateAuthority);

        // Get all the certificateAuthorityList
        restCertificateAuthorityMockMvc.perform(get("/api/certificate-authorities?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(certificateAuthority.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].host").value(hasItem(DEFAULT_HOST.toString())))
            .andExpect(jsonPath("$.[*].customizationSource").value(hasItem(DEFAULT_CUSTOMIZATION_SOURCE.toString())))
            .andExpect(jsonPath("$.[*].customizationClassContentType").value(hasItem(DEFAULT_CUSTOMIZATION_CLASS_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].customizationClass").value(hasItem(Base64Utils.encodeToString(DEFAULT_CUSTOMIZATION_CLASS))));
    }
    

    @Test
    @Transactional
    public void getCertificateAuthority() throws Exception {
        // Initialize the database
        certificateAuthorityRepository.saveAndFlush(certificateAuthority);

        // Get the certificateAuthority
        restCertificateAuthorityMockMvc.perform(get("/api/certificate-authorities/{id}", certificateAuthority.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(certificateAuthority.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.host").value(DEFAULT_HOST.toString()))
            .andExpect(jsonPath("$.customizationSource").value(DEFAULT_CUSTOMIZATION_SOURCE.toString()))
            .andExpect(jsonPath("$.customizationClassContentType").value(DEFAULT_CUSTOMIZATION_CLASS_CONTENT_TYPE))
            .andExpect(jsonPath("$.customizationClass").value(Base64Utils.encodeToString(DEFAULT_CUSTOMIZATION_CLASS)));
    }
    @Test
    @Transactional
    public void getNonExistingCertificateAuthority() throws Exception {
        // Get the certificateAuthority
        restCertificateAuthorityMockMvc.perform(get("/api/certificate-authorities/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCertificateAuthority() throws Exception {
        // Initialize the database
        certificateAuthorityRepository.saveAndFlush(certificateAuthority);

        int databaseSizeBeforeUpdate = certificateAuthorityRepository.findAll().size();

        // Update the certificateAuthority
        CertificateAuthority updatedCertificateAuthority = certificateAuthorityRepository.findById(certificateAuthority.getId()).get();
        // Disconnect from session so that the updates on updatedCertificateAuthority are not directly saved in db
        em.detach(updatedCertificateAuthority);
        updatedCertificateAuthority
            .name(UPDATED_NAME)
            .host(UPDATED_HOST)
            .customizationSource(UPDATED_CUSTOMIZATION_SOURCE)
            .customizationClass(UPDATED_CUSTOMIZATION_CLASS)
            .customizationClassContentType(UPDATED_CUSTOMIZATION_CLASS_CONTENT_TYPE);
        CertificateAuthorityDTO certificateAuthorityDTO = certificateAuthorityMapper.toDto(updatedCertificateAuthority);

        restCertificateAuthorityMockMvc.perform(put("/api/certificate-authorities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(certificateAuthorityDTO)))
            .andExpect(status().isOk());

        // Validate the CertificateAuthority in the database
        List<CertificateAuthority> certificateAuthorityList = certificateAuthorityRepository.findAll();
        assertThat(certificateAuthorityList).hasSize(databaseSizeBeforeUpdate);
        CertificateAuthority testCertificateAuthority = certificateAuthorityList.get(certificateAuthorityList.size() - 1);
        assertThat(testCertificateAuthority.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testCertificateAuthority.getHost()).isEqualTo(UPDATED_HOST);
        assertThat(testCertificateAuthority.getCustomizationSource()).isEqualTo(UPDATED_CUSTOMIZATION_SOURCE);
        assertThat(testCertificateAuthority.getCustomizationClass()).isEqualTo(UPDATED_CUSTOMIZATION_CLASS);
        assertThat(testCertificateAuthority.getCustomizationClassContentType()).isEqualTo(UPDATED_CUSTOMIZATION_CLASS_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingCertificateAuthority() throws Exception {
        int databaseSizeBeforeUpdate = certificateAuthorityRepository.findAll().size();

        // Create the CertificateAuthority
        CertificateAuthorityDTO certificateAuthorityDTO = certificateAuthorityMapper.toDto(certificateAuthority);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCertificateAuthorityMockMvc.perform(put("/api/certificate-authorities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(certificateAuthorityDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CertificateAuthority in the database
        List<CertificateAuthority> certificateAuthorityList = certificateAuthorityRepository.findAll();
        assertThat(certificateAuthorityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCertificateAuthority() throws Exception {
        // Initialize the database
        certificateAuthorityRepository.saveAndFlush(certificateAuthority);

        int databaseSizeBeforeDelete = certificateAuthorityRepository.findAll().size();

        // Get the certificateAuthority
        restCertificateAuthorityMockMvc.perform(delete("/api/certificate-authorities/{id}", certificateAuthority.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CertificateAuthority> certificateAuthorityList = certificateAuthorityRepository.findAll();
        assertThat(certificateAuthorityList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CertificateAuthority.class);
        CertificateAuthority certificateAuthority1 = new CertificateAuthority();
        certificateAuthority1.setId(1L);
        CertificateAuthority certificateAuthority2 = new CertificateAuthority();
        certificateAuthority2.setId(certificateAuthority1.getId());
        assertThat(certificateAuthority1).isEqualTo(certificateAuthority2);
        certificateAuthority2.setId(2L);
        assertThat(certificateAuthority1).isNotEqualTo(certificateAuthority2);
        certificateAuthority1.setId(null);
        assertThat(certificateAuthority1).isNotEqualTo(certificateAuthority2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CertificateAuthorityDTO.class);
        CertificateAuthorityDTO certificateAuthorityDTO1 = new CertificateAuthorityDTO();
        certificateAuthorityDTO1.setId(1L);
        CertificateAuthorityDTO certificateAuthorityDTO2 = new CertificateAuthorityDTO();
        assertThat(certificateAuthorityDTO1).isNotEqualTo(certificateAuthorityDTO2);
        certificateAuthorityDTO2.setId(certificateAuthorityDTO1.getId());
        assertThat(certificateAuthorityDTO1).isEqualTo(certificateAuthorityDTO2);
        certificateAuthorityDTO2.setId(2L);
        assertThat(certificateAuthorityDTO1).isNotEqualTo(certificateAuthorityDTO2);
        certificateAuthorityDTO1.setId(null);
        assertThat(certificateAuthorityDTO1).isNotEqualTo(certificateAuthorityDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(certificateAuthorityMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(certificateAuthorityMapper.fromId(null)).isNull();
    }
}
