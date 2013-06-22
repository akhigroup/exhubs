package com.bigcay.exhubs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bigcay.exhubs.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUserId(String userId);
	
	List<User> findByGroup_Id(Integer groupId);
	
}
