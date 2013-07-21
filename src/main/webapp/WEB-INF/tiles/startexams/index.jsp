<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<form:form id="form" class="form-horizontal" method="post"
	modelAttribute="submitExamPaperFormBean">
	<fieldset>
		<legend>
			<s:message code="global.info.start_exam" />
			- ${examEvent.name}
		</legend>

		<input type="hidden" name="examEventId" value="${examEvent.id}" /> <input
			type="hidden" name="userId" value="${userId}" />

		<div id="examEventPanel" class="panel">
			<s:message code="examevents.info.examevents_user_id" />
			: ${examEvent.user.userId} <br />
			<s:message code="examevents.info.examevents_start_datetime" />
			:
			<fmt:formatDate pattern="yyyy-MM-dd HH:mm"
				value="${examEvent.startDateTime}" />
			<br />
			<s:message code="examevents.info.examevents_end_datetime" />
			:
			<fmt:formatDate pattern="yyyy-MM-dd HH:mm"
				value="${examEvent.endDateTime}" />
			<br />
			<s:message code="exampapers.info.question_subjects_total_score" />
			: ${examEvent.examPaper.totalScores} <br /> <br />
		</div>

		<c:forEach var="questionSubject"
			items="${examEvent.examPaper.questionSubjects}"
			varStatus="question_subject_status">
			<div>
				<h5>${question_subject_status.count}.${questionSubject.content}</h5>

				<c:if test="${not empty questionSubject.imageId}">
					<img src="/image/${questionSubject.imageId}">
					<br />
				</c:if>

				<c:forEach var="questionHeader"
					items="${questionSubject.questionHeaders}"
					varStatus="question_header_status">
					<input type="hidden"
						name="submitQuestionHeaderBeans[${question_header_status.index}].questionHeaderId"
						value="${questionHeader.id}" />
					<c:if
						test="${questionHeader.questionType.name == 'SCQ' or questionHeader.questionType.name == 'MCQ'}">
					${question_header_status.index + 1}) ${questionHeader.description } (<s:message
							code="questionrepos.info.question_subject_header_score"
							arguments="${questionHeader.score}" />)<br />
						<c:forEach var="questionDetail"
							items="${questionHeader.questionDetails}"
							varStatus="question_detail_status">
							<div>
								<label class="radio"> <input type="radio"
									name='submitQuestionHeaderBeans[${question_header_status.index}].radioSelectedIndex'
									value='${question_detail_status.index}'>
									${questionChoices[question_detail_status.index]}.
									${questionDetail.content}
								</label>
							</div>
						</c:forEach>
					</c:if>
					<br />
				</c:forEach>

			</div>
		</c:forEach>

		<div class="form-actions">
			<button type="submit" class="btn btn-primary">
				<s:message code="global.info.btn.submit" />
			</button>
		</div>
	</fieldset>
</form:form>
