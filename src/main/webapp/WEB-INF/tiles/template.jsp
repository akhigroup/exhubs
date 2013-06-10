<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<!DOCTYPE html>
<html>
<head>
<title><s:message code="global.info.title" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="/resources/css/bootstrap.min.css" rel="stylesheet"
	media="screen" />
<link href="/resources/css/core.css" rel="stylesheet" media="screen" />
<link rel="shortcut icon" href="/resources/img/favicon.ico" />
<style type="text/css">
body {
	padding-top: 60px;
	padding-bottom: 40px;
}
</style>
<script src="/resources/js/jquery.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>
</head>
<body>
	<tiles:insertAttribute name="menu" />
	<div id="container" class="container">

		<c:if test="${not empty info}">
			<div class="alert alert-success">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
				${info}
			</div>
		</c:if>

		<c:if test="${not empty error}">
			<div class="alert alert-error">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
				${error}
			</div>
		</c:if>

		<tiles:insertAttribute name="body" />
	</div>
	<tiles:insertAttribute name="footer" />
</body>
</html>