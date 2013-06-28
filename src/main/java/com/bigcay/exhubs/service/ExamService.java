package com.bigcay.exhubs.service;

import java.util.Locale;

import org.springframework.data.domain.Page;

import com.bigcay.exhubs.common.ValidationResult;
import com.bigcay.exhubs.model.ExamType;

public interface ExamService {

	Page<ExamType> findPageableExamTypes(Integer pageNumber);

	ExamType findExamTypeById(Integer id);
	
	ExamType findExamTypeByName(String name);

	ExamType persist(ExamType examType);
	
	ValidationResult validateBeforeDeleteExamType(Integer examTypeId, Locale locale);
	
	void deleteExamType(Integer examTypeId);
}
