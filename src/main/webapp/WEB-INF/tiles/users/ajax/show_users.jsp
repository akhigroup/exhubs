<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<table class="table table-hover">
	<thead>
		<tr>
			<th><s:message code="users.info.users_userid" /></th>
			<th><s:message code="users.info.users_name" /></th>
			<th><s:message code="users.info.users_email" /></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="userBean" items="${userBeans}">
			<tr>
				<td>${userBean.userId}</td>
				<td>${userBean.name}</td>
				<td>${userBean.email}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
