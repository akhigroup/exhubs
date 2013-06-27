package com.bigcay.exhubs.service.impl;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bigcay.exhubs.common.ResultType;
import com.bigcay.exhubs.common.ValidationResult;
import com.bigcay.exhubs.form.QuestionDetailBean;
import com.bigcay.exhubs.form.QuestionHeaderBean;
import com.bigcay.exhubs.form.QuestionSubjectFormBean;
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
@Transactional
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

	@Override
	public QuestionSubject persist(QuestionSubject questionSubject) {
		return questionSubjectRepository.save(questionSubject);
	}

	@Override
	public void deleteQuestionSubject(Integer questionSubjectId) {
		questionSubjectRepository.delete(questionSubjectId);
	}

	@Override
	public ValidationResult validateBeforeDeleteQuestionSubject(Integer questionSubjectId, Locale locale) {
		// TO-DO

		ValidationResult result = new ValidationResult(ResultType.SUCCESS);
		return result;
	}

	@Override
	public ValidationResult validateBeforeCreateQuestionSubject(QuestionSubjectFormBean questionSubjectFormBean,
			Locale locale) {

		// let's suppose that there is no error at the beginning
		ValidationResult result = new ValidationResult(ResultType.SUCCESS);

		String questionTypeName = null;

		if (questionSubjectFormBean.getQuestionTypeId() == null || questionSubjectFormBean.getQuestionTypeId() == 0) {
			result.setResultType(ResultType.ERROR);
			result.addErrorMessage("Please select a question type!");
			return result;
		} else {
			questionTypeName = this.findQuestionTypeById(questionSubjectFormBean.getQuestionTypeId()).getName();
		}

		if (questionSubjectFormBean.getContent() == null || questionSubjectFormBean.getContent().isEmpty()) {
			result.setResultType(ResultType.ERROR);
			result.addErrorMessage("Question content should not be empty!");
			return result;
		}

		if (questionSubjectFormBean.getTotalScore() == null || questionSubjectFormBean.getTotalScore().intValue() == 0) {
			result.setResultType(ResultType.ERROR);
			result.addErrorMessage("Total score should not be empty!");
			return result;
		}

		List<QuestionHeaderBean> questionHeaderBeans = questionSubjectFormBean.getQuestionHeaderBeans();
		if (questionHeaderBeans == null || questionHeaderBeans.size() == 0) {
			result.setResultType(ResultType.ERROR);
			result.addErrorMessage("Question Header should not be empty!");
			return result;
		} else {
			int questionHeaderScoreSum = 0;

			for (QuestionHeaderBean questionHeaderBean : questionHeaderBeans) {
				if (questionHeaderBean.getDescription() == null || questionHeaderBean.getDescription().isEmpty()) {
					result.setResultType(ResultType.ERROR);
					result.addErrorMessage("Question Header Description should not be empty!");
					return result;
				}

				if (questionHeaderBean.getScore() == null || questionHeaderBean.getScore() == 0) {
					result.setResultType(ResultType.ERROR);
					result.addErrorMessage("Question Header Score should not be empty!");
					return result;
				} else {
					questionHeaderScoreSum += questionHeaderBean.getScore();
				}

				List<QuestionDetailBean> questionDetailBeans = questionHeaderBean.getQuestionDetailBeans();

				if ("SCQ".equalsIgnoreCase(questionTypeName) || "MCQ".equalsIgnoreCase(questionTypeName)) {
					if (questionDetailBeans == null || questionDetailBeans.size() == 0) {
						result.setResultType(ResultType.ERROR);
						result.addErrorMessage("Question Detail should not be empty!");
						return result;
					} else {
						boolean chkboxSelectedFlag = false;

						for (QuestionDetailBean questionDetailBean : questionDetailBeans) {
							if (questionDetailBean.getContent() == null || questionDetailBean.getContent().isEmpty()) {
								result.setResultType(ResultType.ERROR);
								result.addErrorMessage("Question Detail Content should not be empty!");
								return result;
							}

							if (questionDetailBean.getIsChecked() != null && questionDetailBean.getIsChecked()) {
								chkboxSelectedFlag = true;
							}
						}

						if ("SCQ".equalsIgnoreCase(questionTypeName)
								&& questionHeaderBean.getRadioSelectedIndex() == null) {
							result.setResultType(ResultType.ERROR);
							result.addErrorMessage("SCQ - Please select the right option!");
							return result;
						}

						if ("MCQ".equalsIgnoreCase(questionTypeName) && !chkboxSelectedFlag) {
							result.setResultType(ResultType.ERROR);
							result.addErrorMessage("MCQ - Please select the right option!");
							return result;
						}

					}
				}
			}

			if (questionSubjectFormBean.getTotalScore().intValue() != questionHeaderScoreSum) {
				result.setResultType(ResultType.ERROR);
				result.addErrorMessage("Total Socres & Item Scores do not match!");
				return result;
			}
		}

		return result;
	}

}
