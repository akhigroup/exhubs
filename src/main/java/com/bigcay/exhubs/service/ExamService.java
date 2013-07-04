package com.bigcay.exhubs.service;

import java.util.List;
import java.util.Locale;

import org.springframework.data.domain.Page;

import com.bigcay.exhubs.common.ValidationResult;
import com.bigcay.exhubs.model.ExamEvent;
import com.bigcay.exhubs.model.ExamPaper;
import com.bigcay.exhubs.model.ExamType;
import com.bigcay.exhubs.model.QuestionSubject;

public interface ExamService {

	Page<ExamType> findPageableExamTypes(Integer pageNumber);

	ExamType findExamTypeById(Integer id);
	
	ExamType findExamTypeByName(String name);

	ExamType persist(ExamType examType);
	
	List<ExamType> findAllExamTypes();
	
	ValidationResult validateBeforeDeleteExamType(Integer examTypeId, Locale locale);
	
	void deleteExamType(Integer examTypeId);
	
	List<ExamPaper> findAllExamPapers();
	
	Page<ExamPaper> findPageableExamPapers(Integer pageNumber);
	
	ExamPaper findExamPaperById(Integer id);
	
	ExamPaper findExamPaperByName(String name);
	
	ExamPaper persist(ExamPaper examPaper);
	
	ValidationResult validateBeforeDeleteExamPaper(Integer examPaperId, Locale locale);
	
	void deleteExamPaper(Integer examPaperId);
	
	List<QuestionSubject> findQuestionSubjectsByExamPaperId(Integer examPaperId);
	
	Page<ExamEvent> findPageableExamEvents(Integer pageNumber);
	
	ExamEvent findExamEventById(Integer id);
	
	ExamEvent findExamEventByName(String name);
	
	ExamEvent persist(ExamEvent examEvent);
}
