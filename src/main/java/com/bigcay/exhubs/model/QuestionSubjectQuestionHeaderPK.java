package com.bigcay.exhubs.model;

import java.io.Serializable;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class QuestionSubjectQuestionHeaderPK implements Serializable {

	private static final long serialVersionUID = -898560473504269780L;

	@ManyToOne
	@JoinColumn(name = "question_subject_id", referencedColumnName = "id")
	private QuestionSubject questionSubject;

	@ManyToOne
	@JoinColumn(name = "question_header_id", referencedColumnName = "id")
	private QuestionHeader questionHeader;

}
