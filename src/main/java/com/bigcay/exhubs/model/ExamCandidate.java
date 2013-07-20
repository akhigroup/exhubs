package com.bigcay.exhubs.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(ExamCandidatePK.class)
@Table(name = "exam_candidates")
public class ExamCandidate {

	@Id
	private ExamEvent examEvent;

	@Id
	private User user;

	@Column(name = "submit_datetime")
	private Date submitDateTime;

	@Column(name = "final_score")
	private Integer finalScore;

	@Column(name = "done_flg")
	private Boolean doneFlag;

	public ExamEvent getExamEvent() {
		return examEvent;
	}

	public void setExamEvent(ExamEvent examEvent) {
		this.examEvent = examEvent;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getFinalScore() {
		return finalScore;
	}

	public void setFinalScore(Integer finalScore) {
		this.finalScore = finalScore;
	}

	public Boolean getDoneFlag() {
		return doneFlag;
	}

	public void setDoneFlag(Boolean doneFlag) {
		this.doneFlag = doneFlag;
	}
	
	public Date getSubmitDateTime() {
		return submitDateTime;
	}

	public void setSubmitDateTime(Date submitDateTime) {
		this.submitDateTime = submitDateTime;
	}

}
