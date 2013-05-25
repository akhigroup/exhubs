package com.bigcay.exhubs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bigcay.exhubs.model.Role;
import com.bigcay.exhubs.model.User;
import com.bigcay.exhubs.repository.RoleRepository;
import com.bigcay.exhubs.repository.UserRepository;
import com.bigcay.exhubs.service.AuthorityService;

@Service
public class AuthorityServiceImpl implements AuthorityService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public User findUserById(Integer id) {
		return userRepository.findOne(id);
	}

	@Override
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public Role findRoleById(Integer id) {
		return roleRepository.findOne(id);
	}

	@Override
	public List<Role> findAllRoles() {
		return roleRepository.findAll();
	}

}
