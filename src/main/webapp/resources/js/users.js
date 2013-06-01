function showUsers() {
	$.ajax({
		url : '/ajax/users/show_users',
		data : {},
		type : 'get',
		cache : false,
		success : function(response, textStatus, xhr) {
			$('#users').html(response);
		}
	});
};