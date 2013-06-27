package com.bigcay.exhubs.service;

import java.util.List;
import java.util.Locale;

import com.bigcay.exhubs.common.ValidationResult;
import com.bigcay.exhubs.form.QuestionSubjectFormBean;
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

	List<QuestionType> findAllQuestionTypes();

	List<QuestionSubject> findAllQuestionSubjects();

	QuestionSubject persist(QuestionSubject questionSubject);

	ValidationResult validateBeforeDeleteQuestionSubject(Integer questionSubjectId, Locale locale);
	
	ValidationResult validateBeforeCreateQuestionSubject(QuestionSubjectFormBean questionSubjectFormBean, Locale locale);
	
	void deleteQuestionSubject(Integer questionSubjectId);
}
