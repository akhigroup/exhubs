package com.bigcay.exhubs.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bigcay.exhubs.bean.UserBean;
import com.bigcay.exhubs.service.AuthorityService;

@Controller
@RequestMapping("user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private AuthorityService authorityService;

	@RequestMapping("api/users")
	public @ResponseBody
	List<UserBean> usersApiHandler() {

		logger.debug("UserController.usersApiHandler is invoked.");

		return authorityService.findAllUserBeans();
	}
}
