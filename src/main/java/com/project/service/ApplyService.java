package com.project.service;

import static java.util.stream.Collectors.toList;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.project.domains.Apply;
import com.project.domains.Opportunity;
import com.project.domains.form.ApplyForm;
import com.project.domains.form.ReplyForm;
import com.project.domains.view.RecruiterOverallView;
import com.project.repository.ApplyRepository;
import com.project.repository.OpportunityRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApplyService {

	private final ApplyRepository applyRepository;
	private final OpportunityRepository opRepo;

	public Apply apply(ApplyForm applyForm) {
		var count = applyRepository.count();

		var apply = Apply.builder()
				.id(count + 1)
				.jobId(applyForm.getJobId())
				.jobName(findbyId(applyForm.getJobId()))
				.userId(applyForm.getUserId())
				.applyMessage(applyForm.getApplyMessage())
				.status("Enviado")
				.lastUpdate(LocalDateTime.now())
				.build();

		return applyRepository.save(apply);
	}

	public Apply recruiterReply(Long id, ReplyForm reply) {
		var apply = applyRepository.findById(id).orElse(null);

		apply.setReplyMessage(reply.getReplyMessage());
		apply.setStatus("Em processo");
		apply.setLastUpdate(LocalDateTime.now());

		return applyRepository.save(apply);
	}

	public String findbyId(Long id) {
		var job = opRepo.findById(id).orElse(null);

		return job.getJobName();
	}

	public List<Apply> findByUser(Long userId){
		return applyRepository.findAllByUserId(userId);
	}
	
	
	public RecruiterOverallView getOverall(Long id) {
		var myOpportunities = opRepo.findAllBySavedById(id);
		
		var jobIdList = myOpportunities.stream().map(Opportunity::getId).collect(toList());
		var applies = applyRepository.findByJobIdIn(jobIdList);
		
		var replies = applies.stream().filter(item -> item.getStatus().equals("Enviado")).collect(toList());
	
		return RecruiterOverallView.builder()
				.appliesCount(applies.size())
				.opportunityCount(myOpportunities.size())
				.repliesCount(replies.size())
				.build();
	}
	
	public List<Apply> getAllRecruiterPendingApplies(Long id){
		var myOpportunities = opRepo.findAllBySavedById(id);
		
		var jobIdList = myOpportunities.stream().map(Opportunity::getId).collect(toList());
		var applies = applyRepository.findByJobIdIn(jobIdList);
		
		return applies.stream().filter(item -> item.getStatus().equals("Enviado")).collect(toList());
	}
}
