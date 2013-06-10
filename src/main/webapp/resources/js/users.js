function showUsers(pageNumber) {
	$.ajax({
		url : '/ajax/users/show_users',
		data : {
			pageNumber : pageNumber == null ? 1 : pageNumber
		},
		type : 'get',
		cache : false,
		success : function(response, textStatus, xhr) {
			$('#users').html(response);
		}
	});
};