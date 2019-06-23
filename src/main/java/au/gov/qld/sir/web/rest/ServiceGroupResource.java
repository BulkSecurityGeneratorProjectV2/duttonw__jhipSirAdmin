package au.gov.qld.sir.web.rest;

import au.gov.qld.sir.service.ServiceGroupService;
import au.gov.qld.sir.web.rest.errors.BadRequestAlertException;
import au.gov.qld.sir.service.dto.ServiceGroupDTO;
import au.gov.qld.sir.service.dto.ServiceGroupCriteria;
import au.gov.qld.sir.service.ServiceGroupQueryService;

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
 * REST controller for managing {@link au.gov.qld.sir.domain.ServiceGroup}.
 */
@RestController
@RequestMapping("/api")
public class ServiceGroupResource {

    private final Logger log = LoggerFactory.getLogger(ServiceGroupResource.class);

    private static final String ENTITY_NAME = "serviceGroup";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ServiceGroupService serviceGroupService;

    private final ServiceGroupQueryService serviceGroupQueryService;

    public ServiceGroupResource(ServiceGroupService serviceGroupService, ServiceGroupQueryService serviceGroupQueryService) {
        this.serviceGroupService = serviceGroupService;
        this.serviceGroupQueryService = serviceGroupQueryService;
    }

    /**
     * {@code POST  /service-groups} : Create a new serviceGroup.
     *
     * @param serviceGroupDTO the serviceGroupDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new serviceGroupDTO, or with status {@code 400 (Bad Request)} if the serviceGroup has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/service-groups")
    public ResponseEntity<ServiceGroupDTO> createServiceGroup(@Valid @RequestBody ServiceGroupDTO serviceGroupDTO) throws URISyntaxException {
        log.debug("REST request to save ServiceGroup : {}", serviceGroupDTO);
        if (serviceGroupDTO.getId() != null) {
            throw new BadRequestAlertException("A new serviceGroup cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ServiceGroupDTO result = serviceGroupService.save(serviceGroupDTO);
        return ResponseEntity.created(new URI("/api/service-groups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /service-groups} : Updates an existing serviceGroup.
     *
     * @param serviceGroupDTO the serviceGroupDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated serviceGroupDTO,
     * or with status {@code 400 (Bad Request)} if the serviceGroupDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the serviceGroupDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/service-groups")
    public ResponseEntity<ServiceGroupDTO> updateServiceGroup(@Valid @RequestBody ServiceGroupDTO serviceGroupDTO) throws URISyntaxException {
        log.debug("REST request to update ServiceGroup : {}", serviceGroupDTO);
        if (serviceGroupDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ServiceGroupDTO result = serviceGroupService.save(serviceGroupDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, serviceGroupDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /service-groups} : get all the serviceGroups.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of serviceGroups in body.
     */
    @GetMapping("/service-groups")
    public ResponseEntity<List<ServiceGroupDTO>> getAllServiceGroups(ServiceGroupCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get ServiceGroups by criteria: {}", criteria);
        Page<ServiceGroupDTO> page = serviceGroupQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /service-groups/count} : count all the serviceGroups.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/service-groups/count")
    public ResponseEntity<Long> countServiceGroups(ServiceGroupCriteria criteria) {
        log.debug("REST request to count ServiceGroups by criteria: {}", criteria);
        return ResponseEntity.ok().body(serviceGroupQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /service-groups/:id} : get the "id" serviceGroup.
     *
     * @param id the id of the serviceGroupDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the serviceGroupDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/service-groups/{id}")
    public ResponseEntity<ServiceGroupDTO> getServiceGroup(@PathVariable Long id) {
        log.debug("REST request to get ServiceGroup : {}", id);
        Optional<ServiceGroupDTO> serviceGroupDTO = serviceGroupService.findOne(id);
        return ResponseUtil.wrapOrNotFound(serviceGroupDTO);
    }

    /**
     * {@code DELETE  /service-groups/:id} : delete the "id" serviceGroup.
     *
     * @param id the id of the serviceGroupDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/service-groups/{id}")
    public ResponseEntity<Void> deleteServiceGroup(@PathVariable Long id) {
        log.debug("REST request to delete ServiceGroup : {}", id);
        serviceGroupService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
