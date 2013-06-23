package com.bigcay.exhubs.form;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class GroupFormBeanValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(GroupFormBean.class);
	}

	@Override
	public void validate(Object target, Errors errors) {

		// 1. Preparations
		GroupFormBean groupFormBean = (GroupFormBean) target;
		
		// 2. Bean validations
		ValidationUtils.rejectIfEmpty(errors, "name", "GroupFormBean.name.NotEmpty");
		ValidationUtils.rejectIfEmpty(errors, "description", "GroupFormBean.description.NotEmpty");
		
		if(groupFormBean.getRoleIds() == null || groupFormBean.getRoleIds().size() == 0) {
			errors.rejectValue("roleIds", "GroupFormBean.roleIds.IsRequired");
		}
		
	}

}
