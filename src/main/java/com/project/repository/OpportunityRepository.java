package com.project.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.project.domains.Opportunity;

public interface OpportunityRepository extends MongoRepository<Opportunity, Long> {
	
	Page<Opportunity> findAllById(Long id, Pageable pageable);
	Page<Opportunity> findAllBySavedById(Long savedById, Pageable pageable);
	List<Opportunity> findAllBySavedById(Long savedById	);
}
