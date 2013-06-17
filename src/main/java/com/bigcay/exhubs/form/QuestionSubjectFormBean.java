package com.bigcay.exhubs.form;

public class QuestionSubjectFormBean {

	private Integer id;

	private String content;

	private Integer totalScore;
	
	private Integer questionTypeId;

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

	public Integer getQuestionTypeId() {
		return questionTypeId;
	}

	public void setQuestionTypeId(Integer questionTypeId) {
		this.questionTypeId = questionTypeId;
	}

}
