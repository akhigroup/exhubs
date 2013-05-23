package com.bigcay.exhubs.service;

import java.util.List;

import com.bigcay.exhubs.model.User;

public interface AuthorityService {

	User findUserById(Long id);

	List<User> findAllUsers();
}
