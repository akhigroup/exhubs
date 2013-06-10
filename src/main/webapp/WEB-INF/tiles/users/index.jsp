<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<div class="page-header">
	<h4>
		<s:message code="global.info.user_setting" />
	</h4>
</div>

<div class="container-fluid">
	<div class="row-fluid">
		<div class="span10">
			<div id="users" class="panel"></div>
		</div>
		<div class="span2"></div>
	</div>
	<div class="row-fluid">
		<div class="span6">
			<button type="button" class="btn btn-primary"
				onclick="location.href='/users/create'">
				<s:message code="users.info.btn.add_user" />
			</button>
		</div>
		<div class="span6"></div>
	</div>
</div>
<br />

<script src="/resources/js/users.js"></script>
<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
		showUsers(1);
	});
</script>
