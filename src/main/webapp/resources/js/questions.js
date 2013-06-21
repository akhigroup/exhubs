function showQuestionTypes() {
	$.ajax({
		url : '/ajax/questiontypes/show_question_types',
		data : {},
		type : 'get',
		cache : false,
		success : function(response, textStatus, xhr) {
			$('#questionTypes').html(response);
		}
	});
};

function showQuestionSubjects() {
	$.ajax({
		url : '/ajax/questionrepos/show_question_subjects',
		data : {},
		type : 'get',
		cache : false,
		success : function(response, textStatus, xhr) {
			$('#questionSubjects').html(response);
		}
	});
};

function deleteQuestionSubject(deleteId) {
	$.ajax({
		url : '/rest/questionrepos/delete_question_subject',
		data : {
			deleteId : deleteId
		},
		type : 'post',
		cache : false,
		success : function(response, textStatus, xhr) {
			var obj = jQuery.parseJSON(xhr.responseText);
			if (obj.success) {
				showQuestionSubjects();
			} else {
				$('#ajax_error').html(obj.error).show();
			}
		}
	});
};