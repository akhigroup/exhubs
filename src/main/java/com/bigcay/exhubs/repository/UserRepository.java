package com.bigcay.exhubs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.bigcay.exhubs.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUserId(String userId);

}
