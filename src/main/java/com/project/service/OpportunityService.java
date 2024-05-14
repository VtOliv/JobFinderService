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
import com.project.domains.filter.OpportunityFilter;
import com.project.domains.form.OpportunityForm;
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
		var count = repository.count();

		domain.setId(count + 1);

		return repository.save(domain);
	}

	public Opportunity update(OpportunityForm form, Long id) {
		var result = repository.findById(id).orElse(null);

		if (result != null) {
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
		addCriteria(filter, query);

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

	private void addCriteria(OpportunityFilter filter, Query query) {

		if (filter.getId() != null) {
			query.addCriteria(Criteria.where("_id").is(filter.getId()));
		}

		if (filter.getCompanyName() != null) {
			query.addCriteria(Criteria.where("companyName").regex(filter.getCompanyName(), "i"));
		}

		if (filter.getJobName() != null) {
			query.addCriteria(Criteria.where("jobName").regex(filter.getJobName(), "i"));

		}

		if (filter.getOfficeHour() != null) {
			query.addCriteria(Criteria.where("officeHour").regex(filter.getOfficeHour()));
		}
	}

	public Opportunity getById(Long id) {
		return repository.findById(id).orElse(null);
	}

	public Page<Opportunity> getAllBySavedById(Long id, Pageable pageable) {
		return repository.findAllBySavedById(id, pageable);
	}

	public String deleteById(Long id) {

		var opToDelete = repository.findById(id);
		opToDelete.ifPresent(item -> {
			item.setIsActive(false);
			repository.save(item);
		});
		var savedItem = repository.findById(id).orElse(null);

		if (savedItem.getIsActive()) {
			return "Ativo";
		} else {
			return "Desativado";
		}
	}
}
