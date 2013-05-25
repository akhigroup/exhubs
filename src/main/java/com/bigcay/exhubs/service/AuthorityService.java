package com.bigcay.exhubs.service;

import java.util.List;

import com.bigcay.exhubs.model.Role;
import com.bigcay.exhubs.model.User;

public interface AuthorityService {

	User findUserById(Integer id);

	List<User> findAllUsers();

	Role findRoleById(Integer id);

	List<Role> findAllRoles();
}
