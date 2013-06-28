package com.bigcay.exhubs.form;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.bigcay.exhubs.service.ExamService;

@Component
public class ExamTypeFormBeanValidator implements Validator {

	@Autowired
	private ExamService examService;

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(ExamTypeFormBean.class);
	}

	@Override
	public void validate(Object target, Errors errors) {

		// 1. Preparations
		ExamTypeFormBean examTypeFormBean = (ExamTypeFormBean) target;

		// 2. Bean validations
		ValidationUtils.rejectIfEmpty(errors, "name", "ExamTypeFormBean.name.NotEmpty");
		ValidationUtils.rejectIfEmpty(errors, "description", "ExamTypeFormBean.description.NotEmpty");

		// 3. Business validations
		// * Do not perform "exam type name already exist" validation when updating an existing record *
		if (examTypeFormBean.getId() == null) {
			if (examService.findExamTypeByName(examTypeFormBean.getName()) != null) {
				errors.rejectValue("name", "ExamTypeFormBean.name.AlreadyExist");
			}
		}

	}

}
