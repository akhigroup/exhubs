<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container-fluid">
	<div class="row-fluid">
		<div class="span8">
			<div id="users" class="panel"></div>
		</div>
		<div class="span4"></div>
	</div>
</div>

<script src="/resources/js/users.js"></script>
<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
		showUsers();
	});
</script>
