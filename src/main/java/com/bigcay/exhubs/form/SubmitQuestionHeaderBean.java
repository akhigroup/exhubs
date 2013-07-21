package com.bigcay.exhubs.form;

public class SubmitQuestionHeaderBean {

	private Integer questionHeaderId;

	private SubmitQuestionAnswerBean submitQuestionAnswerBean;

	private Integer radioSelectedIndex;

	public Integer getRadioSelectedIndex() {
		return radioSelectedIndex;
	}

	public void setRadioSelectedIndex(Integer radioSelectedIndex) {
		this.radioSelectedIndex = radioSelectedIndex;
	}

	public Integer getQuestionHeaderId() {
		return questionHeaderId;
	}

	public void setQuestionHeaderId(Integer questionHeaderId) {
		this.questionHeaderId = questionHeaderId;
	}

	public SubmitQuestionAnswerBean getSubmitQuestionAnswerBean() {
		return submitQuestionAnswerBean;
	}

	public void setSubmitQuestionAnswerBean(SubmitQuestionAnswerBean submitQuestionAnswerBean) {
		this.submitQuestionAnswerBean = submitQuestionAnswerBean;
	}

}
