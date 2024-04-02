package com.project.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.domains.Opportunity;
import com.project.domains.OpportunityFilter;
import com.project.domains.OpportunityForm;
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

		return ResponseEntity.status(HttpStatus.CREATED).body(createdOpportunity);
	}

	@PutMapping("/update/{id}")
	private ResponseEntity<Opportunity> updateOpportunity(@PathVariable Long id, @RequestBody OpportunityForm form) {

		log.info("form={}", form);
		
		var updatedOpportunity = service.update(form, id);

		log.info("method={} id={}", "updateOpportunity", updatedOpportunity.getId());

		return ResponseEntity.status(HttpStatus.OK).body(updatedOpportunity);
	}

	@GetMapping("find")
	private ResponseEntity<Page<Opportunity>> findOpportunity(OpportunityFilter filter, Pageable pageable) {

		log.info("filter={} method={}", filter, "findOpportunity");

		var response = service.getFiltered(filter, pageable);

		log.info("filter={} quantityFound={}", filter, response.getTotalElements());

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@GetMapping
	private ResponseEntity<Page<Opportunity>> findAll(Pageable pageable) {

		log.info("method={}", "findAll");

		var response = service.getAllPaginated(pageable);

		log.info("quantityFound={}", response.getTotalElements());

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}
