<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<input type="hidden" name="examEventId" value="${examEventId}" />

<div class="page-header">
	<h4>
		<s:message code="global.info.exam_event_setting" />
	</h4>
</div>

<h4>
	<s:message code="examevents.info.potential_candidates" />
</h4>

<div class="container-fluid">
	<div class="row-fluid">
		<div class="span11">
			<div id="potentialCandidates" class="panel"></div>
		</div>
		<div class="span1"></div>
	</div>
</div>

<h4>
	<s:message code="examevents.info.associated_candidates" />
</h4>

<div class="container-fluid">
	<div class="row-fluid">
		<div class="span11">
			<div id="associatedCandidates" class="panel"></div>
		</div>
		<div class="span1"></div>
	</div>
</div>

<script src="/resources/js/exams.js"></script>
<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
		var examEventId = $('input[name=examEventId]').val();
		showPotentialCandidates();
		showAssociatedCandidates(examEventId);
	});
</script>
