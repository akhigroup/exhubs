package com.bigcay.exhubs.controller;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bigcay.exhubs.bean.UserBean;
import com.bigcay.exhubs.form.UserFormBean;
import com.bigcay.exhubs.model.User;
import com.bigcay.exhubs.service.AuthorityService;

@Controller
public class UserController {

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
	public String showUsersAjaxHandler(Model model) {

		logger.debug("UserController.showUsersAjaxHandler is invoked.");

		List<UserBean> userBeans = authorityService.findAllUserBeans();
		model.addAttribute("userBeans", userBeans);

		return "ajax/users/show_users";
	}

	@RequestMapping(value = "users/create", method = RequestMethod.GET)
	public String addUserGetHandler(Model model) {

		logger.debug("UserController.addUserGetHandler is invoked.");

		model.addAttribute("userFormBean", new UserFormBean());

		return "users/add_user";
	}

	@RequestMapping(value = "users/create", method = RequestMethod.POST)
	public String addUserSubmitHandler(Locale locale, @Valid @ModelAttribute("userFormBean") UserFormBean userFormBean,
			BindingResult result, final RedirectAttributes redirectAttributes) {

		logger.debug("UserController.addUserSubmitHandler is invoked.");

		if (result.hasErrors()) {
			return "users/add_user";
		} else {
			User user = new User();
			user.setUserId(userFormBean.getUserId());
			user.setName(userFormBean.getName());
			user.setPassword(userFormBean.getPassword());
			user.setEmail(userFormBean.getEmail());
			user.setGroup(authorityService.findGroupById(2)); // TO-DO

			authorityService.persist(user);

			redirectAttributes.addFlashAttribute("info", messageSource.getMessage("users.info.add_user_success", new String[] { user.getUserId() }, locale));
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
