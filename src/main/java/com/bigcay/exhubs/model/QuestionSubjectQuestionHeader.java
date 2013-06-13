package com.bigcay.exhubs.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(QuestionSubjectQuestionHeaderPK.class)
@Table(name = "question_subject_question_header")
public class QuestionSubjectQuestionHeader {

	@Id
	private QuestionSubject questionSubject;

	@Id
	private QuestionHeader questionHeader;

	public QuestionSubject getQuestionSubject() {
		return questionSubject;
	}

	public void setQuestionSubject(QuestionSubject questionSubject) {
		this.questionSubject = questionSubject;
	}

	public QuestionHeader getQuestionHeader() {
		return questionHeader;
	}

	public void setQuestionHeader(QuestionHeader questionHeader) {
		this.questionHeader = questionHeader;
	}

}
