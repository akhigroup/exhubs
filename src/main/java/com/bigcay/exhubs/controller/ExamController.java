package com.bigcay.exhubs.controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bigcay.exhubs.common.GlobalManager;
import com.bigcay.exhubs.common.ResponseResult;
import com.bigcay.exhubs.common.ResultType;
import com.bigcay.exhubs.common.ValidationResult;
import com.bigcay.exhubs.converter.DefaultDateEditor;
import com.bigcay.exhubs.form.ExamEventFormBean;
import com.bigcay.exhubs.form.ExamEventFormBeanValidator;
import com.bigcay.exhubs.form.ExamPaperFormBean;
import com.bigcay.exhubs.form.ExamPaperFormBeanValidator;
import com.bigcay.exhubs.form.ExamTypeFormBean;
import com.bigcay.exhubs.form.ExamTypeFormBeanValidator;
import com.bigcay.exhubs.model.ExamEvent;
import com.bigcay.exhubs.model.ExamPaper;
import com.bigcay.exhubs.model.ExamType;
import com.bigcay.exhubs.model.QuestionSubject;
import com.bigcay.exhubs.model.User;
import com.bigcay.exhubs.service.AuthorityService;
import com.bigcay.exhubs.service.ExamService;
import com.bigcay.exhubs.service.QuestionService;

@Controller
public class ExamController extends BaseController {

	@Autowired
	MessageSource messageSource;

	private static final Logger logger = LoggerFactory.getLogger(ExamController.class);

	@Autowired
	private ExamService examService;

	@Autowired
	private AuthorityService authorityService;
	
	@Autowired
	private QuestionService questionService;

	@Autowired
	private ExamTypeFormBeanValidator examTypeFormBeanValidator;

	@Autowired
	private ExamPaperFormBeanValidator examPaperFormBeanValidator;
	
	@Autowired
	private ExamEventFormBeanValidator examEventFormBeanValidator;

	@InitBinder("examTypeFormBean")
	protected void initExamTypeFormBeanBinder(WebDataBinder binder) {
		binder.setValidator(examTypeFormBeanValidator);
	}

	@InitBinder("examPaperFormBean")
	protected void initExamPaperFormBeanBinder(WebDataBinder binder) {
		binder.setValidator(examPaperFormBeanValidator);
	}
	
