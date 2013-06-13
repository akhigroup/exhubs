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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name = "question_subjects")
public class QuestionSubject {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "content")
	private String content;

	@Column(name = "total_score")
	private Integer totalScore;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "question_type_id")
	private QuestionType questionType;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany(mappedBy = "questionSubject", fetch = FetchType.LAZY)
	private Set<QuestionSubjectQuestionHeader> questionSubjectQuestionHeaders;

	@ManyToMany(fetch = FetchType.EAGER)
	@OrderBy("id ASC")
	@JoinTable(name = "question_subject_question_header", joinColumns = { @JoinColumn(name = "question_subject_id") }, inverseJoinColumns = { @JoinColumn(name = "question_header_id") })
	private Set<QuestionHeader> questionHeaders = new HashSet<QuestionHeader>();

	public Set<QuestionSubjectQuestionHeader> getQuestionSubjectQuestionHeaders() {
		return questionSubjectQuestionHeaders;
	}

	public void setQuestionSubjectQuestionHeaders(Set<QuestionSubjectQuestionHeader> questionSubjectQuestionHeaders) {
		this.questionSubjectQuestionHeaders = questionSubjectQuestionHeaders;
	}

	public Set<QuestionHeader> getQuestionHeaders() {
		return questionHeaders;
	}

	public void setQuestionHeaders(Set<QuestionHeader> questionHeaders) {
		this.questionHeaders = questionHeaders;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(Integer totalScore) {
		this.totalScore = totalScore;
	}

	public QuestionType getQuestionType() {
		return questionType;
	}

	public void setQuestionType(QuestionType questionType) {
		this.questionType = questionType;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
