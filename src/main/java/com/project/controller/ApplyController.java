package com.project.controller;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.project.domains.Apply;
import com.project.domains.form.ApplyForm;
import com.project.domains.form.ReplyForm;
import com.project.domains.view.RecruiterOverallView;
import com.project.service.ApplyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ApplyController {

	private final ApplyService service;

	
	@PostMapping("/apply")
	private ResponseEntity<Apply> apply(@RequestBody ApplyForm form) {
		log.info("form={}", form);
		
		var response = service.apply(form);
		
		log.info("method={} id={}", "apply", response.getId());
		
		return ResponseEntity.status(OK).body(response);
	}
	
	@PostMapping("/reply/{id}")
	private ResponseEntity<Apply> reply(@PathVariable Long id,@RequestBody ReplyForm form) {
		log.info("form={}", form);
		
		var response = service.recruiterReply(id, form);
		
		log.info("method={} id={}", "reply", response.getId());
		
		return ResponseEntity.status(OK).body(response);
	}
	
	@GetMapping("/myapplies/{id}")
	private ResponseEntity<List<Apply>> list(@PathVariable Long id){
		var response = service.findByUser(id);
		
		log.info("method={} size={}", "list", response.size());
		
		return ResponseEntity.status(OK).body(response);
	}
	
	@GetMapping("/overall/{id}")
	private ResponseEntity<RecruiterOverallView> getOverall(@PathVariable Long id){
		var response = service.getOverall(id);
		
		log.info("method={} opportunitiesFound={}", "getOverall", response.getOpportunityCount());
		
		return ResponseEntity.status(OK).body(response);
	}
	
	@GetMapping("/applies/{id}")
	private ResponseEntity<List<Apply>> getRecruiterApplies(@PathVariable Long id){
		var response = service.getAllRecruiterPendingApplies(id);
		
		log.info("method={} size={}", "getRecruiterApplies", response.size());
		
		return ResponseEntity.status(OK).body(response);
	}
}
