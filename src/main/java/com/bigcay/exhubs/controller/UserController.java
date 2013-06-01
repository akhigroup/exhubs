package com.bigcay.exhubs.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bigcay.exhubs.bean.UserBean;
import com.bigcay.exhubs.service.AuthorityService;

@Controller
public class UserController {

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

	// not used yet
	@RequestMapping("rest/users")
	public @ResponseBody
	List<UserBean> usersRestHandler() {

		logger.debug("UserController.usersRestHandler is invoked.");

		return authorityService.findAllUserBeans();
	}
}
