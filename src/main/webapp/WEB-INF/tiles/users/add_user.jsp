<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<form:form id="form" class="form-horizontal" method="post"
	modelAttribute="userFormBean">
	<fieldset>
		<legend>
			<s:message code="users.info.add_user" />
		</legend>

		<s:bind path="userId">
			<div class="control-group ${status.error ? 'error' : '' }">
				<label class="control-label" for="userId"><s:message
						code="users.info.user_userid" /></label>
				<div class="controls">
					<form:input class="input-xlarge" path="userId" />
					<c:if test="${status.error}">
						<span class="help-inline">${status.errorMessage}</span>
					</c:if>
				</div>
			</div>
		</s:bind>
		<s:bind path="name">
			<div class="control-group ${status.error ? 'error' : '' }">
				<label class="control-label" for="name"><s:message
						code="users.info.user_name" /></label>
				<div class="controls">
					<form:input class="input-xlarge" path="name" />
					<c:if test="${status.error}">
						<span class="help-inline">${status.errorMessage}</span>
					</c:if>
				</div>
			</div>
		</s:bind>
		<s:bind path="password">
			<div class="control-group ${status.error ? 'error' : '' }">
				<label class="control-label" for="password"><s:message
						code="users.info.user_password" /></label>
				<div class="controls">
					<form:input type="password" class="input-xlarge" path="password" />
					<c:if test="${status.error}">
						<span class="help-inline">${status.errorMessage}</span>
					</c:if>
				</div>
			</div>
		</s:bind>
		<s:bind path="email">
			<div class="control-group ${status.error ? 'error' : '' }">
				<label class="control-label" for="email"><s:message
						code="users.info.user_email" /></label>
				<div class="controls">
					<form:input class="input-xlarge" path="email" />
					<c:if test="${status.error}">
						<span class="help-inline">${status.errorMessage}</span>
					</c:if>
				</div>
			</div>
		</s:bind>

		<s:bind path="groupId">
			<div class="control-group ${status.error ? 'error' : '' }">
				<label class="control-label" for="groupId"><s:message
						code="users.info.user_group" /></label>
				<div class="controls">
					<form:select path="groupId">
						<form:option value="0" label="--- Select ---" />
						<form:options items="${groupBeans}" itemValue="id" itemLabel="description" />
					</form:select>
					<c:if test="${status.error}">
						<span class="help-inline">${status.errorMessage}</span>
					</c:if>
				</div>
			</div>
		</s:bind>

		<div class="form-actions">
			<button type="submit" class="btn btn-primary">
				<s:message code="users.info.btn.add_user" />
			</button>
		</div>
	</fieldset>
</form:form>