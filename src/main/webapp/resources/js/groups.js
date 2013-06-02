function showGroups() {
	$.ajax({
		url : '/ajax/groups/show_groups',
		data : {},
		type : 'get',
		cache : false,
		success : function(response, textStatus, xhr) {
			$('#groups').html(response);
		}
	});
};