package com.bigcay.exhubs.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bigcay.exhubs.bean.UserBean;
import com.bigcay.exhubs.bean.UserBeansObj;
import com.bigcay.exhubs.model.User;
import com.bigcay.exhubs.service.AuthorityService;

@Controller
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private AuthorityService authorityService;

	@RequestMapping("api/users")
	public @ResponseBody UserBeansObj usersApiHandler() {
		UserBeansObj userBeansObj = new UserBeansObj();

		List<UserBean> userBeans = new ArrayList<UserBean>();
		List<User> users = authorityService.findAllUsers();

		for (User user : users) {
			UserBean userBean = new UserBean();
			userBean.setId(user.getId());
			userBean.setUserId(user.getUserId());
			userBean.setPassword(user.getPassword());
			userBean.setName(user.getName());
			userBean.setEmail(user.getEmail());

			userBeans.add(userBean);
		}

		userBeansObj.setUserBeans(userBeans);
		return userBeansObj;
	}
}
