package com.bigcay.exhubs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bigcay.exhubs.model.QuestionAnswer;
import com.bigcay.exhubs.model.QuestionDetail;
import com.bigcay.exhubs.model.QuestionHeader;
import com.bigcay.exhubs.model.QuestionSubject;
import com.bigcay.exhubs.model.QuestionType;
import com.bigcay.exhubs.repository.QuestionAnswerRepository;
import com.bigcay.exhubs.repository.QuestionDetailRepository;
import com.bigcay.exhubs.repository.QuestionHeaderRepository;
import com.bigcay.exhubs.repository.QuestionSubjectRepository;
import com.bigcay.exhubs.repository.QuestionTypeRepository;
import com.bigcay.exhubs.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionTypeRepository questionTypeRepository;

	@Autowired
	private QuestionSubjectRepository questionSubjectRepository;

	@Autowired
	private QuestionAnswerRepository questionAnswerRepository;

	@Autowired
	private QuestionHeaderRepository questionHeaderRepository;

	@Autowired
	private QuestionDetailRepository questionDetailRepository;

	@Override
	public QuestionType findQuestionTypeById(Integer id) {
		return questionTypeRepository.findOne(id);
	}

	@Override
	public QuestionSubject findQuestionSubjectById(Integer id) {
		return questionSubjectRepository.findOne(id);
	}

	@Override
	public QuestionAnswer findQuestionAnswerById(Integer id) {
		return questionAnswerRepository.findOne(id);
	}

	@Override
	public QuestionHeader findQuestionHeaderById(Integer id) {
		return questionHeaderRepository.findOne(id);
	}

	@Override
	public QuestionDetail findQuestionDetailById(Integer id) {
		return questionDetailRepository.findOne(id);
	}

	@Override
	public List<QuestionType> findAllQuestionTypes() {
		return questionTypeRepository.findAll();
	}

	@Override
	public List<QuestionSubject> findAllQuestionSubjects() {
		return questionSubjectRepository.findAll();
	}

}
