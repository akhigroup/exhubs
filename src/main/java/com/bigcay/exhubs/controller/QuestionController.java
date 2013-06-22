package com.bigcay.exhubs.controller;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bigcay.exhubs.common.ResponseResult;
import com.bigcay.exhubs.common.ResultType;
import com.bigcay.exhubs.common.ValidationResult;
import com.bigcay.exhubs.model.QuestionSubject;
import com.bigcay.exhubs.model.QuestionType;
import com.bigcay.exhubs.service.QuestionService;
import com.bigcay.exhubs.util.QuestionUtil;

@Controller
public class QuestionController extends BaseController {

	@Autowired
	MessageSource messageSource;

	@Autowired
	private QuestionService questionService;

	private static final Logger logger = LoggerFactory.getLogger(QuestionController.class);

	@ModelAttribute("questionChoices")
	public String[] getAllQuestionChoices() {
		return QuestionUtil.getQuestionChoices();
	}

	@RequestMapping("questiontypes")
	public String questionTypeIndexHandler() {

		logger.debug("QuestionController.questionTypeIndexHandler is invoked.");

		return "questiontypes/index";
	}

	@RequestMapping("ajax/questiontypes/show_question_types")
	public String showQuestionTypesAjaxHandler(Model model) {

		logger.debug("QuestionController.showQuestionTypesAjaxHandler is invoked.");

		List<QuestionType> questionTypes = questionService.findAllQuestionTypes();

		model.addAttribute("questionTypes", questionTypes);

		return "ajax/questiontypes/show_question_types";
	}

	@RequestMapping("questionrepos")
	public String questionRepoIndexHandler() {

		logger.debug("QuestionController.questionRepoIndexHandler is invoked.");

		return "questionrepos/index";
	}

	@RequestMapping("ajax/questionrepos/show_question_subjects")
	public String showQuestionSubjectsAjaxHandler(Model model) {

		logger.debug("QuestionController.showQuestionSubjectsAjaxHandler is invoked.");

		List<QuestionSubject> questionSubjects = questionService.findAllQuestionSubjects();

		model.addAttribute("questionSubjects", questionSubjects);

		return "ajax/questionrepos/show_question_subjects";
	}

	@RequestMapping("question_subject/{questionSubjectId}")
	public String selectQuestionSubjectHandler(Model model, @PathVariable Integer questionSubjectId) {

		logger.debug("QuestionController.selectQuestionSubjectHandler is invoked.");

		QuestionSubject questionSubject = questionService.findQuestionSubjectById(questionSubjectId);

		model.addAttribute("questionSubject", questionSubject);

		return "questionrepos/select_question_subject";
	}

	@RequestMapping(value = "/rest/questionrepos/delete_question_subject", method = RequestMethod.POST)
	public @ResponseBody
	ResponseResult deleteQuestionSubjectRestHandler(Locale locale, @RequestParam("deleteId") Integer deleteId) {

		logger.debug("QuestionController.deleteQuestionSubjectRestHandler is invoked.");

		ValidationResult validationResult = questionService.validateBeforeDeleteQuestionSubject(deleteId, locale);

		if (ResultType.SUCCESS == validationResult.getResultType()) {
			questionService.deleteQuestionSubject(deleteId);
		}

		ResponseResult responseResult = new ResponseResult(validationResult);
		return responseResult;
	}

}
