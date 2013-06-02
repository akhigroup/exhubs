package com.bigcay.exhubs.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bigcay.exhubs.bean.GroupBean;
import com.bigcay.exhubs.bean.UserBean;
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
	public List<Role> findAllRoles() {
		return roleRepository.findAll();
	}

	@Override
	public List<UserBean> findAllUserBeans() {

		List<UserBean> userBeans = new ArrayList<UserBean>();
		List<User> users = userRepository.findAll();

		for (User user : users) {
			UserBean userBean = new UserBean();
			userBean.setId(user.getId());
			userBean.setUserId(user.getUserId());
			userBean.setName(user.getName());
			userBean.setEmail(user.getEmail());

			userBeans.add(userBean);
		}

		return userBeans;
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

}
