<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<form:form id="form" class="form-horizontal" method="post"
	modelAttribute="questionSubjectFormBean">
	<fieldset>
		<legend> demo - create question </legend>

		<input type="hidden" name="id" value="${questionSubjectFormBean.id}" />

		<s:bind path="questionTypeId">
			<div class="control-group ${status.error ? 'error' : '' }">
				<label class="control-label" for="questionTypeId">Question
					Type</label>
				<div class="controls">
					<form:select id="questionTypeSelector" path="questionTypeId"
						onchange="questionTypeChanged();" tabindex="1"
						autofocus="autofocus">
						<form:option value="0" label="-- Please select one --" />
						<form:options items="${questionTypes}" itemValue="id"
							itemLabel="description" />
					</form:select>
					<c:if test="${status.error}">
						<span class="help-inline">${status.errorMessage}</span>
					</c:if>
				</div>
			</div>
		</s:bind>

		<s:bind path="content">
			<div class="control-group ${status.error ? 'error' : '' }">
				<label class="control-label" for="content">content</label>
				<div class="controls">
					<form:textarea rows="4" class="input-xlarge" path="content"
						tabindex="2" />
					<c:if test="${status.error}">
						<span class="help-inline">${status.errorMessage}</span>
					</c:if>
				</div>
			</div>
		</s:bind>

		<s:bind path="totalScore">
			<div class="control-group ${status.error ? 'error' : '' }">
				<label class="control-label" for="totalScore">Total Score</label>
				<div class="controls">
					<form:input class="input-mini" path="totalScore" tabindex="3" />
					<c:if test="${status.error}">
						<span class="help-inline">${status.errorMessage}</span>
					</c:if>
					<button type="button" class="btn btn-small">Add Sub
						Question</button>
				</div>
			</div>
		</s:bind>

		<div id="sub_question_container" class="control-group">
			<div>
				<label class="control-label">xxx Score</label>
				<div class="controls">
					<input type="text" class="input-large" />
				</div>
			</div>
		</div>

		<div class="form-actions">
			<button type="submit" class="btn btn-primary" tabindex="99">
				Create Question</button>
		</div>
	</fieldset>
</form:form>

<script type="text/javascript" charset="utf-8">
	function questionTypeChanged() {
		var questionTypeId = $('#questionTypeSelector').val();
		alert(questionTypeId);
		
		
		
	};
</script>
