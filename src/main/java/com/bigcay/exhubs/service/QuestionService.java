package com.bigcay.exhubs.service;

import java.util.List;

import com.bigcay.exhubs.bean.QuestionTypeBean;
import com.bigcay.exhubs.model.QuestionAnswer;
import com.bigcay.exhubs.model.QuestionDetail;
import com.bigcay.exhubs.model.QuestionHeader;
import com.bigcay.exhubs.model.QuestionSubject;
import com.bigcay.exhubs.model.QuestionType;

public interface QuestionService {

	QuestionType findQuestionTypeById(Integer id);

	QuestionSubject findQuestionSubjectById(Integer id);

	QuestionAnswer findQuestionAnswerById(Integer id);

	QuestionHeader findQuestionHeaderById(Integer id);
	
	QuestionDetail findQuestionDetailById(Integer id);
	
	List<QuestionTypeBean> findAllQuestionTypeBeans();

}
