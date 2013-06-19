package com.bigcay.exhubs.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.bigcay.exhubs.form.QuestionDetailBean;
import com.bigcay.exhubs.form.QuestionHeaderBean;
import com.bigcay.exhubs.form.QuestionSubjectFormBean;
import com.bigcay.exhubs.form.UserFormBean;
import com.bigcay.exhubs.form.UserFormBeanValidator;
import com.bigcay.exhubs.model.Group;
import com.bigcay.exhubs.model.QuestionAnswer;
import com.bigcay.exhubs.model.QuestionDetail;
import com.bigcay.exhubs.model.QuestionHeader;
import com.bigcay.exhubs.model.QuestionSubject;
import com.bigcay.exhubs.model.QuestionType;
import com.bigcay.exhubs.model.Role;
import com.bigcay.exhubs.model.User;
import com.bigcay.exhubs.service.AuthorityService;
import com.bigcay.exhubs.service.QuestionService;

@Controller
public class DemoController extends BaseController {

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

	@ModelAttribute("groups")
	public List<Group> getAllGroups() {
		return authorityService.findAllGroups();
	}

	@ModelAttribute("questionTypes")
	public List<QuestionType> getAllQuestionTypes() {
		return questionService.findAllQuestionTypes();
	}

	@RequestMapping(value = "demo", method = RequestMethod.GET)
	public String indexHandler(Model model) {
		return "demo/index";
	}

	@RequestMapping(value = "demo/save_group", method = RequestMethod.GET)
	public String cascadeSaveGroupHandler(Model model) {

		Group newGroup = new Group();
		newGroup.setName("Group1-name");
		newGroup.setDescription("Group1-desc");

		Set<Role> newRoles = new HashSet<Role>();
		Role newRole = new Role();
		newRole.setName("Role1-name");
		newRole.setDescription("Role1-desc");
		newRoles.add(newRole);

		newGroup.setRoles(newRoles);

		Group savedGroup = authorityService.persist(newGroup);

		if (savedGroup != null) {
			logger.debug("** group was saved successfully!");
		} else {
			logger.debug("** failed to save group ...");
		}

		return "demo/index";
	}

	@RequestMapping(value = "demo/create_user", method = RequestMethod.GET)
	public String createUserHandler(Model model) {

		logger.info("DemoController.createUserHandler is invoked.");

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

		return "demo/create_user";
	}

	@RequestMapping(value = "demo/create_user", method = RequestMethod.POST)
	public String createUserSubmitHandler(Model model, Locale locale,
			@Valid @ModelAttribute("userFormBean") UserFormBean userFormBean, BindingResult result,
			final RedirectAttributes redirectAttributes) {

		logger.debug("DemoController.createUserSubmitHandler is invoked.");

		if (result.hasErrors()) {
			return "demo/create_user";
		} else {
			redirectAttributes.addFlashAttribute("info", "success!");
			return "redirect:/";
		}
	}

	@RequestMapping(value = "demo/create_question", method = RequestMethod.GET)
	public String createQuestionHandler(Model model) {

		logger.info("DemoController.createQuestionHandler is invoked.");

		List<QuestionHeaderBean> questionHeaderBeans = new ArrayList<QuestionHeaderBean>();
		QuestionSubjectFormBean questionSubjectFormBean = new QuestionSubjectFormBean();
		QuestionHeaderBean questionHeaderBean = new QuestionHeaderBean();
		questionHeaderBean.setQuestionDetailBeans(new ArrayList<QuestionDetailBean>());
		questionHeaderBeans.add(questionHeaderBean);
		questionSubjectFormBean.setQuestionHeaderBeans(questionHeaderBeans);

		model.addAttribute("questionSubjectFormBean", questionSubjectFormBean);

		return "demo/create_question";
	}

