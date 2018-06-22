package com.bukodi.jh5.gerbera.web.rest;

import com.bukodi.jh5.gerbera.JHipster5TestApp;

import com.bukodi.jh5.gerbera.domain.CustomProcess;
import com.bukodi.jh5.gerbera.repository.CustomProcessRepository;
import com.bukodi.jh5.gerbera.service.CustomProcessService;
import com.bukodi.jh5.gerbera.service.dto.CustomProcessDTO;
import com.bukodi.jh5.gerbera.service.mapper.CustomProcessMapper;
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
 * Test class for the CustomProcessResource REST controller.
 *
 * @see CustomProcessResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JHipster5TestApp.class)
public class CustomProcessResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_INTERFACE_FQN = "AAAAAAAAAA";
    private static final String UPDATED_INTERFACE_FQN = "BBBBBBBBBB";

    private static final Boolean DEFAULT_TEMPLATE = false;
    private static final Boolean UPDATED_TEMPLATE = true;

    private static final String DEFAULT_SOURCE = "AAAAAAAAAA";
    private static final String UPDATED_SOURCE = "BBBBBBBBBB";

    private static final String DEFAULT_SCHEDULING = "AAAAAAAAAA";
    private static final String UPDATED_SCHEDULING = "BBBBBBBBBB";

    @Autowired
    private CustomProcessRepository customProcessRepository;


    @Autowired
    private CustomProcessMapper customProcessMapper;
    

    @Autowired
    private CustomProcessService customProcessService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCustomProcessMockMvc;

    private CustomProcess customProcess;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CustomProcessResource customProcessResource = new CustomProcessResource(customProcessService);
        this.restCustomProcessMockMvc = MockMvcBuilders.standaloneSetup(customProcessResource)
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
    public static CustomProcess createEntity(EntityManager em) {
        CustomProcess customProcess = new CustomProcess()
            .name(DEFAULT_NAME)
            .interfaceFqn(DEFAULT_INTERFACE_FQN)
            .template(DEFAULT_TEMPLATE)
            .source(DEFAULT_SOURCE)
            .scheduling(DEFAULT_SCHEDULING);
        return customProcess;
    }

    @Before
    public void initTest() {
        customProcess = createEntity(em);
    }

    @Test
    @Transactional
    public void createCustomProcess() throws Exception {
        int databaseSizeBeforeCreate = customProcessRepository.findAll().size();

        // Create the CustomProcess
        CustomProcessDTO customProcessDTO = customProcessMapper.toDto(customProcess);
        restCustomProcessMockMvc.perform(post("/api/custom-processes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(customProcessDTO)))
            .andExpect(status().isCreated());

        // Validate the CustomProcess in the database
        List<CustomProcess> customProcessList = customProcessRepository.findAll();
        assertThat(customProcessList).hasSize(databaseSizeBeforeCreate + 1);
        CustomProcess testCustomProcess = customProcessList.get(customProcessList.size() - 1);
        assertThat(testCustomProcess.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testCustomProcess.getInterfaceFqn()).isEqualTo(DEFAULT_INTERFACE_FQN);
        assertThat(testCustomProcess.isTemplate()).isEqualTo(DEFAULT_TEMPLATE);
        assertThat(testCustomProcess.getSource()).isEqualTo(DEFAULT_SOURCE);
        assertThat(testCustomProcess.getScheduling()).isEqualTo(DEFAULT_SCHEDULING);
    }

    @Test
    @Transactional
    public void createCustomProcessWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = customProcessRepository.findAll().size();

        // Create the CustomProcess with an existing ID
        customProcess.setId(1L);
        CustomProcessDTO customProcessDTO = customProcessMapper.toDto(customProcess);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCustomProcessMockMvc.perform(post("/api/custom-processes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(customProcessDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CustomProcess in the database
        List<CustomProcess> customProcessList = customProcessRepository.findAll();
        assertThat(customProcessList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = customProcessRepository.findAll().size();
        // set the field null
        customProcess.setName(null);

        // Create the CustomProcess, which fails.
        CustomProcessDTO customProcessDTO = customProcessMapper.toDto(customProcess);

        restCustomProcessMockMvc.perform(post("/api/custom-processes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(customProcessDTO)))
            .andExpect(status().isBadRequest());

        List<CustomProcess> customProcessList = customProcessRepository.findAll();
        assertThat(customProcessList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkInterfaceFqnIsRequired() throws Exception {
        int databaseSizeBeforeTest = customProcessRepository.findAll().size();
        // set the field null
        customProcess.setInterfaceFqn(null);

        // Create the CustomProcess, which fails.
        CustomProcessDTO customProcessDTO = customProcessMapper.toDto(customProcess);

        restCustomProcessMockMvc.perform(post("/api/custom-processes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(customProcessDTO)))
            .andExpect(status().isBadRequest());

        List<CustomProcess> customProcessList = customProcessRepository.findAll();
        assertThat(customProcessList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCustomProcesses() throws Exception {
        // Initialize the database
        customProcessRepository.saveAndFlush(customProcess);

        // Get all the customProcessList
        restCustomProcessMockMvc.perform(get("/api/custom-processes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(customProcess.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].interfaceFqn").value(hasItem(DEFAULT_INTERFACE_FQN.toString())))
            .andExpect(jsonPath("$.[*].template").value(hasItem(DEFAULT_TEMPLATE.booleanValue())))
            .andExpect(jsonPath("$.[*].source").value(hasItem(DEFAULT_SOURCE.toString())))
            .andExpect(jsonPath("$.[*].scheduling").value(hasItem(DEFAULT_SCHEDULING.toString())));
    }
    

    @Test
    @Transactional
    public void getCustomProcess() throws Exception {
        // Initialize the database
        customProcessRepository.saveAndFlush(customProcess);

        // Get the customProcess
        restCustomProcessMockMvc.perform(get("/api/custom-processes/{id}", customProcess.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(customProcess.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.interfaceFqn").value(DEFAULT_INTERFACE_FQN.toString()))
            .andExpect(jsonPath("$.template").value(DEFAULT_TEMPLATE.booleanValue()))
            .andExpect(jsonPath("$.source").value(DEFAULT_SOURCE.toString()))
            .andExpect(jsonPath("$.scheduling").value(DEFAULT_SCHEDULING.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingCustomProcess() throws Exception {
        // Get the customProcess
        restCustomProcessMockMvc.perform(get("/api/custom-processes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCustomProcess() throws Exception {
        // Initialize the database
        customProcessRepository.saveAndFlush(customProcess);

        int databaseSizeBeforeUpdate = customProcessRepository.findAll().size();

        // Update the customProcess
        CustomProcess updatedCustomProcess = customProcessRepository.findById(customProcess.getId()).get();
        // Disconnect from session so that the updates on updatedCustomProcess are not directly saved in db
        em.detach(updatedCustomProcess);
        updatedCustomProcess
            .name(UPDATED_NAME)
            .interfaceFqn(UPDATED_INTERFACE_FQN)
            .template(UPDATED_TEMPLATE)
            .source(UPDATED_SOURCE)
            .scheduling(UPDATED_SCHEDULING);
        CustomProcessDTO customProcessDTO = customProcessMapper.toDto(updatedCustomProcess);

        restCustomProcessMockMvc.perform(put("/api/custom-processes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(customProcessDTO)))
            .andExpect(status().isOk());

        // Validate the CustomProcess in the database
        List<CustomProcess> customProcessList = customProcessRepository.findAll();
        assertThat(customProcessList).hasSize(databaseSizeBeforeUpdate);
        CustomProcess testCustomProcess = customProcessList.get(customProcessList.size() - 1);
        assertThat(testCustomProcess.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testCustomProcess.getInterfaceFqn()).isEqualTo(UPDATED_INTERFACE_FQN);
        assertThat(testCustomProcess.isTemplate()).isEqualTo(UPDATED_TEMPLATE);
        assertThat(testCustomProcess.getSource()).isEqualTo(UPDATED_SOURCE);
        assertThat(testCustomProcess.getScheduling()).isEqualTo(UPDATED_SCHEDULING);
    }

    @Test
    @Transactional
    public void updateNonExistingCustomProcess() throws Exception {
        int databaseSizeBeforeUpdate = customProcessRepository.findAll().size();

        // Create the CustomProcess
        CustomProcessDTO customProcessDTO = customProcessMapper.toDto(customProcess);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCustomProcessMockMvc.perform(put("/api/custom-processes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(customProcessDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CustomProcess in the database
        List<CustomProcess> customProcessList = customProcessRepository.findAll();
        assertThat(customProcessList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCustomProcess() throws Exception {
        // Initialize the database
        customProcessRepository.saveAndFlush(customProcess);

        int databaseSizeBeforeDelete = customProcessRepository.findAll().size();

        // Get the customProcess
        restCustomProcessMockMvc.perform(delete("/api/custom-processes/{id}", customProcess.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CustomProcess> customProcessList = customProcessRepository.findAll();
        assertThat(customProcessList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CustomProcess.class);
        CustomProcess customProcess1 = new CustomProcess();
        customProcess1.setId(1L);
        CustomProcess customProcess2 = new CustomProcess();
        customProcess2.setId(customProcess1.getId());
        assertThat(customProcess1).isEqualTo(customProcess2);
        customProcess2.setId(2L);
        assertThat(customProcess1).isNotEqualTo(customProcess2);
        customProcess1.setId(null);
        assertThat(customProcess1).isNotEqualTo(customProcess2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CustomProcessDTO.class);
        CustomProcessDTO customProcessDTO1 = new CustomProcessDTO();
        customProcessDTO1.setId(1L);
        CustomProcessDTO customProcessDTO2 = new CustomProcessDTO();
        assertThat(customProcessDTO1).isNotEqualTo(customProcessDTO2);
        customProcessDTO2.setId(customProcessDTO1.getId());
        assertThat(customProcessDTO1).isEqualTo(customProcessDTO2);
        customProcessDTO2.setId(2L);
        assertThat(customProcessDTO1).isNotEqualTo(customProcessDTO2);
        customProcessDTO1.setId(null);
        assertThat(customProcessDTO1).isNotEqualTo(customProcessDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(customProcessMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(customProcessMapper.fromId(null)).isNull();
    }
}
