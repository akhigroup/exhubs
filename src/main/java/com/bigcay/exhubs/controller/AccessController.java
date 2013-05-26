package com.bigcay.exhubs.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccessController {

	@Autowired
	MessageSource messageSource;

	private static final Logger logger = LoggerFactory.getLogger(AccessController.class);

	@RequestMapping("/login")
	public String login(Model model, @RequestParam(required = false) String error) throws UnsupportedEncodingException {

		logger.debug("AccessController.login is invoked.");

		if (error != null) {
			model.addAttribute("error", error);
		}

		return "access/login";
	}

	@RequestMapping(value = "/login/{status}")
	public String loginFailure(Locale locale, @PathVariable String status) throws UnsupportedEncodingException {

		logger.debug("AccessController.loginFailure is invoked.");

		if ("success".equals(status)) {
			return "redirect:/?info="
					+ URLEncoder.encode(messageSource.getMessage("login.info.login_success", null, locale), "UTF-8");
		} else {
			return "redirect:/login?error="
					+ URLEncoder.encode(messageSource.getMessage("login.error.login_failure", null, locale), "UTF-8");
		}
	}

}
