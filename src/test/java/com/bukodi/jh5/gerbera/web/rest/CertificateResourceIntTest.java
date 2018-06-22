package com.bukodi.jh5.gerbera.web.rest;

import com.bukodi.jh5.gerbera.JHipster5TestApp;

import com.bukodi.jh5.gerbera.domain.Certificate;
import com.bukodi.jh5.gerbera.domain.CertificateAuthority;
import com.bukodi.jh5.gerbera.repository.CertificateRepository;
import com.bukodi.jh5.gerbera.service.CertificateService;
import com.bukodi.jh5.gerbera.service.dto.CertificateDTO;
import com.bukodi.jh5.gerbera.service.mapper.CertificateMapper;
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

import com.bukodi.jh5.gerbera.domain.enumeration.CertificateType;
/**
 * Test class for the CertificateResource REST controller.
 *
 * @see CertificateResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JHipster5TestApp.class)
public class CertificateResourceIntTest {

    private static final String DEFAULT_SUBJECT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SUBJECT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SERIAL_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_SERIAL_NUMBER = "BBBBBBBBBB";

    private static final CertificateType DEFAULT_TYPE = CertificateType.SIGN;
    private static final CertificateType UPDATED_TYPE = CertificateType.AUTH;

    private static final byte[] DEFAULT_CERT_DATA = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_CERT_DATA = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_CERT_DATA_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_CERT_DATA_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_PRIVATE_KEY = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_PRIVATE_KEY = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_PRIVATE_KEY_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_PRIVATE_KEY_CONTENT_TYPE = "image/png";

    @Autowired
    private CertificateRepository certificateRepository;


    @Autowired
    private CertificateMapper certificateMapper;
    

    @Autowired
    private CertificateService certificateService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCertificateMockMvc;

    private Certificate certificate;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CertificateResource certificateResource = new CertificateResource(certificateService);
        this.restCertificateMockMvc = MockMvcBuilders.standaloneSetup(certificateResource)
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
    public static Certificate createEntity(EntityManager em) {
        Certificate certificate = new Certificate()
            .subjectName(DEFAULT_SUBJECT_NAME)
            .serialNumber(DEFAULT_SERIAL_NUMBER)
            .type(DEFAULT_TYPE)
            .certData(DEFAULT_CERT_DATA)
            .certDataContentType(DEFAULT_CERT_DATA_CONTENT_TYPE)
            .privateKey(DEFAULT_PRIVATE_KEY)
            .privateKeyContentType(DEFAULT_PRIVATE_KEY_CONTENT_TYPE);
        // Add required entity
        CertificateAuthority certificateAuthority = CertificateAuthorityResourceIntTest.createEntity(em);
        em.persist(certificateAuthority);
        em.flush();
        certificate.setCa(certificateAuthority);
        return certificate;
    }

    @Before
    public void initTest() {
        certificate = createEntity(em);
    }

    @Test
    @Transactional
    public void createCertificate() throws Exception {
        int databaseSizeBeforeCreate = certificateRepository.findAll().size();

        // Create the Certificate
        CertificateDTO certificateDTO = certificateMapper.toDto(certificate);
        restCertificateMockMvc.perform(post("/api/certificates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(certificateDTO)))
            .andExpect(status().isCreated());

        // Validate the Certificate in the database
        List<Certificate> certificateList = certificateRepository.findAll();
        assertThat(certificateList).hasSize(databaseSizeBeforeCreate + 1);
        Certificate testCertificate = certificateList.get(certificateList.size() - 1);
        assertThat(testCertificate.getSubjectName()).isEqualTo(DEFAULT_SUBJECT_NAME);
        assertThat(testCertificate.getSerialNumber()).isEqualTo(DEFAULT_SERIAL_NUMBER);
        assertThat(testCertificate.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testCertificate.getCertData()).isEqualTo(DEFAULT_CERT_DATA);
        assertThat(testCertificate.getCertDataContentType()).isEqualTo(DEFAULT_CERT_DATA_CONTENT_TYPE);
        assertThat(testCertificate.getPrivateKey()).isEqualTo(DEFAULT_PRIVATE_KEY);
        assertThat(testCertificate.getPrivateKeyContentType()).isEqualTo(DEFAULT_PRIVATE_KEY_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void createCertificateWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = certificateRepository.findAll().size();

        // Create the Certificate with an existing ID
        certificate.setId(1L);
        CertificateDTO certificateDTO = certificateMapper.toDto(certificate);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCertificateMockMvc.perform(post("/api/certificates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(certificateDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Certificate in the database
        List<Certificate> certificateList = certificateRepository.findAll();
        assertThat(certificateList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkSubjectNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = certificateRepository.findAll().size();
        // set the field null
        certificate.setSubjectName(null);

        // Create the Certificate, which fails.
        CertificateDTO certificateDTO = certificateMapper.toDto(certificate);

        restCertificateMockMvc.perform(post("/api/certificates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(certificateDTO)))
            .andExpect(status().isBadRequest());

        List<Certificate> certificateList = certificateRepository.findAll();
        assertThat(certificateList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = certificateRepository.findAll().size();
        // set the field null
        certificate.setType(null);

        // Create the Certificate, which fails.
        CertificateDTO certificateDTO = certificateMapper.toDto(certificate);

        restCertificateMockMvc.perform(post("/api/certificates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(certificateDTO)))
            .andExpect(status().isBadRequest());

        List<Certificate> certificateList = certificateRepository.findAll();
        assertThat(certificateList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCertificates() throws Exception {
        // Initialize the database
        certificateRepository.saveAndFlush(certificate);

        // Get all the certificateList
        restCertificateMockMvc.perform(get("/api/certificates?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(certificate.getId().intValue())))
            .andExpect(jsonPath("$.[*].subjectName").value(hasItem(DEFAULT_SUBJECT_NAME.toString())))
            .andExpect(jsonPath("$.[*].serialNumber").value(hasItem(DEFAULT_SERIAL_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].certDataContentType").value(hasItem(DEFAULT_CERT_DATA_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].certData").value(hasItem(Base64Utils.encodeToString(DEFAULT_CERT_DATA))))
            .andExpect(jsonPath("$.[*].privateKeyContentType").value(hasItem(DEFAULT_PRIVATE_KEY_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].privateKey").value(hasItem(Base64Utils.encodeToString(DEFAULT_PRIVATE_KEY))));
    }
    

    @Test
    @Transactional
    public void getCertificate() throws Exception {
        // Initialize the database
        certificateRepository.saveAndFlush(certificate);

        // Get the certificate
        restCertificateMockMvc.perform(get("/api/certificates/{id}", certificate.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(certificate.getId().intValue()))
            .andExpect(jsonPath("$.subjectName").value(DEFAULT_SUBJECT_NAME.toString()))
            .andExpect(jsonPath("$.serialNumber").value(DEFAULT_SERIAL_NUMBER.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.certDataContentType").value(DEFAULT_CERT_DATA_CONTENT_TYPE))
            .andExpect(jsonPath("$.certData").value(Base64Utils.encodeToString(DEFAULT_CERT_DATA)))
            .andExpect(jsonPath("$.privateKeyContentType").value(DEFAULT_PRIVATE_KEY_CONTENT_TYPE))
            .andExpect(jsonPath("$.privateKey").value(Base64Utils.encodeToString(DEFAULT_PRIVATE_KEY)));
    }
    @Test
    @Transactional
    public void getNonExistingCertificate() throws Exception {
        // Get the certificate
        restCertificateMockMvc.perform(get("/api/certificates/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCertificate() throws Exception {
        // Initialize the database
        certificateRepository.saveAndFlush(certificate);

        int databaseSizeBeforeUpdate = certificateRepository.findAll().size();

        // Update the certificate
        Certificate updatedCertificate = certificateRepository.findById(certificate.getId()).get();
        // Disconnect from session so that the updates on updatedCertificate are not directly saved in db
        em.detach(updatedCertificate);
        updatedCertificate
            .subjectName(UPDATED_SUBJECT_NAME)
            .serialNumber(UPDATED_SERIAL_NUMBER)
            .type(UPDATED_TYPE)
            .certData(UPDATED_CERT_DATA)
            .certDataContentType(UPDATED_CERT_DATA_CONTENT_TYPE)
            .privateKey(UPDATED_PRIVATE_KEY)
            .privateKeyContentType(UPDATED_PRIVATE_KEY_CONTENT_TYPE);
        CertificateDTO certificateDTO = certificateMapper.toDto(updatedCertificate);

        restCertificateMockMvc.perform(put("/api/certificates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(certificateDTO)))
            .andExpect(status().isOk());

        // Validate the Certificate in the database
        List<Certificate> certificateList = certificateRepository.findAll();
        assertThat(certificateList).hasSize(databaseSizeBeforeUpdate);
        Certificate testCertificate = certificateList.get(certificateList.size() - 1);
        assertThat(testCertificate.getSubjectName()).isEqualTo(UPDATED_SUBJECT_NAME);
        assertThat(testCertificate.getSerialNumber()).isEqualTo(UPDATED_SERIAL_NUMBER);
        assertThat(testCertificate.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testCertificate.getCertData()).isEqualTo(UPDATED_CERT_DATA);
        assertThat(testCertificate.getCertDataContentType()).isEqualTo(UPDATED_CERT_DATA_CONTENT_TYPE);
        assertThat(testCertificate.getPrivateKey()).isEqualTo(UPDATED_PRIVATE_KEY);
        assertThat(testCertificate.getPrivateKeyContentType()).isEqualTo(UPDATED_PRIVATE_KEY_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingCertificate() throws Exception {
        int databaseSizeBeforeUpdate = certificateRepository.findAll().size();

        // Create the Certificate
        CertificateDTO certificateDTO = certificateMapper.toDto(certificate);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCertificateMockMvc.perform(put("/api/certificates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(certificateDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Certificate in the database
        List<Certificate> certificateList = certificateRepository.findAll();
        assertThat(certificateList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCertificate() throws Exception {
        // Initialize the database
        certificateRepository.saveAndFlush(certificate);

        int databaseSizeBeforeDelete = certificateRepository.findAll().size();

        // Get the certificate
        restCertificateMockMvc.perform(delete("/api/certificates/{id}", certificate.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Certificate> certificateList = certificateRepository.findAll();
        assertThat(certificateList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Certificate.class);
        Certificate certificate1 = new Certificate();
        certificate1.setId(1L);
        Certificate certificate2 = new Certificate();
        certificate2.setId(certificate1.getId());
        assertThat(certificate1).isEqualTo(certificate2);
        certificate2.setId(2L);
        assertThat(certificate1).isNotEqualTo(certificate2);
        certificate1.setId(null);
        assertThat(certificate1).isNotEqualTo(certificate2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CertificateDTO.class);
        CertificateDTO certificateDTO1 = new CertificateDTO();
        certificateDTO1.setId(1L);
        CertificateDTO certificateDTO2 = new CertificateDTO();
        assertThat(certificateDTO1).isNotEqualTo(certificateDTO2);
        certificateDTO2.setId(certificateDTO1.getId());
        assertThat(certificateDTO1).isEqualTo(certificateDTO2);
        certificateDTO2.setId(2L);
        assertThat(certificateDTO1).isNotEqualTo(certificateDTO2);
        certificateDTO1.setId(null);
        assertThat(certificateDTO1).isNotEqualTo(certificateDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(certificateMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(certificateMapper.fromId(null)).isNull();
    }
}
