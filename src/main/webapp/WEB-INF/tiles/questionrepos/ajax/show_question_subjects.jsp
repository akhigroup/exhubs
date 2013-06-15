<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<table class="table table-hover">
	<thead>
		<tr>
			<th><s:message code="questionrepos.info.question_subjects_id" /></th>
			<th><s:message
					code="questionrepos.info.question_subjects_question_type" /></th>
			<th><s:message
					code="questionrepos.info.question_subjects_content" /></th>
			<th><s:message
					code="questionrepos.info.question_subjects_total_score" /></th>
			<th><s:message
					code="questionrepos.info.question_subjects_creator" /></th>
			<th><s:message
					code="questionrepos.info.question_subjects_operation" /></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="questionSubject" items="${questionSubjects}">
			<tr>
				<td>${questionSubject.id}</td>
				<td>${questionSubject.questionType.description}</td>
				<td>${fn:substring(questionSubject.content, 0, 40)}...</td>
				<td>${questionSubject.totalScore}</td>
				<td>${questionSubject.user.userId}</td>
				<td>...</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
