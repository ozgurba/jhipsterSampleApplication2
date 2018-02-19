package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplication2App;

import io.github.jhipster.application.domain.StakeHolder;
import io.github.jhipster.application.repository.StakeHolderRepository;
import io.github.jhipster.application.service.StakeHolderService;
import io.github.jhipster.application.service.dto.StakeHolderDTO;
import io.github.jhipster.application.service.mapper.StakeHolderMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;

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

import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the StakeHolderResource REST controller.
 *
 * @see StakeHolderResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterSampleApplication2App.class)
public class StakeHolderResourceIntTest {

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_AGENCY = "AAAAAAAAAA";
    private static final String UPDATED_AGENCY = "BBBBBBBBBB";

    private static final String DEFAULT_UNIT = "AAAAAAAAAA";
    private static final String UPDATED_UNIT = "BBBBBBBBBB";

    @Autowired
    private StakeHolderRepository stakeHolderRepository;

    @Autowired
    private StakeHolderMapper stakeHolderMapper;

    @Autowired
    private StakeHolderService stakeHolderService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restStakeHolderMockMvc;

    private StakeHolder stakeHolder;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final StakeHolderResource stakeHolderResource = new StakeHolderResource(stakeHolderService);
        this.restStakeHolderMockMvc = MockMvcBuilders.standaloneSetup(stakeHolderResource)
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
    public static StakeHolder createEntity(EntityManager em) {
        StakeHolder stakeHolder = new StakeHolder()
            .description(DEFAULT_DESCRIPTION)
            .agency(DEFAULT_AGENCY)
            .unit(DEFAULT_UNIT);
        return stakeHolder;
    }

    @Before
    public void initTest() {
        stakeHolder = createEntity(em);
    }

