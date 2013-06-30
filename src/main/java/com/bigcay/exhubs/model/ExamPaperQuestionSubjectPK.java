package com.bigcay.exhubs.model;

import java.io.Serializable;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class ExamPaperQuestionSubjectPK implements Serializable {

	private static final long serialVersionUID = -6834275365857565217L;

	@ManyToOne
	@JoinColumn(name = "exam_paper_id", referencedColumnName = "id")
	private ExamPaper examPaper;

	@ManyToOne
	@JoinColumn(name = "question_subject_id", referencedColumnName = "id")
	private QuestionSubject questionSubject;

}
