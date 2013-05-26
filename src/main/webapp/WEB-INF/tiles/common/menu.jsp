<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>

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
					<li class="a-c-t-i-v-e"><a href='<c:url value="/"/>'><s:message
								code="global.info.home" /></a></li>
					<sec:authorize ifNotGranted="ROLE_USER">
						<li><a href='<c:url value="/login"/>'><s:message
									code="global.info.login" /></a></li>
					</sec:authorize>
					<sec:authorize access="hasRole('ROLE_USER')">
						<li><a href='<c:url value="/logout"/>'><s:message
									code="global.info.log_out" /></a></li>
					</sec:authorize>
					<li><a href='<c:url value="#"/>'>Menu 1</a></li>
				</ul>
			</div>
		</div>
	</div>
</div>

<!-- 
<script type="text/javascript" charset="utf-8">
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
 -->