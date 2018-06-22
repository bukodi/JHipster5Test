package com.bukodi.jh5.gerbera.web.rest;

import com.bukodi.jh5.gerbera.JHipster5TestApp;

import com.bukodi.jh5.gerbera.domain.DirectoryServer;
import com.bukodi.jh5.gerbera.repository.DirectoryServerRepository;
import com.bukodi.jh5.gerbera.service.DirectoryServerService;
import com.bukodi.jh5.gerbera.service.dto.DirectoryServerDTO;
import com.bukodi.jh5.gerbera.service.mapper.DirectoryServerMapper;
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
 * Test class for the DirectoryServerResource REST controller.
 *
 * @see DirectoryServerResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JHipster5TestApp.class)
public class DirectoryServerResourceIntTest {

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
    private DirectoryServerRepository directoryServerRepository;


    @Autowired
    private DirectoryServerMapper directoryServerMapper;
    

    @Autowired
    private DirectoryServerService directoryServerService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restDirectoryServerMockMvc;

    private DirectoryServer directoryServer;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DirectoryServerResource directoryServerResource = new DirectoryServerResource(directoryServerService);
        this.restDirectoryServerMockMvc = MockMvcBuilders.standaloneSetup(directoryServerResource)
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
    public static DirectoryServer createEntity(EntityManager em) {
        DirectoryServer directoryServer = new DirectoryServer()
            .name(DEFAULT_NAME)
            .host(DEFAULT_HOST)
            .customizationSource(DEFAULT_CUSTOMIZATION_SOURCE)
            .customizationClass(DEFAULT_CUSTOMIZATION_CLASS)
            .customizationClassContentType(DEFAULT_CUSTOMIZATION_CLASS_CONTENT_TYPE);
        return directoryServer;
    }

    @Before
    public void initTest() {
        directoryServer = createEntity(em);
    }

