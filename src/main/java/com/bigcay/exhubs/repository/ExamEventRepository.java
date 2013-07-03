package com.bigcay.exhubs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bigcay.exhubs.model.ExamEvent;

public interface ExamEventRepository extends JpaRepository<ExamEvent, Integer> {

}
