package com.bigcay.exhubs.controller;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bigcay.exhubs.model.Group;
import com.bigcay.exhubs.model.Role;
import com.bigcay.exhubs.service.AuthorityService;

@Controller
public class GroupController extends BaseController {

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

		List<Group> groups = authorityService.findAllGroups();

		model.addAttribute("groups", groups);

		return "ajax/groups/show_groups";
	}

	@RequestMapping("group/{groupId}")
	public String selectGroupHandler(Model model, @PathVariable Integer groupId) {

		logger.debug("GroupController.selectGroupHandler is invoked.");

		model.addAttribute("groupId", groupId);

		return "groups/select_group";
	}

	@RequestMapping("ajax/groups/show_group_roles")
	public String showGroupRolesAjaxHandler(Model model, @RequestParam(required = false) Integer groupId) {

		logger.debug("GroupController.showGroupRolesAjaxHandler is invoked.");
		
		Set<Role> roles = authorityService.findRolesByGroupId(groupId);

		model.addAttribute("roles", roles);

		return "ajax/groups/show_group_roles";
	}
}
