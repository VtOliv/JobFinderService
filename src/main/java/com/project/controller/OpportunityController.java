package com.project.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.domains.Opportunity;
import com.project.domains.filter.OpportunityFilter;
import com.project.domains.form.OpportunityForm;
import com.project.service.OpportunityService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
public class OpportunityController {

	private final OpportunityService service;

	@PostMapping("/create")
	private ResponseEntity<Opportunity> createOpportunity(@RequestBody OpportunityForm form) {

		log.info("form={}", form);

		var createdOpportunity = service.create(form);

		log.info("method={} id={}", "createOpportunity", createdOpportunity.getId());

		return ResponseEntity.status(CREATED).body(createdOpportunity);
	}

	@PutMapping("/update/{id}")
	private ResponseEntity<Opportunity> updateOpportunity(@PathVariable Long id, @RequestBody OpportunityForm form) {

		log.info("form={}", form);

		var updatedOpportunity = service.update(form, id);

		log.info("method={} id={}", "updateOpportunity", updatedOpportunity.getId());

		return ResponseEntity.status(OK).body(updatedOpportunity);
	}

	@GetMapping("find")
	private ResponseEntity<Page<Opportunity>> findOpportunity(OpportunityFilter filter,
			@PageableDefault(size = 10, direction = Direction.ASC) Pageable pageable) {

		log.info("filter={} method={}", filter, "findOpportunity");

		var response = service.getFiltered(filter, pageable);

		log.info("filter={} quantityFound={}", filter, response.getTotalElements());

		return ResponseEntity.status(OK).body(response);
	}

	@GetMapping
	private ResponseEntity<Page<Opportunity>> findAll(
			@PageableDefault(size = 10, direction = Direction.ASC) Pageable pageable) {

		log.info("method={}", "findAll");

		var response = service.getAllPaginated(pageable);

		log.info("quantityFound={}", response.getTotalElements());

		return ResponseEntity.status(OK).body(response);
	}

	@GetMapping("/{id}")
	private ResponseEntity<Opportunity> findOpportunityById(@PathVariable Long id) {

		log.info("id={} method={}", id, "findOpportunityById");

		var response = service.getById(id);

		log.info("id={} name={}", id, response.getJobName());

		return ResponseEntity.status(OK).body(response);
	}

	@GetMapping("/recruiter/{id}")
	private ResponseEntity<Page<Opportunity>> findOpportunityByRecruiterId(@PathVariable Long id, Pageable pageable) {

		log.info("id={} method={}", id, "findOpportunityByRecruiterId");

		var response = service.getAllBySavedById(id, pageable);

		log.info("id={} jobsFound={}", id, response.getNumberOfElements());

		return ResponseEntity.status(OK).body(response);
	}
	
	@PostMapping("/deactivate")
	private ResponseEntity<String> deleteOpportunity(@RequestBody Long id){
		
		log.info("id={} method={}", id, "findOpportunityByRecruiterId");

		var response = service.deleteById(id);

		log.info("id={} response={}", response);
		
		return ResponseEntity.status(200).body(response);
		
	}
}
