package com.bigcay.exhubs.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.bigcay.exhubs.bean.GroupBean;
import com.bigcay.exhubs.bean.RoleBean;
import com.bigcay.exhubs.bean.UserBean;
import com.bigcay.exhubs.model.Group;
import com.bigcay.exhubs.model.Role;
import com.bigcay.exhubs.model.User;

public interface AuthorityService {

	User findUserById(Integer id);
	
	User findUserByUserId(String userId);

	List<UserBean> findAllUserBeans();

	Page<User> findPageableUsers(Integer pageNumber);

	List<UserBean> convertUsers(List<User> users);

	Role findRoleById(Integer id);

	List<Role> findAllRoles();

	Group findGroupById(Integer id);

	List<GroupBean> findAllGroupBeans();

	List<RoleBean> findRoleBeansByGroupId(Integer groupId);

	User persist(User user);

	boolean updateUserActiveFlag(Integer updateId, boolean activeFlag);
}
