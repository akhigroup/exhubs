package com.bigcay.exhubs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bigcay.exhubs.model.User;
import com.bigcay.exhubs.repository.UserRepository;
import com.bigcay.exhubs.service.AuthorityService;

@Service
public class AuthorityServiceImpl implements AuthorityService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User findUserById(Long id) {
		return userRepository.findOne(id);
	}

	@Override
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}

}
