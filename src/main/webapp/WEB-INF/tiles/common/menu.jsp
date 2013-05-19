<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<div class="navbar navbar-inverse navbar-fixed-top navbar-static-top ">
	<div class="navbar-inner">
		<div class="container">
			<a class="btn btn-navbar" data-toggle="collapse"
				data-target=".nav-collapse"> <span class="icon-bar"></span> <span
				class="icon-bar"></span> <span class="icon-bar"></span>
			</a> <a class="brand" href='<c:url value="/"/>'><s:message
					code="global.info.website_name" /></a>
			<div class="nav-collapse">
				<ul class="nav">
					<li class="active"><a href='<c:url value="/"/>'><s:message
								code="global.info.home" /></a></li>
					<li><a href='<c:url value="#"/>'>Menu 2</a></li>
					<li><a href='<c:url value="#"/>'>Menu 3</a></li>
				</ul>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" charset="utf-8">
	// Bootstrap active/inactive menu
	$(document).ready(function() {
		$('.navbar li').click(function(e) {
			$('.navbar li').removeClass('active');
			var $this = $(this);
			if (!$this.hasClass('active')) {
				$this.addClass('active');
			}
			e.preventDefault();
		});
	});

	
</script>