    @Test
    @Transactional
    public void createStakeHolder() throws Exception {
        int databaseSizeBeforeCreate = stakeHolderRepository.findAll().size();

        // Create the StakeHolder
        StakeHolderDTO stakeHolderDTO = stakeHolderMapper.toDto(stakeHolder);
        restStakeHolderMockMvc.perform(post("/api/stake-holders")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stakeHolderDTO)))
            .andExpect(status().isCreated());

        // Validate the StakeHolder in the database
        List<StakeHolder> stakeHolderList = stakeHolderRepository.findAll();
        assertThat(stakeHolderList).hasSize(databaseSizeBeforeCreate + 1);
        StakeHolder testStakeHolder = stakeHolderList.get(stakeHolderList.size() - 1);
        assertThat(testStakeHolder.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testStakeHolder.getAgency()).isEqualTo(DEFAULT_AGENCY);
        assertThat(testStakeHolder.getUnit()).isEqualTo(DEFAULT_UNIT);
    }

    @Test
    @Transactional
    public void createStakeHolderWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = stakeHolderRepository.findAll().size();

        // Create the StakeHolder with an existing ID
        stakeHolder.setId(1L);
        StakeHolderDTO stakeHolderDTO = stakeHolderMapper.toDto(stakeHolder);

        // An entity with an existing ID cannot be created, so this API call must fail
        restStakeHolderMockMvc.perform(post("/api/stake-holders")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stakeHolderDTO)))
            .andExpect(status().isBadRequest());

        // Validate the StakeHolder in the database
        List<StakeHolder> stakeHolderList = stakeHolderRepository.findAll();
        assertThat(stakeHolderList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllStakeHolders() throws Exception {
        // Initialize the database
        stakeHolderRepository.saveAndFlush(stakeHolder);

        // Get all the stakeHolderList
        restStakeHolderMockMvc.perform(get("/api/stake-holders?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(stakeHolder.getId().intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].agency").value(hasItem(DEFAULT_AGENCY.toString())))
            .andExpect(jsonPath("$.[*].unit").value(hasItem(DEFAULT_UNIT.toString())));
    }

    @Test
    @Transactional
    public void getStakeHolder() throws Exception {
        // Initialize the database
        stakeHolderRepository.saveAndFlush(stakeHolder);

        // Get the stakeHolder
        restStakeHolderMockMvc.perform(get("/api/stake-holders/{id}", stakeHolder.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(stakeHolder.getId().intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.agency").value(DEFAULT_AGENCY.toString()))
            .andExpect(jsonPath("$.unit").value(DEFAULT_UNIT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingStakeHolder() throws Exception {
        // Get the stakeHolder
        restStakeHolderMockMvc.perform(get("/api/stake-holders/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateStakeHolder() throws Exception {
        // Initialize the database
        stakeHolderRepository.saveAndFlush(stakeHolder);
        int databaseSizeBeforeUpdate = stakeHolderRepository.findAll().size();

        // Update the stakeHolder
        StakeHolder updatedStakeHolder = stakeHolderRepository.findOne(stakeHolder.getId());
        // Disconnect from session so that the updates on updatedStakeHolder are not directly saved in db
        em.detach(updatedStakeHolder);
        updatedStakeHolder
            .description(UPDATED_DESCRIPTION)
            .agency(UPDATED_AGENCY)
            .unit(UPDATED_UNIT);
        StakeHolderDTO stakeHolderDTO = stakeHolderMapper.toDto(updatedStakeHolder);

        restStakeHolderMockMvc.perform(put("/api/stake-holders")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stakeHolderDTO)))
            .andExpect(status().isOk());

        // Validate the StakeHolder in the database
        List<StakeHolder> stakeHolderList = stakeHolderRepository.findAll();
        assertThat(stakeHolderList).hasSize(databaseSizeBeforeUpdate);
        StakeHolder testStakeHolder = stakeHolderList.get(stakeHolderList.size() - 1);
        assertThat(testStakeHolder.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testStakeHolder.getAgency()).isEqualTo(UPDATED_AGENCY);
        assertThat(testStakeHolder.getUnit()).isEqualTo(UPDATED_UNIT);
    }

    @Test
    @Transactional
    public void updateNonExistingStakeHolder() throws Exception {
        int databaseSizeBeforeUpdate = stakeHolderRepository.findAll().size();

        // Create the StakeHolder
        StakeHolderDTO stakeHolderDTO = stakeHolderMapper.toDto(stakeHolder);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restStakeHolderMockMvc.perform(put("/api/stake-holders")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stakeHolderDTO)))
            .andExpect(status().isCreated());

        // Validate the StakeHolder in the database
        List<StakeHolder> stakeHolderList = stakeHolderRepository.findAll();
        assertThat(stakeHolderList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteStakeHolder() throws Exception {
        // Initialize the database
        stakeHolderRepository.saveAndFlush(stakeHolder);
        int databaseSizeBeforeDelete = stakeHolderRepository.findAll().size();

        // Get the stakeHolder
        restStakeHolderMockMvc.perform(delete("/api/stake-holders/{id}", stakeHolder.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<StakeHolder> stakeHolderList = stakeHolderRepository.findAll();
        assertThat(stakeHolderList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(StakeHolder.class);
        StakeHolder stakeHolder1 = new StakeHolder();
        stakeHolder1.setId(1L);
        StakeHolder stakeHolder2 = new StakeHolder();
        stakeHolder2.setId(stakeHolder1.getId());
        assertThat(stakeHolder1).isEqualTo(stakeHolder2);
        stakeHolder2.setId(2L);
        assertThat(stakeHolder1).isNotEqualTo(stakeHolder2);
        stakeHolder1.setId(null);
        assertThat(stakeHolder1).isNotEqualTo(stakeHolder2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(StakeHolderDTO.class);
        StakeHolderDTO stakeHolderDTO1 = new StakeHolderDTO();
        stakeHolderDTO1.setId(1L);
        StakeHolderDTO stakeHolderDTO2 = new StakeHolderDTO();
        assertThat(stakeHolderDTO1).isNotEqualTo(stakeHolderDTO2);
        stakeHolderDTO2.setId(stakeHolderDTO1.getId());
        assertThat(stakeHolderDTO1).isEqualTo(stakeHolderDTO2);
        stakeHolderDTO2.setId(2L);
        assertThat(stakeHolderDTO1).isNotEqualTo(stakeHolderDTO2);
        stakeHolderDTO1.setId(null);
        assertThat(stakeHolderDTO1).isNotEqualTo(stakeHolderDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(stakeHolderMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(stakeHolderMapper.fromId(null)).isNull();
    }
}
