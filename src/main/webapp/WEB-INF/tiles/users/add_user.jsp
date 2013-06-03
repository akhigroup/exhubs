<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<form:form id="form" class="form-horizontal" method="post"
	modelAttribute="userFormBean">
	<fieldset>
		<legend>
			<s:message code="users.info.add_user" />
		</legend>

		<div class="control-group">
			<label class="control-label" for="userid"><s:message
					code="users.info.user_userid" /></label>
			<div class="controls">
				<form:input class="input-xlarge" path="userId" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="name"><s:message
					code="users.info.user_name" /></label>
			<div class="controls">
				<input type="text" class="input-xlarge" name="name" id="name"
					value="">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="password"><s:message
					code="users.info.user_password" /></label>
			<div class="controls">
				<input type="password" class="input-xlarge" name="password"
					id="password">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="email"><s:message
					code="users.info.user_email" /></label>
			<div class="controls">
				<input type="text" class="input-xlarge" name="email" id="email"
					value="">
			</div>
		</div>
		<div class="form-actions">
			<button type="submit" class="btn btn-primary">
				<s:message code="users.info.btn.add_user" />
			</button>
		</div>
	</fieldset>
</form:form>