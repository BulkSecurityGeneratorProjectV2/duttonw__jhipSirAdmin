package au.gov.qld.sir.web.rest;

import au.gov.qld.sir.service.AgencyService;
import au.gov.qld.sir.web.rest.errors.BadRequestAlertException;
import au.gov.qld.sir.service.dto.AgencyDTO;
import au.gov.qld.sir.service.dto.AgencyCriteria;
import au.gov.qld.sir.service.AgencyQueryService;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link au.gov.qld.sir.domain.Agency}.
 */
@RestController
@RequestMapping("/api")
public class AgencyResource {

    private final Logger log = LoggerFactory.getLogger(AgencyResource.class);

    private static final String ENTITY_NAME = "agency";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AgencyService agencyService;

    private final AgencyQueryService agencyQueryService;

    public AgencyResource(AgencyService agencyService, AgencyQueryService agencyQueryService) {
        this.agencyService = agencyService;
        this.agencyQueryService = agencyQueryService;
    }

    /**
     * {@code POST  /agencies} : Create a new agency.
     *
     * @param agencyDTO the agencyDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new agencyDTO, or with status {@code 400 (Bad Request)} if the agency has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/agencies")
    public ResponseEntity<AgencyDTO> createAgency(@Valid @RequestBody AgencyDTO agencyDTO) throws URISyntaxException {
        log.debug("REST request to save Agency : {}", agencyDTO);
        if (agencyDTO.getId() != null) {
            throw new BadRequestAlertException("A new agency cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AgencyDTO result = agencyService.save(agencyDTO);
        return ResponseEntity.created(new URI("/api/agencies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /agencies} : Updates an existing agency.
     *
     * @param agencyDTO the agencyDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated agencyDTO,
     * or with status {@code 400 (Bad Request)} if the agencyDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the agencyDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/agencies")
    public ResponseEntity<AgencyDTO> updateAgency(@Valid @RequestBody AgencyDTO agencyDTO) throws URISyntaxException {
        log.debug("REST request to update Agency : {}", agencyDTO);
        if (agencyDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AgencyDTO result = agencyService.save(agencyDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, agencyDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /agencies} : get all the agencies.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of agencies in body.
     */
    @GetMapping("/agencies")
    public ResponseEntity<List<AgencyDTO>> getAllAgencies(AgencyCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get Agencies by criteria: {}", criteria);
        Page<AgencyDTO> page = agencyQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /agencies/count} : count all the agencies.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/agencies/count")
    public ResponseEntity<Long> countAgencies(AgencyCriteria criteria) {
        log.debug("REST request to count Agencies by criteria: {}", criteria);
        return ResponseEntity.ok().body(agencyQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /agencies/:id} : get the "id" agency.
     *
     * @param id the id of the agencyDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the agencyDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/agencies/{id}")
    public ResponseEntity<AgencyDTO> getAgency(@PathVariable Long id) {
        log.debug("REST request to get Agency : {}", id);
        Optional<AgencyDTO> agencyDTO = agencyService.findOne(id);
        return ResponseUtil.wrapOrNotFound(agencyDTO);
    }

    /**
     * {@code DELETE  /agencies/:id} : delete the "id" agency.
     *
     * @param id the id of the agencyDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/agencies/{id}")
    public ResponseEntity<Void> deleteAgency(@PathVariable Long id) {
        log.debug("REST request to delete Agency : {}", id);
        agencyService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
