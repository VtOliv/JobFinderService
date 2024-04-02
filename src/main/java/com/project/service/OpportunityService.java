package com.project.service;

import static java.util.Optional.ofNullable;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.project.domains.Opportunity;
import com.project.domains.OpportunityFilter;
import com.project.domains.OpportunityForm;
import com.project.repository.OpportunityRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OpportunityService {

	private final ModelMapper mapper;
	private final MongoTemplate template;
	private final OpportunityRepository repository;

	public Opportunity create(OpportunityForm form) {
		var domain = mapper.map(form, Opportunity.class);

		return repository.save(domain);
	}

//	public Page<Opportunity> getFiltered(OpportunityFilter filter, Pageable pageable) {
//
//		if (of(filter.getId()).isPresent()) {
//
//			return repository.findAllById(filter.getId(), pageable);
//		} else if (of(filter.getJobName()).isPresent()) {
//
//			return repository.findAllByJobNameIgnoreCase(filter.getJobName(), pageable);
//		} else if (of(filter.getCompanyName()).isPresent()) {
//
//			return repository.findAllByCompanyNameIgnoreCase(filter.getCompanyName(), pageable);
//		} else if (of(filter.getOfficeHour()).isPresent()) {
//
//			return repository.findAllByOfficeHourIgnoreCase(filter.getOfficeHour(), pageable);
//		} else {
//
//			return repository.findAll(pageable);
//		}
//	}

	public Opportunity update(OpportunityForm form, Long id) {
		var result = repository.findById(id).orElse(null);
		
		if(result != null) {
			result.setIncome(form.getIncome());
			result.setJobName(form.getJobName());
			result.setOfficeHour(form.getOfficeHour());
			result.setDescription(form.getDescription());
			result.setCompanyName(form.getCompanyName());
			result.setShortDescription(form.getShortDescription());
		}
		
		return repository.save(result);
	}
	
	public Page<Opportunity> getFiltered(OpportunityFilter filter, Pageable pageable) {
		
		var query = buildQuery(pageable);
		addCriteria(filter ,query);
		
		var response = template.find(query, Opportunity.class);
		var list = response.stream().map(p -> mapper.map(p, Opportunity.class)).collect(Collectors.toList());
		
		return new PageImpl<>(list);
	}
	
	public Page<Opportunity> getAllPaginated(Pageable pageable) {
		return repository.findAll(pageable);
	}
	
	private Query buildQuery(Pageable pageable) {
		var query = new Query().cursorBatchSize(15000);
		
		ofNullable(pageable).ifPresent(query::with);
		return query;
	}
	
	private void addCriteria(OpportunityFilter filter , Query query) {
		
		if(filter.getId() != null) {
			query.addCriteria(Criteria.where("_id").is(filter.getId()));
		}
		
		if(filter.getCompanyName() != null) {
			query.addCriteria(Criteria.where("companyName").regex(filter.getCompanyName()));
		}
		
		if(filter.getJobName() != null) {
			query.addCriteria(Criteria.where("jobName").regex(filter.getJobName()));
		}
		
		if(filter.getOfficeHour() != null) {
			query.addCriteria(Criteria.where("officeHour").regex(filter.getOfficeHour()));
		}
	}
}
