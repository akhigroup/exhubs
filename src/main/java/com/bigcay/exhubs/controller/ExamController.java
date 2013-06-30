package com.bigcay.exhubs.controller;

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
import com.bigcay.exhubs.form.ExamTypeFormBean;
import com.bigcay.exhubs.form.ExamTypeFormBeanValidator;
import com.bigcay.exhubs.model.ExamPaper;
import com.bigcay.exhubs.model.ExamType;
import com.bigcay.exhubs.service.ExamService;

@Controller
public class ExamController extends BaseController {

	@Autowired
	MessageSource messageSource;

	private static final Logger logger = LoggerFactory.getLogger(ExamController.class);

	@Autowired
	private ExamService examService;

	@Autowired
	private ExamTypeFormBeanValidator examTypeFormBeanValidator;

	@InitBinder("examTypeFormBean")
	protected void initExamTypeFormBeanBinder(WebDataBinder binder) {
		binder.setValidator(examTypeFormBeanValidator);
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

	@RequestMapping(value = "examtypes/edit/{editId}", method = RequestMethod.GET)
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

	@RequestMapping(value = "examtypes/edit/{editId}", method = RequestMethod.POST)
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

}
