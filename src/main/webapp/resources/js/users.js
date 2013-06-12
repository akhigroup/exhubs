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

function changeUserStatus(updateId, activeFlag, currPageNum) {

	cleanAjaxMessage();

	var updateUserActiveFlagUri = "/rest/users/update_active_flag";
	$.getJSON(updateUserActiveFlagUri, {
		updateId : updateId,
		activeFlag : activeFlag
	}).done(function(obj) {
		if (obj.success) {
			showUsers(currPageNum == null ? 1 : currPageNum);
		} else {
			$('#ajax_error').html(obj.error).show();
		}
	}).fail(function(jqxhr, textStatus, error) {
		var obj = jQuery.parseJSON(jqxhr.responseText);
		$('#ajax_error').html(obj.error).show();
	});
};