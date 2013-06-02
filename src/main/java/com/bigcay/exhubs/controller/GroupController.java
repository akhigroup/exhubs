package com.bigcay.exhubs.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bigcay.exhubs.bean.GroupBean;
import com.bigcay.exhubs.service.AuthorityService;

@Controller
public class GroupController {

	private static final Logger logger = LoggerFactory.getLogger(GroupController.class);

	@Autowired
	private AuthorityService authorityService;

	@RequestMapping("groups")
	public String groupsIndexHandler() {

		logger.debug("GroupController.groupsIndexHandler is invoked.");

		return "groups/index";
	}

	@RequestMapping("ajax/groups/show_groups")
	public String showGroupsAjaxHandler(Model model) {

		logger.debug("GroupController.showGroupsAjaxHandler is invoked.");

		List<GroupBean> groupBeans = authorityService.findAllGroupBeans();

		model.addAttribute("groupBeans", groupBeans);

		return "ajax/groups/show_groups";
	}
}
