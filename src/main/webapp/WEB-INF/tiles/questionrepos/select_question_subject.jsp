<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<div class="page-header">
	<h4>
		<s:message code="global.info.question_repository_setting" />
	</h4>
</div>

<div class="container-fluid">
	<div class="row-fluid">
		<div class="span8">
			<div id="questionSubject" class="panel">
				subject id: ${questionSubject.id} <br /> question type:
				${questionSubject.questionType.description} <br /> creator:
				${questionSubject.user.userId} <br /> <br /> subject content:
				${questionSubject.content} (total score:
				${questionSubject.totalScore}) <br /> <br />
				<c:forEach var="questionHeader"
					items="${questionSubject.questionHeaders}">
					<c:if test="${questionHeader.questionType.name == 'SCQ'}">
					description: ${questionHeader.description } (score: ${questionHeader.score })<br />
					SCQ <br />
					</c:if>
					<c:if test="${questionHeader.questionType.name == 'BFQ'}">
					description: ${questionHeader.description } (score: ${questionHeader.score })<br />
					BFQ <br />
					</c:if>
				</c:forEach>
			</div>
		</div>
		<div class="span4"></div>
	</div>
</div>

<script src="/resources/js/questions.js"></script>
<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
	});
</script>
