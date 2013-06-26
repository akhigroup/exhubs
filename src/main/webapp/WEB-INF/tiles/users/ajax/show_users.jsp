<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:url var="firstUrl" value="javascript:showUsers(1);" />
<c:url var="lastUrl" value="javascript:showUsers(${totalPages});" />
<c:url var="prevUrl" value="javascript:showUsers(${currentIndex - 1});" />
<c:url var="nextUrl" value="javascript:showUsers(${currentIndex + 1});" />

<c:set var="nav_first_page">
	<s:message code="global.info.page_first" />
</c:set>
<c:set var="nav_last_page">
	<s:message code="global.info.page_last" />
</c:set>
<c:set var="nav_prev_page">
	<s:message code="global.info.page_previous" />
</c:set>
<c:set var="nav_next_page">
	<s:message code="global.info.page_next" />
</c:set>

<c:if test="${fn:length(users) == 0}">
	<div class="alert alert-info">
		<button type="button" class="close" data-dismiss="alert">&times;</button>
		<s:message code="global.info.no_records" />
	</div>
</c:if>
<c:if test="${fn:length(users) > 0}">
	<table class="table table-hover">
		<thead>
			<tr>
				<th><s:message code="users.info.users_userid" /></th>
				<th><s:message code="users.info.users_name" /></th>
				<th><s:message code="users.info.users_email" /></th>
				<th><s:message code="users.info.users_group" /></th>
				<th><s:message code="users.info.users_create_date" /></th>
				<th><s:message code="users.info.users_status" /></th>
				<th><s:message code="users.info.users_operation" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="user" items="${users}">
				<tr>
					<td>${user.userId}</td>
					<td>${user.name}</td>
					<td>${user.email}</td>
					<td>${user.group.description}</td>
					<td><fmt:formatDate pattern="yyyy-MM-dd"
							value="${user.createDate}" /></td>
					<td><c:if test="${user.activeFlag}">
							<s:message code="global.info.active" />
						</c:if> <c:if test="${not user.activeFlag}">
							<s:message code="global.info.inactive" />
						</c:if></td>
					<td><c:if test="${user.activeFlag}">
							<button class="btn btn-danger btn-mini"
								onclick="changeUserStatus(${user.id}, false, ${currentIndex});">
								<s:message code="global.info.btn.deactivate" />
							</button>
						</c:if> <c:if test="${not user.activeFlag}">
							<button class="btn btn-success btn-mini"
								onclick="changeUserStatus(${user.id}, true, ${currentIndex});">
								<s:message code="global.info.btn.activate" />
							</button>
						</c:if>
						<button class="btn btn-link btn-mini"
							onclick="location.href='/user/edit/${user.id}'">
							<s:message code="global.info.btn.edit" />
						</button></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<div class="pagination pagination-small">
		<ul>
			<c:choose>
				<c:when test="${currentIndex == 1}">
					<li class="disabled"><a href="#">${nav_first_page}</a></li>
					<li class="disabled"><a href="#">${nav_prev_page}</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="${firstUrl}">${nav_first_page}</a></li>
					<li><a href="${prevUrl}">${nav_prev_page}</a></li>
				</c:otherwise>
			</c:choose>
			<c:forEach var="i" begin="${beginIndex}" end="${endIndex}">
				<c:url var="pageUrl" value="javascript:showUsers(${i});" />
				<c:choose>
					<c:when test="${i == currentIndex}">
						<li class="active"><a href="${pageUrl}"><c:out
									value="${i}" /></a></li>
					</c:when>
					<c:otherwise>
						<li><a href="${pageUrl}"><c:out value="${i}" /></a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:choose>
				<c:when test="${currentIndex == totalPages}">
					<li class="disabled"><a href="#">${nav_next_page}</a></li>
					<li class="disabled"><a href="#">${nav_last_page}</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="${nextUrl}">${nav_next_page}</a></li>
					<li><a href="${lastUrl}">${nav_last_page}</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
</c:if>