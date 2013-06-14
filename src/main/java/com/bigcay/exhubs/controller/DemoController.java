package com.bigcay.exhubs.controller;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bigcay.exhubs.bean.GroupBean;
import com.bigcay.exhubs.form.UserFormBean;
import com.bigcay.exhubs.form.UserFormBeanValidator;
import com.bigcay.exhubs.model.QuestionAnswer;
import com.bigcay.exhubs.model.QuestionDetail;
import com.bigcay.exhubs.model.QuestionHeader;
import com.bigcay.exhubs.model.QuestionSubject;
import com.bigcay.exhubs.model.QuestionType;
import com.bigcay.exhubs.service.AuthorityService;
import com.bigcay.exhubs.service.QuestionService;

@Controller
public class DemoController {

	private static final Logger logger = LoggerFactory.getLogger(DemoController.class);

	@Autowired
	private AuthorityService authorityService;

	@Autowired
	private QuestionService questionService;

	@Autowired
	private UserFormBeanValidator userFormBeanValidator;

	@InitBinder("userFormBean")
	protected void initUserFormBeanBinder(WebDataBinder binder) {
		binder.setValidator(userFormBeanValidator);
	}

	@ModelAttribute("groupBeans")
	public List<GroupBean> getGroupBeans() {
		return authorityService.findAllGroupBeans();
	}

	@RequestMapping(value = "demo", method = RequestMethod.GET)
	public String indexHandler(Model model) {

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

			for (QuestionDetail questionDetail : questionHeaderItem.getQuestionDetails()) {
				logger.debug("**** questionDetail:" + questionDetail.getContent() + ", sort_order:"
						+ questionDetail.getSortOrder());
			}
		}

		model.addAttribute("userFormBean", new UserFormBean());

		return "demo/index";
	}

	@RequestMapping(value = "demo", method = RequestMethod.POST)
	public String demoSubmitHandler(Model model, Locale locale,
			@Valid @ModelAttribute("userFormBean") UserFormBean userFormBean, BindingResult result,
			final RedirectAttributes redirectAttributes) {

		logger.debug("DemoController.demoSubmitHandler is invoked.");

		if (result.hasErrors()) {
			return "demo/index";
		} else {
			redirectAttributes.addFlashAttribute("info", "success!");
			return "redirect:/";
		}
	}

}
