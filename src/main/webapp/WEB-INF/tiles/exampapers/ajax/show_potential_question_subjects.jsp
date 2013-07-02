<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<table class="table table-hover">
	<thead>
		<tr>
			<th><s:message code="exampapers.info.question_subjects_id" /></th>
			<th><s:message
					code="exampapers.info.question_subjects_question_type" /></th>
			<th><s:message code="exampapers.info.question_subjects_content" /></th>
			<th><s:message
					code="exampapers.info.question_subjects_total_score" /></th>
			<th><s:message
					code="exampapers.info.question_subjects_operation" /></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="potentialQuestionSubject"
			items="${potentialQuestionSubjects}">
			<tr>
				<td>${potentialQuestionSubject.id}</td>
				<td>${potentialQuestionSubject.questionType.description}</td>
				<td>${fn:substring(potentialQuestionSubject.content, 0, 25)}...</td>
				<td>${potentialQuestionSubject.totalScore}</td>
				<td>...</td>
			</tr>
		</c:forEach>
	</tbody>
</table>