	@InitBinder("examEventFormBean")
	protected void initExamEventFormBeanBinder(WebDataBinder binder) {
		binder.setValidator(examEventFormBeanValidator);
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new DefaultDateEditor());
	}

	@ModelAttribute("examTypes")
	public List<ExamType> getAllExamTypes() {
		return examService.findAllExamTypes();
	}
	
	@ModelAttribute("examPapers")
	public List<ExamPaper> getAllExamPapers() {
		return examService.findAllExamPapers();
	}

	@RequestMapping("examtypes")
	public String examTypesIndexHandler() {

		logger.debug("ExamController.examTypesIndexHandler is invoked.");

		return "examtypes/index";
	}

	@RequestMapping("ajax/examtypes/show_exam_types")
	public String showExamTypesAjaxHandler(Model model, @RequestParam("pageNumber") Integer pageNumber) {

		logger.debug("ExamController.showExamTypesAjaxHandler is invoked.");

		Page<ExamType> examTypePage = examService.findPageableExamTypes(pageNumber - 1);
		List<ExamType> examTypes = examTypePage.getContent();

		model.addAttribute("examTypes", examTypes);
		// add pagination attributes
		model.addAttribute("showRecordsJSFunc", "showExamTypes");
		model.addAllAttributes(GlobalManager.getGlobalPageableMap(examTypePage));

		return "ajax/examtypes/show_exam_types";
	}

	@RequestMapping(value = "examtypes/create", method = RequestMethod.GET)
	public String addExamTypeGetHandler(Model model) {

		logger.debug("ExamController.addExamTypeGetHandler is invoked.");

		model.addAttribute("examTypeFormBean", new ExamTypeFormBean());

		return "examtypes/add_exam_type";
	}

	@RequestMapping(value = "examtypes/create", method = RequestMethod.POST)
	public String addExamTypeSubmitHandler(Model model, Locale locale,
			@Valid @ModelAttribute("examTypeFormBean") ExamTypeFormBean examTypeFormBean, BindingResult result,
			final RedirectAttributes redirectAttributes) {

		logger.debug("ExamController.addExamTypeSubmitHandler is invoked.");

		if (result.hasErrors()) {
			return "examtypes/add_exam_type";
		} else {
			ExamType examType = new ExamType();
			examType.setName(examTypeFormBean.getName());
			examType.setDescription(examTypeFormBean.getDescription());

			examType = examService.persist(examType);

			redirectAttributes.addFlashAttribute("info", messageSource.getMessage(
					"examtypes.info.add_exam_type_success", new String[] { examType.getName() }, locale));
			return "redirect:/examtypes";
		}
	}

	@RequestMapping(value = "examtype/edit/{editId}", method = RequestMethod.GET)
	public String editExamTypeGetHandler(Model model, @PathVariable Integer editId) {

		logger.debug("ExamController.editExamTypeGetHandler is invoked.");

		ExamType editExamType = examService.findExamTypeById(editId);

		ExamTypeFormBean examTypeFormBean = new ExamTypeFormBean();
		examTypeFormBean.setId(editExamType.getId());
		examTypeFormBean.setName(editExamType.getName());
		examTypeFormBean.setDescription(editExamType.getDescription());

		model.addAttribute("examTypeFormBean", examTypeFormBean);

		return "examtypes/edit_exam_type";
	}

	@RequestMapping(value = "examtype/edit/{editId}", method = RequestMethod.POST)
	public String editExamTypeSubmitHandler(Model model, Locale locale, @PathVariable Integer editId,
			@Valid @ModelAttribute("examTypeFormBean") ExamTypeFormBean examTypeFormBean, BindingResult result,
			final RedirectAttributes redirectAttributes) {

		logger.debug("ExamController.editExamTypeSubmitHandler is invoked.");

		if (result.hasErrors()) {
			return "examtypes/edit_exam_type";
		} else {
			ExamType examType = examService.findExamTypeById(editId);
			examType.setName(examTypeFormBean.getName());
			examType.setDescription(examTypeFormBean.getDescription());

			examService.persist(examType);

			redirectAttributes.addFlashAttribute("info", messageSource.getMessage(
					"examtypes.info.edit_exam_type_success", new String[] { examType.getName() }, locale));
			return "redirect:/examtypes";
		}
	}

	@RequestMapping("exampapers")
	public String examPapersIndexHandler() {

		logger.debug("ExamController.examPapersIndexHandler is invoked.");

		return "exampapers/index";
	}

	@RequestMapping("ajax/exampapers/show_exam_papers")
	public String showExamPapersAjaxHandler(Model model, @RequestParam("pageNumber") Integer pageNumber) {

		logger.debug("ExamController.showExamPapersAjaxHandler is invoked.");

		Page<ExamPaper> examPaperPage = examService.findPageableExamPapers(pageNumber - 1);
		List<ExamPaper> examPapers = examPaperPage.getContent();

		model.addAttribute("examPapers", examPapers);
		// add pagination attributes
		model.addAttribute("showRecordsJSFunc", "showExamPapers");
		model.addAllAttributes(GlobalManager.getGlobalPageableMap(examPaperPage));

		return "ajax/exampapers/show_exam_papers";
	}

	@RequestMapping(value = "exampapers/create", method = RequestMethod.GET)
	public String addExamPaperGetHandler(Model model) {

		logger.debug("ExamController.addExamPaperGetHandler is invoked.");

		model.addAttribute("examPaperFormBean", new ExamPaperFormBean());

		return "exampapers/add_exam_paper";
	}

	@RequestMapping(value = "exampapers/create", method = RequestMethod.POST)
	public String addExamPaperSubmitHandler(Model model, Locale locale,
			@Valid @ModelAttribute("examPaperFormBean") ExamPaperFormBean examPaperFormBean, BindingResult result,
			final RedirectAttributes redirectAttributes, Principal principal) {

		logger.debug("ExamController.addExamPaperSubmitHandler is invoked.");

		if (result.hasErrors()) {
			return "exampapers/add_exam_paper";
		} else {
			/* authorityService.findUserByUserId(principal.getName()); */
			User editUser = authorityService.findUserById(1);

			ExamPaper examPaper = new ExamPaper();
			examPaper.setName(examPaperFormBean.getName());
			examPaper.setDescription(examPaperFormBean.getDescription());
			examPaper.setCreateDate(new Date());
			examPaper.setActiveFlag(true);
			examPaper.setUser(editUser);
			examPaper.setExamType(examService.findExamTypeById(examPaperFormBean.getExamTypeId()));

			examPaper = examService.persist(examPaper);

			redirectAttributes.addFlashAttribute(
					"info",
					messageSource.getMessage("exampapers.info.add_exam_paper_success",
							new String[] { examPaper.getName() }, locale));
			return "redirect:/exampapers";
		}
	}

	@RequestMapping(value = "exam_paper/edit/{editId}", method = RequestMethod.GET)
	public String editExamPaperGetHandler(Model model, @PathVariable Integer editId) {

		logger.debug("ExamController.editExamPaperGetHandler is invoked.");

		ExamPaper editExamPaper = examService.findExamPaperById(editId);

		ExamPaperFormBean examPaperFormBean = new ExamPaperFormBean();
		examPaperFormBean.setId(editExamPaper.getId());
		examPaperFormBean.setName(editExamPaper.getName());
		examPaperFormBean.setDescription(editExamPaper.getDescription());
		examPaperFormBean.setExamTypeId(editExamPaper.getExamType().getId());

		model.addAttribute("examPaperFormBean", examPaperFormBean);

		return "exampapers/edit_exam_paper";
	}

	@RequestMapping(value = "exam_paper/edit/{editId}", method = RequestMethod.POST)
	public String editExamPageSubmitHandler(Model model, Locale locale, @PathVariable Integer editId,
			@Valid @ModelAttribute("examPaperFormBean") ExamPaperFormBean examPaperFormBean, BindingResult result,
			final RedirectAttributes redirectAttributes) {

		logger.debug("ExamController.editExamPageSubmitHandler is invoked.");

		if (result.hasErrors()) {
			return "exampapers/edit_exam_paper";
		} else {
			ExamPaper examPaper = examService.findExamPaperById(editId);
			examPaper.setName(examPaperFormBean.getName());
			examPaper.setDescription(examPaperFormBean.getDescription());
			examPaper.setExamType(examService.findExamTypeById(examPaperFormBean.getExamTypeId()));

			examService.persist(examPaper);

			redirectAttributes.addFlashAttribute(
					"info",
					messageSource.getMessage("exampapers.info.edit_exam_paper_success",
							new String[] { examPaper.getName() }, locale));
			return "redirect:/exampapers";
		}
	}
	
	@RequestMapping("exam_paper/{examPaperId}")
	public String selectExamPaperHandler(Model model, @PathVariable Integer examPaperId) {

		logger.debug("ExamController.selectExamPaperHandler is invoked.");

		model.addAttribute("examPaperId", examPaperId);

		return "exampapers/select_exam_paper";
	}
	
	@RequestMapping("ajax/exampapers/show_associated_question_subjects")
	public String showAssociatedQuestionSubjectsAjaxHandler(Model model,
			@RequestParam("examPaperId") Integer examPaperId) {

		logger.debug("ExamController.showAssociatedQuestionSubjectsAjaxHandler is invoked.");

		List<QuestionSubject> associatedQuestionSubjects = examService.findQuestionSubjectsByExamPaperId(examPaperId);

		model.addAttribute("associatedQuestionSubjects", associatedQuestionSubjects);

		return "ajax/exampapers/show_associated_question_subjects";
	}
	
	@RequestMapping("ajax/exampapers/show_potential_question_subjects")
	public String showPotentialQuestionSubjectsAjaxHandler(Model model,
			@RequestParam("pageNumber") Integer pageNumber) {

		logger.debug("ExamController.showPotentialQuestionSubjectsAjaxHandler is invoked.");

		// TO-DO, Add filter to get potential question subjects
		Page<QuestionSubject> potentialQuestionSubjectPage = questionService.findPageableQuestionSubjects(pageNumber - 1);
		List<QuestionSubject> potentialQuestionSubjects = potentialQuestionSubjectPage.getContent();

		model.addAttribute("potentialQuestionSubjects", potentialQuestionSubjects);
		// add pagination attributes
		model.addAttribute("showRecordsJSFunc", "showPotentialQuestionSubjects");
		model.addAllAttributes(GlobalManager.getGlobalPageableMap(potentialQuestionSubjectPage));

		return "ajax/exampapers/show_potential_question_subjects";
	}
	
	@RequestMapping("examevents")
	public String examEventsIndexHandler() {

		logger.debug("ExamController.examEventsIndexHandler is invoked.");

		return "examevents/index";
	}
	
	@RequestMapping("ajax/examevents/show_exam_events")
	public String showExamEventsAjaxHandler(Model model, @RequestParam("pageNumber") Integer pageNumber) {

		logger.debug("ExamController.showExamEventsAjaxHandler is invoked.");

		Page<ExamEvent> examEventPage = examService.findPageableExamEvents(pageNumber - 1);
		List<ExamEvent> examEvents = examEventPage.getContent();

		model.addAttribute("examEvents", examEvents);
		// add pagination attributes
		model.addAttribute("showRecordsJSFunc", "showExamEvents");
		model.addAllAttributes(GlobalManager.getGlobalPageableMap(examEventPage));

		return "ajax/examevents/show_exam_events";
	}
	
	@RequestMapping(value = "examevents/create", method = RequestMethod.GET)
	public String addExamEventGetHandler(Model model) {

		logger.debug("ExamController.addExamEventGetHandler is invoked.");

		model.addAttribute("examEventFormBean", new ExamEventFormBean());

		return "examevents/add_exam_event";
	}
	
	
	@RequestMapping(value = "examevents/create", method = RequestMethod.POST)
	public String addExamEventeSubmitHandler(Model model, Locale locale,
			@Valid @ModelAttribute("examEventFormBean") ExamEventFormBean examEventFormBean, BindingResult result,
			final RedirectAttributes redirectAttributes, Principal principal) {

		logger.debug("ExamController.addExamEventeSubmitHandler is invoked.");

		if (result.hasErrors()) {
			return "examevents/add_exam_event";
		} else {
			/* authorityService.findUserByUserId(principal.getName()); */
			User editUser = authorityService.findUserById(1);

			ExamEvent examEvent = new ExamEvent();
			examEvent.setName(examEventFormBean.getName());
			examEvent.setDescription(examEventFormBean.getDescription());
			examEvent.setExamPaper(examService.findExamPaperById(examEventFormBean.getExamPaperId()));
			examEvent.setUser(editUser);
			examEvent.setStartDateTime(examEventFormBean.getStartDateTime()); // TO-DO
			examEvent.setEndDateTime(new Date()); // TO-DO
			examEvent.setDuration(examEventFormBean.getDuration());
			examEvent.setActiveFlag(true);

			examEvent = examService.persist(examEvent);

			redirectAttributes.addFlashAttribute(
					"info",
					messageSource.getMessage("examevents.info.add_exam_event_success",
							new String[] { examEvent.getName() }, locale));
			return "redirect:/examevents";
		}
	}
	

	@RequestMapping(value = "/rest/examtypes/delete_exam_type", method = RequestMethod.POST)
	public @ResponseBody
	ResponseResult deleteExamTypeRestHandler(Locale locale, @RequestParam("deleteId") Integer deleteId) {

		logger.debug("ExamController.deleteExamTypeRestHandler is invoked.");

		ValidationResult validationResult = examService.validateBeforeDeleteExamType(deleteId, locale);

		if (ResultType.SUCCESS == validationResult.getResultType()) {
			examService.deleteExamType(deleteId);
		}

		ResponseResult responseResult = new ResponseResult(validationResult);
		return responseResult;
	}
	
	@RequestMapping(value = "/rest/exampapers/delete_exam_paper", method = RequestMethod.POST)
	public @ResponseBody
	ResponseResult deleteExamPaperRestHandler(Locale locale, @RequestParam("deleteId") Integer deleteId) {

		logger.debug("ExamController.deleteExamPaperRestHandler is invoked.");

		ValidationResult validationResult = examService.validateBeforeDeleteExamPaper(deleteId, locale);

		if (ResultType.SUCCESS == validationResult.getResultType()) {
			examService.deleteExamPaper(deleteId);
		}

		ResponseResult responseResult = new ResponseResult(validationResult);
		return responseResult;
	}

}
