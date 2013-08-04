<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<table class="table table-hover">
	<thead>
		<tr>
			<th><s:message code="reviewexams.info.question_subject_id" /></th>
			<th><s:message code="reviewexams.info.question_type" /></th>
			<th><s:message code="reviewexams.info.content" /></th>
			<th><s:message code="global.info.operation" /></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="questionSubject" items="${questionSubjects}">
			<tr>
				<td>${questionSubject.id}</td>
				<td>${questionSubject.questionType.description}</td>
				<td>${fn:substring(questionSubject.content, 0, 25)}...</td>
				<td>
					<button class="btn btn-info btn-mini"
						onclick="location.href='/review_question_subject/${questionSubject.id}?examEventId=${examEventId}'">
						<s:message code="reviewexams.info.btn.review_question_subject" />
					</button>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
