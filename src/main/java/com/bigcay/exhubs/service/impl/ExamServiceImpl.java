package com.bigcay.exhubs.service.impl;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bigcay.exhubs.common.GlobalManager;
import com.bigcay.exhubs.common.ResultType;
import com.bigcay.exhubs.common.ValidationResult;
import com.bigcay.exhubs.model.ExamType;
import com.bigcay.exhubs.repository.ExamTypeRepository;
import com.bigcay.exhubs.service.ExamService;

@Service
@Transactional
public class ExamServiceImpl implements ExamService {

	@Autowired
	private ExamTypeRepository examTypeRepository;

	@Override
	public Page<ExamType> findPageableExamTypes(Integer pageNumber) {
		PageRequest pageRequest = new PageRequest(pageNumber, GlobalManager.DEFAULT_PAGE_SIZE, Sort.Direction.ASC, "id");
		return examTypeRepository.findAll(pageRequest);
	}

	@Override
	public ExamType findExamTypeByName(String name) {
		return examTypeRepository.findByName(name);
	}

	@Override
	public ExamType persist(ExamType examType) {
		return examTypeRepository.save(examType);
	}

	@Override
	public ExamType findExamTypeById(Integer id) {
		return examTypeRepository.findOne(id);
	}

	@Override
	public ValidationResult validateBeforeDeleteExamType(Integer examTypeId, Locale locale) {
		// TO-DO

		ValidationResult result = new ValidationResult(ResultType.SUCCESS);
		return result;
	}

	@Override
	public void deleteExamType(Integer examTypeId) {
		examTypeRepository.delete(examTypeId);
	}

}