    @Test
    @Transactional
    public void createDirectoryServer() throws Exception {
        int databaseSizeBeforeCreate = directoryServerRepository.findAll().size();

        // Create the DirectoryServer
        DirectoryServerDTO directoryServerDTO = directoryServerMapper.toDto(directoryServer);
        restDirectoryServerMockMvc.perform(post("/api/directory-servers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(directoryServerDTO)))
            .andExpect(status().isCreated());

        // Validate the DirectoryServer in the database
        List<DirectoryServer> directoryServerList = directoryServerRepository.findAll();
        assertThat(directoryServerList).hasSize(databaseSizeBeforeCreate + 1);
        DirectoryServer testDirectoryServer = directoryServerList.get(directoryServerList.size() - 1);
        assertThat(testDirectoryServer.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testDirectoryServer.getHost()).isEqualTo(DEFAULT_HOST);
        assertThat(testDirectoryServer.getCustomizationSource()).isEqualTo(DEFAULT_CUSTOMIZATION_SOURCE);
        assertThat(testDirectoryServer.getCustomizationClass()).isEqualTo(DEFAULT_CUSTOMIZATION_CLASS);
        assertThat(testDirectoryServer.getCustomizationClassContentType()).isEqualTo(DEFAULT_CUSTOMIZATION_CLASS_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void createDirectoryServerWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = directoryServerRepository.findAll().size();

        // Create the DirectoryServer with an existing ID
        directoryServer.setId(1L);
        DirectoryServerDTO directoryServerDTO = directoryServerMapper.toDto(directoryServer);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDirectoryServerMockMvc.perform(post("/api/directory-servers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(directoryServerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DirectoryServer in the database
        List<DirectoryServer> directoryServerList = directoryServerRepository.findAll();
        assertThat(directoryServerList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = directoryServerRepository.findAll().size();
        // set the field null
        directoryServer.setName(null);

        // Create the DirectoryServer, which fails.
        DirectoryServerDTO directoryServerDTO = directoryServerMapper.toDto(directoryServer);

        restDirectoryServerMockMvc.perform(post("/api/directory-servers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(directoryServerDTO)))
            .andExpect(status().isBadRequest());

        List<DirectoryServer> directoryServerList = directoryServerRepository.findAll();
        assertThat(directoryServerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkHostIsRequired() throws Exception {
        int databaseSizeBeforeTest = directoryServerRepository.findAll().size();
        // set the field null
        directoryServer.setHost(null);

        // Create the DirectoryServer, which fails.
        DirectoryServerDTO directoryServerDTO = directoryServerMapper.toDto(directoryServer);

        restDirectoryServerMockMvc.perform(post("/api/directory-servers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(directoryServerDTO)))
            .andExpect(status().isBadRequest());

        List<DirectoryServer> directoryServerList = directoryServerRepository.findAll();
        assertThat(directoryServerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDirectoryServers() throws Exception {
        // Initialize the database
        directoryServerRepository.saveAndFlush(directoryServer);

        // Get all the directoryServerList
        restDirectoryServerMockMvc.perform(get("/api/directory-servers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(directoryServer.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].host").value(hasItem(DEFAULT_HOST.toString())))
            .andExpect(jsonPath("$.[*].customizationSource").value(hasItem(DEFAULT_CUSTOMIZATION_SOURCE.toString())))
            .andExpect(jsonPath("$.[*].customizationClassContentType").value(hasItem(DEFAULT_CUSTOMIZATION_CLASS_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].customizationClass").value(hasItem(Base64Utils.encodeToString(DEFAULT_CUSTOMIZATION_CLASS))));
    }
    

    @Test
    @Transactional
    public void getDirectoryServer() throws Exception {
        // Initialize the database
        directoryServerRepository.saveAndFlush(directoryServer);

        // Get the directoryServer
        restDirectoryServerMockMvc.perform(get("/api/directory-servers/{id}", directoryServer.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(directoryServer.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.host").value(DEFAULT_HOST.toString()))
            .andExpect(jsonPath("$.customizationSource").value(DEFAULT_CUSTOMIZATION_SOURCE.toString()))
            .andExpect(jsonPath("$.customizationClassContentType").value(DEFAULT_CUSTOMIZATION_CLASS_CONTENT_TYPE))
            .andExpect(jsonPath("$.customizationClass").value(Base64Utils.encodeToString(DEFAULT_CUSTOMIZATION_CLASS)));
    }
    @Test
    @Transactional
    public void getNonExistingDirectoryServer() throws Exception {
        // Get the directoryServer
        restDirectoryServerMockMvc.perform(get("/api/directory-servers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDirectoryServer() throws Exception {
        // Initialize the database
        directoryServerRepository.saveAndFlush(directoryServer);

        int databaseSizeBeforeUpdate = directoryServerRepository.findAll().size();

        // Update the directoryServer
        DirectoryServer updatedDirectoryServer = directoryServerRepository.findById(directoryServer.getId()).get();
        // Disconnect from session so that the updates on updatedDirectoryServer are not directly saved in db
        em.detach(updatedDirectoryServer);
        updatedDirectoryServer
            .name(UPDATED_NAME)
            .host(UPDATED_HOST)
            .customizationSource(UPDATED_CUSTOMIZATION_SOURCE)
            .customizationClass(UPDATED_CUSTOMIZATION_CLASS)
            .customizationClassContentType(UPDATED_CUSTOMIZATION_CLASS_CONTENT_TYPE);
        DirectoryServerDTO directoryServerDTO = directoryServerMapper.toDto(updatedDirectoryServer);

        restDirectoryServerMockMvc.perform(put("/api/directory-servers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(directoryServerDTO)))
            .andExpect(status().isOk());

        // Validate the DirectoryServer in the database
        List<DirectoryServer> directoryServerList = directoryServerRepository.findAll();
        assertThat(directoryServerList).hasSize(databaseSizeBeforeUpdate);
        DirectoryServer testDirectoryServer = directoryServerList.get(directoryServerList.size() - 1);
        assertThat(testDirectoryServer.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testDirectoryServer.getHost()).isEqualTo(UPDATED_HOST);
        assertThat(testDirectoryServer.getCustomizationSource()).isEqualTo(UPDATED_CUSTOMIZATION_SOURCE);
        assertThat(testDirectoryServer.getCustomizationClass()).isEqualTo(UPDATED_CUSTOMIZATION_CLASS);
        assertThat(testDirectoryServer.getCustomizationClassContentType()).isEqualTo(UPDATED_CUSTOMIZATION_CLASS_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingDirectoryServer() throws Exception {
        int databaseSizeBeforeUpdate = directoryServerRepository.findAll().size();

        // Create the DirectoryServer
        DirectoryServerDTO directoryServerDTO = directoryServerMapper.toDto(directoryServer);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restDirectoryServerMockMvc.perform(put("/api/directory-servers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(directoryServerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DirectoryServer in the database
        List<DirectoryServer> directoryServerList = directoryServerRepository.findAll();
        assertThat(directoryServerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDirectoryServer() throws Exception {
        // Initialize the database
        directoryServerRepository.saveAndFlush(directoryServer);

        int databaseSizeBeforeDelete = directoryServerRepository.findAll().size();

        // Get the directoryServer
        restDirectoryServerMockMvc.perform(delete("/api/directory-servers/{id}", directoryServer.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<DirectoryServer> directoryServerList = directoryServerRepository.findAll();
        assertThat(directoryServerList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DirectoryServer.class);
        DirectoryServer directoryServer1 = new DirectoryServer();
        directoryServer1.setId(1L);
        DirectoryServer directoryServer2 = new DirectoryServer();
        directoryServer2.setId(directoryServer1.getId());
        assertThat(directoryServer1).isEqualTo(directoryServer2);
        directoryServer2.setId(2L);
        assertThat(directoryServer1).isNotEqualTo(directoryServer2);
        directoryServer1.setId(null);
        assertThat(directoryServer1).isNotEqualTo(directoryServer2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DirectoryServerDTO.class);
        DirectoryServerDTO directoryServerDTO1 = new DirectoryServerDTO();
        directoryServerDTO1.setId(1L);
        DirectoryServerDTO directoryServerDTO2 = new DirectoryServerDTO();
        assertThat(directoryServerDTO1).isNotEqualTo(directoryServerDTO2);
        directoryServerDTO2.setId(directoryServerDTO1.getId());
        assertThat(directoryServerDTO1).isEqualTo(directoryServerDTO2);
        directoryServerDTO2.setId(2L);
        assertThat(directoryServerDTO1).isNotEqualTo(directoryServerDTO2);
        directoryServerDTO1.setId(null);
        assertThat(directoryServerDTO1).isNotEqualTo(directoryServerDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(directoryServerMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(directoryServerMapper.fromId(null)).isNull();
    }
}
