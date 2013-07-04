package com.bigcay.exhubs.service.impl;

import java.util.List;
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
import com.bigcay.exhubs.model.ExamEvent;
import com.bigcay.exhubs.model.ExamPaper;
import com.bigcay.exhubs.model.ExamType;
import com.bigcay.exhubs.model.QuestionSubject;
import com.bigcay.exhubs.repository.ExamEventRepository;
import com.bigcay.exhubs.repository.ExamPaperRepository;
import com.bigcay.exhubs.repository.ExamTypeRepository;
import com.bigcay.exhubs.repository.QuestionSubjectRepository;
import com.bigcay.exhubs.service.ExamService;

@Service
@Transactional
public class ExamServiceImpl implements ExamService {

	@Autowired
	private ExamTypeRepository examTypeRepository;

	@Autowired
	private ExamPaperRepository examPaperRepository;
	
	@Autowired
	private ExamEventRepository examEventRepository;
	
	@Autowired
	private QuestionSubjectRepository questionSubjectRepository;

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

	@Override
	public ExamPaper persist(ExamPaper examPaper) {
		return examPaperRepository.save(examPaper);
	}

	@Override
	public Page<ExamPaper> findPageableExamPapers(Integer pageNumber) {
		PageRequest pageRequest = new PageRequest(pageNumber, GlobalManager.DEFAULT_PAGE_SIZE, Sort.Direction.ASC, "id");
		return examPaperRepository.findAll(pageRequest);
	}

	@Override
	public ExamPaper findExamPaperByName(String name) {
		return examPaperRepository.findByName(name);
	}

	@Override
	public List<ExamType> findAllExamTypes() {
		return examTypeRepository.findAll();
	}

	@Override
	public ExamPaper findExamPaperById(Integer id) {
		return examPaperRepository.findOne(id);
	}

	@Override
	public ValidationResult validateBeforeDeleteExamPaper(Integer examPaperId, Locale locale) {
		// TO-DO

		ValidationResult result = new ValidationResult(ResultType.SUCCESS);
		return result;
	}

	@Override
	public void deleteExamPaper(Integer examPaperId) {
		examPaperRepository.delete(examPaperId);
	}

	@Override
	public List<QuestionSubject> findQuestionSubjectsByExamPaperId(Integer examPaperId) {
		return questionSubjectRepository.findByExamPapers_Id(examPaperId);
	}

	@Override
	public Page<ExamEvent> findPageableExamEvents(Integer pageNumber) {
		PageRequest pageRequest = new PageRequest(pageNumber, GlobalManager.DEFAULT_PAGE_SIZE, Sort.Direction.ASC, "id");
		return examEventRepository.findAll(pageRequest);
	}

	@Override
	public List<ExamPaper> findAllExamPapers() {
		return examPaperRepository.findAll();
	}

	@Override
	public ExamEvent persist(ExamEvent examEvent) {
		return examEventRepository.save(examEvent);
	}

	@Override
	public ExamEvent findExamEventByName(String name) {
		return examEventRepository.findByName(name);
	}

}
