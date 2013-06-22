<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<h1>This is a demo</h1>

<div id="demo_msg_box"></div>

<form:form id="form" class="form-horizontal" method="post">
	<button type="submit" class="btn btn-primary">Post Form</button>
</form:form>

<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
		$('#demo_msg_box').html('Exhubs');

	});

	
</script>