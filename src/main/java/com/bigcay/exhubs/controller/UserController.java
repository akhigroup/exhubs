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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bigcay.exhubs.bean.GroupBean;
import com.bigcay.exhubs.bean.UserBean;
import com.bigcay.exhubs.form.UserFormBean;
import com.bigcay.exhubs.global.GlobalManager;
import com.bigcay.exhubs.model.User;
import com.bigcay.exhubs.service.AuthorityService;

@Controller
public class UserController extends BaseController {

	@Autowired
	MessageSource messageSource;

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private AuthorityService authorityService;

	@RequestMapping("users")
	public String usersIndexHandler() {

		logger.debug("UserController.usersIndexHandler is invoked.");

		return "users/index";
	}

	@RequestMapping("ajax/users/show_users")
	public String showUsersAjaxHandler(Model model, @RequestParam("pageNumber") Integer pageNumber) {

		logger.debug("UserController.showUsersAjaxHandler is invoked.");

		Page<User> userPage = authorityService.findPageableUsers(pageNumber - 1);
		List<UserBean> userBeans = authorityService.convertUsers(userPage.getContent());

		model.addAttribute("userBeans", userBeans);
		// add pagination attributes
		model.addAllAttributes(GlobalManager.getGlobalPageableMap(userPage));

		return "ajax/users/show_users";
	}

	@RequestMapping(value = "users/create", method = RequestMethod.GET)
	public String addUserGetHandler(Model model) {

		logger.debug("UserController.addUserGetHandler is invoked.");

		List<GroupBean> groupBeans = authorityService.findAllGroupBeans();
		model.addAttribute("userFormBean", new UserFormBean());
		model.addAttribute("groupBeans", groupBeans);

		return "users/add_user";
	}

	@RequestMapping(value = "users/create", method = RequestMethod.POST)
	public String addUserSubmitHandler(Model model, Locale locale,
			@Valid @ModelAttribute("userFormBean") UserFormBean userFormBean, BindingResult result,
			final RedirectAttributes redirectAttributes) {

		logger.debug("UserController.addUserSubmitHandler is invoked.");

		if (result.hasErrors()) {
			List<GroupBean> groupBeans = authorityService.findAllGroupBeans();
			model.addAttribute("groupBeans", groupBeans);

			return "users/add_user";
		} else {
			User user = new User();
			user.setUserId(userFormBean.getUserId());
			user.setName(userFormBean.getName());
			user.setPassword(userFormBean.getPassword());
			user.setEmail(userFormBean.getEmail());
			user.setGroup(authorityService.findGroupById(userFormBean.getGroupId()));
			user.setActiveFlag(true);

			authorityService.persist(user);

			redirectAttributes.addFlashAttribute("info",
					messageSource.getMessage("users.info.add_user_success", new String[] { user.getUserId() }, locale));
			return "redirect:/users";
		}

	}

	// not used yet
	@RequestMapping("rest/users")
	public @ResponseBody
	List<UserBean> usersRestHandler() {

		logger.debug("UserController.usersRestHandler is invoked.");

		return authorityService.findAllUserBeans();
	}
}
