package com.mycompany.myapp.web.rest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;

import com.mycompany.myapp.Application;
import com.mycompany.myapp.domain.Pais;
import com.mycompany.myapp.repository.PaisRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the PaisResource REST controller.
 *
 * @see PaisResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class PaisResourceTest {

    private static final String DEFAULT_NOME = "SAMPLE_TEXT";
    private static final String UPDATED_NOME = "UPDATED_TEXT";

    @Inject
    private PaisRepository paisRepository;

    private MockMvc restPaisMockMvc;

    private Pais pais;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        PaisResource paisResource = new PaisResource();
        ReflectionTestUtils.setField(paisResource, "paisRepository", paisRepository);
        this.restPaisMockMvc = MockMvcBuilders.standaloneSetup(paisResource).build();
    }

    @Before
    public void initTest() {
        pais = new Pais();
        pais.setNome(DEFAULT_NOME);
    }

    @Test
    @Transactional
    public void createPais() throws Exception {
        // Validate the database is empty
        assertThat(paisRepository.findAll()).hasSize(0);

        // Create the Pais
        restPaisMockMvc.perform(post("/api/paiss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(pais)))
                .andExpect(status().isOk());

        // Validate the Pais in the database
        List<Pais> paiss = paisRepository.findAll();
        assertThat(paiss).hasSize(1);
        Pais testPais = paiss.iterator().next();
        assertThat(testPais.getNome()).isEqualTo(DEFAULT_NOME);
    }

    @Test
    @Transactional
    public void getAllPaiss() throws Exception {
        // Initialize the database
        paisRepository.saveAndFlush(pais);

        // Get all the paiss
        restPaisMockMvc.perform(get("/api/paiss"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id").value(pais.getId().intValue()))
                .andExpect(jsonPath("$.[0].nome").value(DEFAULT_NOME.toString()));
    }

    @Test
    @Transactional
    public void getPais() throws Exception {
        // Initialize the database
        paisRepository.saveAndFlush(pais);

        // Get the pais
        restPaisMockMvc.perform(get("/api/paiss/{id}", pais.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(pais.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPais() throws Exception {
        // Get the pais
        restPaisMockMvc.perform(get("/api/paiss/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePais() throws Exception {
        // Initialize the database
        paisRepository.saveAndFlush(pais);

        // Update the pais
        pais.setNome(UPDATED_NOME);
        restPaisMockMvc.perform(post("/api/paiss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(pais)))
                .andExpect(status().isOk());

        // Validate the Pais in the database
        List<Pais> paiss = paisRepository.findAll();
        assertThat(paiss).hasSize(1);
        Pais testPais = paiss.iterator().next();
        assertThat(testPais.getNome()).isEqualTo(UPDATED_NOME);
    }

    @Test
    @Transactional
    public void deletePais() throws Exception {
        // Initialize the database
        paisRepository.saveAndFlush(pais);

        // Get the pais
        restPaisMockMvc.perform(delete("/api/paiss/{id}", pais.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Pais> paiss = paisRepository.findAll();
        assertThat(paiss).hasSize(0);
    }
}
