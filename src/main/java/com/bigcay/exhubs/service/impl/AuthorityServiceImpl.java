package com.bigcay.exhubs.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.bigcay.exhubs.bean.GroupBean;
import com.bigcay.exhubs.bean.RoleBean;
import com.bigcay.exhubs.bean.UserBean;
import com.bigcay.exhubs.global.GlobalManager;
import com.bigcay.exhubs.model.Group;
import com.bigcay.exhubs.model.Role;
import com.bigcay.exhubs.model.User;
import com.bigcay.exhubs.repository.GroupRepository;
import com.bigcay.exhubs.repository.RoleRepository;
import com.bigcay.exhubs.repository.UserRepository;
import com.bigcay.exhubs.service.AuthorityService;

@Service
public class AuthorityServiceImpl implements AuthorityService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private GroupRepository groupRepository;

	@Override
	public User findUserById(Integer id) {
		return userRepository.findOne(id);
	}

	@Override
	public Role findRoleById(Integer id) {
		return roleRepository.findOne(id);
	}

	@Override
	public Group findGroupById(Integer id) {
		return groupRepository.findOne(id);
	}

	@Override
	public List<Role> findAllRoles() {
		return roleRepository.findAll();
	}

	@Override
	public List<UserBean> findAllUserBeans() {
		return this.convertUsers(userRepository.findAll());
	}

	@Override
	public List<GroupBean> findAllGroupBeans() {

		List<GroupBean> groupBeans = new ArrayList<GroupBean>();
		List<Group> groups = groupRepository.findAll();

		for (Group group : groups) {
			GroupBean groupBean = new GroupBean();
			groupBean.setId(group.getId());
			groupBean.setName(group.getName());
			groupBean.setDescription(group.getDescription());

			groupBeans.add(groupBean);
		}

		return groupBeans;
	}

	@Override
	public List<RoleBean> findRoleBeansByGroupId(Integer groupId) {

		List<RoleBean> roleBeans = new ArrayList<RoleBean>();

		Group group = groupRepository.findOne(groupId);
		Set<Role> roles = group.getRoles();

		for (Role role : roles) {
			RoleBean roleBean = new RoleBean();
			roleBean.setId(role.getId());
			roleBean.setName(role.getName());
			roleBean.setDescription(role.getDescription());

			roleBeans.add(roleBean);
		}

		return roleBeans;
	}

	@Override
	public User persist(User user) {
		return userRepository.save(user);
	}

	@Override
	public Page<User> findPageableUsers(Integer pageNumber) {
		PageRequest pageRequest = new PageRequest(pageNumber, GlobalManager.DEFAULT_PAGE_SIZE);
		return userRepository.findAll(pageRequest);
	}

	@Override
	public List<UserBean> convertUsers(List<User> users) {

		List<UserBean> userBeans = new ArrayList<UserBean>();

		for (User user : users) {
			UserBean userBean = new UserBean();
			userBean.setId(user.getId());
			userBean.setUserId(user.getUserId());
			userBean.setName(user.getName());
			userBean.setEmail(user.getEmail());
			userBean.setGroup(user.getGroup());
			userBean.setActiveFlag(user.getActiveFlag());

			userBeans.add(userBean);
		}

		return userBeans;
	}

}
