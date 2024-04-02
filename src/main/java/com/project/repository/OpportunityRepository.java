package com.project.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.project.domains.Opportunity;

public interface OpportunityRepository extends MongoRepository<Opportunity, Long> {
	
	Page<Opportunity> findAllById(Long id, Pageable pageable);
	Page<Opportunity> findAllByJobNameIgnoreCase(String jobName, Pageable pageable);
	Page<Opportunity> findAllByOfficeHourIgnoreCase(String officeHour, Pageable pageable);
	Page<Opportunity> findAllByCompanyNameIgnoreCase(String companyName, Pageable pageable);
}