	@RequestMapping(value = "demo/create_question", method = RequestMethod.POST)
	public String createQuestionSubmitHandler(Model model, Locale locale,
			@Valid @ModelAttribute("questionSubjectFormBean") QuestionSubjectFormBean questionSubjectFormBean,
			BindingResult result, final RedirectAttributes redirectAttributes, Principal principal) {
		if (result.hasErrors()) {

			logger.debug("ERROR! TO-DO");

			return "demo/create_question";
		} else {
			// debug
			/*
			 * logger.debug("* id:" + questionSubjectFormBean.getId());
			 * logger.debug("* questionTypeId:" +
			 * questionSubjectFormBean.getQuestionTypeId());
			 * logger.debug("* content:" +
			 * questionSubjectFormBean.getContent());
			 * logger.debug("* totalScore:" +
			 * questionSubjectFormBean.getTotalScore());
			 * logger.debug("*-> radioSelectedIndex:" +
			 * questionSubjectFormBean.getRadioSelectedIndex());
			 * 
			 * List<QuestionHeaderBean> questionHeaderBeans =
			 * questionSubjectFormBean.getQuestionHeaderBeans();
			 * 
			 * for (QuestionHeaderBean questionHeaderBean : questionHeaderBeans)
			 * { logger.debug("** questionHeaderBean.description:" +
			 * questionHeaderBean.getDescription());
			 * logger.debug("** questionHeaderBean.score:" +
			 * questionHeaderBean.getScore());
			 * 
			 * if (questionHeaderBean != null) {
			 * logger.debug("good news - questionHeaderBean is not null");
			 * 
			 * List<QuestionDetailBean> questionDetailBeans =
			 * questionHeaderBean.getQuestionDetailBeans(); for
			 * (QuestionDetailBean questionDetailBean : questionDetailBeans) {
			 * logger.debug("### questionDetailBean:" +
			 * questionDetailBean.getId() + "," +
			 * questionDetailBean.getContent() + " - " +
			 * questionDetailBean.getIsChecked()); }
			 * 
			 * if (questionSubjectFormBean.getRadioSelectedIndex() != null) {
			 * QuestionDetailBean selectedQuestionDetailBean =
			 * questionDetailBeans.get(questionSubjectFormBean
			 * .getRadioSelectedIndex());
			 * logger.debug("**** selected answer is: " +
			 * selectedQuestionDetailBean.getContent()); } } }
			 */

			/* authorityService.findUserByUserId(principal.getName()); */
			User editUser = authorityService.findUserById(1);

			QuestionType questionType = questionService.findQuestionTypeById(questionSubjectFormBean
					.getQuestionTypeId());

			QuestionSubject questionSubject = new QuestionSubject();
			questionSubject.setContent(questionSubjectFormBean.getContent());
			questionSubject.setQuestionType(questionType);
			questionSubject.setTotalScore(questionSubjectFormBean.getTotalScore());
			questionSubject.setUser(editUser);
			
			questionSubject = questionService.persist(questionSubject);

			if ("SCQ".equalsIgnoreCase(questionType.getName())) {
				List<QuestionHeaderBean> questionHeaderBeans = questionSubjectFormBean.getQuestionHeaderBeans();
				Set<QuestionHeader> questionHeaders = new HashSet<QuestionHeader>();

				for (QuestionHeaderBean questionHeaderBean : questionHeaderBeans) {

					QuestionHeader questionHeader = new QuestionHeader();

					QuestionAnswer questionAnswer = new QuestionAnswer();
					questionAnswer.setBinaryValue(1);

					List<QuestionDetailBean> questionDetailBeans = questionHeaderBean.getQuestionDetailBeans();

					Set<QuestionDetail> questionDetails = new HashSet<QuestionDetail>();

					int questionDetailIndex = 1;
					for (QuestionDetailBean questionDetailBean : questionDetailBeans) {
						QuestionDetail questionDetail = new QuestionDetail();
						questionDetail.setContent(questionDetailBean.getContent());
						questionDetail.setSortOrder(questionDetailIndex);
						questionDetails.add(questionDetail);

						questionDetailIndex++;
					}

					questionHeader.setQuestionDetails(questionDetails);
					questionHeader.setQuestionAnswer(questionAnswer);
					questionHeader.setQuestionSubject(questionSubject);
					questionHeader.setQuestionType(questionType);
					questionHeader.setScore(questionHeaderBean.getScore());

					questionHeaders.add(questionHeader);
					
					questionSubject.setQuestionHeaders(questionHeaders);
				}

			} else if ("MCQ".equalsIgnoreCase(questionType.getName())) {

			}
			
			//save here
			questionService.persist(questionSubject);

			redirectAttributes.addFlashAttribute("info", "success!");
			return "redirect:/questionrepos";
		}
	}

	@RequestMapping("ajax/demo/show_sub_question_area")
	public String showSubQuestionAreaAjaxHandler(Model model, @RequestParam(required = true) Integer questionTypeId) {

		logger.debug("GroupController.showGroupRolesAjaxHandler is invoked.");

		QuestionType questionType = questionService.findQuestionTypeById(questionTypeId);

		if ("SCQ".equalsIgnoreCase(questionType.getName())) {
			model.addAttribute("selectedQuestionTypeName", "SCQ");
			return "ajax/demo/show_SCQ_area";
		} else if ("MCQ".equalsIgnoreCase(questionType.getName())) {
			model.addAttribute("selectedQuestionTypeName", "MCQ");
			return "ajax/demo/show_MCQ_area";
		} else {
			// TO-DO, handle error
			return "ajax/demo/show_SCQ_area";
		}
	}

	@RequestMapping(value = "demo/export/groups", method = RequestMethod.GET)
	public View exportGroups2ExcelHandler(Model model) {

		View view = new AbstractExcelView() {

			@Override
			protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook,
					HttpServletRequest request, HttpServletResponse response) throws Exception {

				response.setHeader("Content-Disposition", "attachment; filename=\"groups.xls\"");
				HSSFSheet sheet = workbook.createSheet("groups");

				int rowNum = 0;
				int idx = 0;

				HSSFRow header = sheet.createRow(rowNum++);
				header.createCell(idx++).setCellValue("id");
				header.createCell(idx++).setCellValue("name");
				header.createCell(idx++).setCellValue("description");

				List<Group> groups = authorityService.findAllGroups();
				HSSFRow row;

				for (Group group : groups) {
					idx = 0;
					row = sheet.createRow(rowNum++);
					row.createCell(idx++).setCellValue(group.getId());
					row.createCell(idx++).setCellValue(group.getName());
					row.createCell(idx++).setCellValue(group.getDescription());
				}
			}
		};

		return view;
	}
}
