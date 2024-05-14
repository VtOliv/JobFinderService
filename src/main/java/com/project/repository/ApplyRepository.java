package com.project.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.project.domains.Apply;

public interface ApplyRepository extends MongoRepository<Apply, Long> {
	List<Apply> findAllByJobId(Long jobId);
	List<Apply> findAllByUserId(Long userId);
	List<Apply> findByJobIdIn(List<Long> jobIds);
}
