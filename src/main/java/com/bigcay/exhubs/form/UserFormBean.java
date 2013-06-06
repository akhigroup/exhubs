package com.bigcay.exhubs.form;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;

public class UserFormBean {

	@NotEmpty
	private String userId;

	@NotEmpty
	private String name;

	@NotEmpty
	private String password;

	@NotEmpty
	private String email;
	
	@Min(1)
	private Integer groupId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

}
