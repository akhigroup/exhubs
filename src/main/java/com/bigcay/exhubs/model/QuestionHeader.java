package com.bigcay.exhubs.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name = "question_headers")
public class QuestionHeader {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "description")
	private String description;

	@Column(name = "score")
	private Integer score;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "question_type_id")
	private QuestionType questionType;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "question_answer_id")
	private QuestionAnswer questionAnswer;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "questionHeader")
	@OrderBy("sortOrder ASC")
	private Set<QuestionDetail> questionDetails = new HashSet<QuestionDetail>();

	@OneToMany(mappedBy = "questionHeader", fetch = FetchType.LAZY)
	private Set<QuestionSubjectQuestionHeader> questionSubjectQuestionHeaders;

	@ManyToMany(mappedBy = "questionHeaders", fetch = FetchType.LAZY)
	private Set<QuestionSubject> questionSubject = new HashSet<QuestionSubject>();

	public Set<QuestionSubjectQuestionHeader> getQuestionSubjectQuestionHeaders() {
		return questionSubjectQuestionHeaders;
	}

	public void setQuestionSubjectQuestionHeaders(Set<QuestionSubjectQuestionHeader> questionSubjectQuestionHeaders) {
		this.questionSubjectQuestionHeaders = questionSubjectQuestionHeaders;
	}

	public Set<QuestionSubject> getQuestionSubject() {
		return questionSubject;
	}

	public void setQuestionSubject(Set<QuestionSubject> questionSubject) {
		this.questionSubject = questionSubject;
	}

	public Set<QuestionDetail> getQuestionDetails() {
		return questionDetails;
	}

	public void setQuestionDetails(Set<QuestionDetail> questionDetails) {
		this.questionDetails = questionDetails;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public QuestionType getQuestionType() {
		return questionType;
	}

	public void setQuestionType(QuestionType questionType) {
		this.questionType = questionType;
	}

	public QuestionAnswer getQuestionAnswer() {
		return questionAnswer;
	}

	public void setQuestionAnswer(QuestionAnswer questionAnswer) {
		this.questionAnswer = questionAnswer;
	}

}
