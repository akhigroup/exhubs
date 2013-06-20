package com.bigcay.exhubs.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bigcay.exhubs.form.GroupFormBean;
import com.bigcay.exhubs.model.Group;
import com.bigcay.exhubs.model.Role;
import com.bigcay.exhubs.service.AuthorityService;

@Controller
public class GroupController extends BaseController {

	@Autowired
	MessageSource messageSource;

	private static final Logger logger = LoggerFactory.getLogger(GroupController.class);

	@Autowired
	private AuthorityService authorityService;

	@ModelAttribute("roles")
	public List<Role> getAllRoles() {
		return authorityService.findAllRoles();
	}

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

	@RequestMapping(value = "groups/create", method = RequestMethod.GET)
	public String addGroupGetHandler(Model model) {

		logger.debug("GroupController.addGroupGetHandler is invoked.");

		model.addAttribute("groupFormBean", new GroupFormBean());

		return "groups/add_group";
	}

	@RequestMapping(value = "groups/create", method = RequestMethod.POST)
	public String addGroupSubmitHandler(Model model, Locale locale,
			@Valid @ModelAttribute("groupFormBean") GroupFormBean groupFormBean, BindingResult result,
			final RedirectAttributes redirectAttributes) {

		logger.debug("GroupController.addGroupSubmitHandler is invoked.");

		if (result.hasErrors()) {
			return "groups/add_group";
		} else {
			Group group = new Group();

			group.setName(groupFormBean.getName());
			group.setDescription(groupFormBean.getDescription());
			group.setRoles(null);

			Group savedGroup = authorityService.persist(group);

			Set<Role> selectedRoles = new HashSet<Role>();

			for (Integer roleId : groupFormBean.getRoleIds()) {
				Role role = authorityService.findRoleById(roleId);
				selectedRoles.add(role);
			}

			savedGroup.setRoles(selectedRoles);
			authorityService.persist(savedGroup);

			redirectAttributes.addFlashAttribute("info", messageSource.getMessage("groups.info.add_group_success",
					new String[] { savedGroup.getName() }, locale));
			return "redirect:/groups";
		}
	}
}
