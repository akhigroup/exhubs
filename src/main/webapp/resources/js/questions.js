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