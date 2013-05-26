<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<c:set var="website_name">
	<s:message code="global.info.website_name" />
</c:set>

<div class="hero-unit">
	<h1>
		<s:message code="site.index.info.hero_unit_welcome"
			arguments="${website_name}" />
	</h1>
	<p>
		<s:message code="site.index.info.hero_unit_desc"
			arguments="${website_name}" />
	</p>
	<p>
		<a class="btn btn-primary" href='<c:url value="/login"/>'><s:message
				code="site.index.info.btn.log_in" /></a>
	</p>
</div>