package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.domain.Pais;
import com.mycompany.myapp.repository.PaisRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * REST controller for managing Pais.
 */
@RestController
@RequestMapping("/api")
public class PaisResource {

    private final Logger log = LoggerFactory.getLogger(PaisResource.class);

    @Inject
    private PaisRepository paisRepository;

    /**
     * POST  /paiss -> Create a new pais.
     */
    @RequestMapping(value = "/paiss",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void create(@RequestBody Pais pais) {
        log.debug("REST request to save Pais : {}", pais);
        paisRepository.save(pais);
    }

    /**
     * GET  /paiss -> get all the paiss.
     */
    @RequestMapping(value = "/paiss",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Pais> getAll() {
        log.debug("REST request to get all Paiss");
        return paisRepository.findAll();
    }

    /**
     * GET  /paiss/:id -> get the "id" pais.
     */
    @RequestMapping(value = "/paiss/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Pais> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get Pais : {}", id);
        Pais pais = paisRepository.findOne(id);
        if (pais == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pais, HttpStatus.OK);
    }

    /**
     * DELETE  /paiss/:id -> delete the "id" pais.
     */
    @RequestMapping(value = "/paiss/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Pais : {}", id);
        paisRepository.delete(id);
    }
}
