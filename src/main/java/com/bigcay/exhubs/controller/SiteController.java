package com.bigcay.exhubs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SiteController {

	private static final Logger logger = LoggerFactory.getLogger(SiteController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String indexHandler(Model model, @RequestParam(required = false) String info,
			@RequestParam(required = false) String error) {

		logger.debug("SiteController.indexHandler is invoked.");

		if (info != null) {
			model.addAttribute("info", info);
		}

		if (error != null) {
			model.addAttribute("error", error);
		}

		return "site/index";
	}

}
