<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container-fluid">
	<div class="row-fluid">
		<div class="span8">
			<div id="groups" class="panel"></div>
		</div>
		<div class="span4"></div>
	</div>
</div>

<script src="/resources/js/groups.js"></script>
<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
		showGroups();
	});
</script>
