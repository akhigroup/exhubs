package com.bigcay.exhubs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bigcay.exhubs.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
