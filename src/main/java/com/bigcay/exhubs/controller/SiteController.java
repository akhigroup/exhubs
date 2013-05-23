package com.bigcay.exhubs.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bigcay.exhubs.model.User;
import com.bigcay.exhubs.service.AuthorityService;

@Controller
public class SiteController {

	private static final Logger logger = LoggerFactory.getLogger(SiteController.class);

	@Autowired
	private AuthorityService authorityService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String indexHandler(Model model) {
		logger.debug("indexHandler is invokded.");

		List<User> users = authorityService.findAllUsers();

		for (User user : users) {
			logger.info(user.getName());
		}

		model.addAttribute("message", "Welcome to home page. ");
		return "site/index";
	}

}
