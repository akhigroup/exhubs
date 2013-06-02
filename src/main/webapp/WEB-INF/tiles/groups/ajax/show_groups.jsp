<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<table class="table table-hover">
	<thead>
		<tr>
			<th><s:message code="groups.info.groups_name" /></th>
			<th><s:message code="groups.info.groups_description" /></th>
			<th><s:message code="groups.info.groups_operation" /></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="groupBean" items="${groupBeans}">
			<tr>
				<td>${groupBean.name}</td>
				<td>${groupBean.description}</td>
				<td>
					<button class="btn btn-link btn-mini"
						onclick="location.href='/group/${groupBean.id}'">
						<s:message code="groups.info.groups_view" />
					</button>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
