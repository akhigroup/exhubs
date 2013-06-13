package com.bigcay.exhubs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bigcay.exhubs.model.QuestionAnswer;
import com.bigcay.exhubs.model.QuestionDetail;
import com.bigcay.exhubs.model.QuestionHeader;
import com.bigcay.exhubs.model.QuestionSubject;
import com.bigcay.exhubs.model.QuestionType;
import com.bigcay.exhubs.service.QuestionService;

@Controller
public class DemoController {

	private static final Logger logger = LoggerFactory.getLogger(DemoController.class);

	@Autowired
	private QuestionService questionService;

	@RequestMapping(value = "/demo", method = RequestMethod.GET)
	public String indexHandler() {

		logger.info("DemoController.indexHandler is invoked.");

		QuestionType questionType = questionService.findQuestionTypeById(1);
		logger.debug("-----------------------------------------");
		logger.debug(questionType.getDescription());

		QuestionSubject questionSubject = questionService.findQuestionSubjectById(1);
		logger.debug("-----------------------------------------");
		logger.debug("* content:" + questionSubject.getContent());
		logger.debug("** questionType:" + questionSubject.getQuestionType().getDescription());
		logger.debug("*** userId:" + questionSubject.getUser().getUserId());

		QuestionAnswer questionAnswer = questionService.findQuestionAnswerById(1);
		logger.debug("-----------------------------------------");
		logger.debug("* binary_value:" + questionAnswer.getBinaryValue());

		QuestionHeader questionHeader = questionService.findQuestionHeaderById(1);
		logger.debug("-----------------------------------------");
		logger.debug("* header.description:" + questionHeader.getDescription());
		logger.debug("* header.questionAnswer.binary_value:" + questionHeader.getQuestionAnswer().getBinaryValue());

		logger.debug("-----------------------------------------");
		for (QuestionDetail questionDetail : questionHeader.getQuestionDetails()) {
			logger.debug("detail:" + questionDetail.getSortOrder() + "," + questionDetail.getContent());
		}

		logger.debug("-----------------------------------------");
		for (QuestionHeader questionHeaderItem : questionSubject.getQuestionHeaders()) {
			logger.debug("*** questionHeaderItem: " + questionHeaderItem.getId() + ","
					+ questionHeaderItem.getDescription() + "," + questionHeaderItem.getScore());
		}

		return "demo/index";
	}

}
