package com.bigcay.exhubs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bigcay.exhubs.model.SubmitQuestionHeader;

public interface SubmitQuestionHeaderRepository extends JpaRepository<SubmitQuestionHeader, Integer> {

	SubmitQuestionHeader findByExamEventIdAndCandidateIdAndQuestionHeaderId(Integer examEventId, Integer candidateUserId, Integer questionHeaderId);
	
}
