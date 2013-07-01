function showExamTypes(pageNumber) {
	$.ajax({
		url : '/ajax/examtypes/show_exam_types',
		data : {
			pageNumber : pageNumber == null ? 1 : pageNumber
		},
		type : 'get',
		cache : false,
		success : function(response, textStatus, xhr) {
			$('#examTypes').html(response);
		}
	});
};

function deleteExamType(deleteId) {
	$.ajax({
		url : '/rest/examtypes/delete_exam_type',
		data : {
			deleteId : deleteId
		},
		type : 'post',
		cache : false,
		success : function(response, textStatus, xhr) {
			var obj = jQuery.parseJSON(xhr.responseText);
			if (obj.resultType == 'SUCCESS') {
				showExamTypes();
			} else if (obj.resultType == 'ERROR') {
				$('#ajax_error').html(obj.errorMessage).show();
			}
		}
	});
};

function showExamPapers(pageNumber) {
	$.ajax({
		url : '/ajax/exampapers/show_exam_papers',
		data : {
			pageNumber : pageNumber == null ? 1 : pageNumber
		},
		type : 'get',
		cache : false,
		success : function(response, textStatus, xhr) {
			$('#examPapers').html(response);
		}
	});
};

function deleteExamPaper(deleteId) {
	$.ajax({
		url : '/rest/exampapers/delete_exam_paper',
		data : {
			deleteId : deleteId
		},
		type : 'post',
		cache : false,
		success : function(response, textStatus, xhr) {
			var obj = jQuery.parseJSON(xhr.responseText);
			if (obj.resultType == 'SUCCESS') {
				showExamPapers();
			} else if (obj.resultType == 'ERROR') {
				$('#ajax_error').html(obj.errorMessage).show();
			}
		}
	});
};


function showAssociatedQuestionSubjects(examPaperId) {
	$.ajax({
		url : '/ajax/exampapers/show_associated_question_subjects',
		data : {
			examPaperId: examPaperId
		},
		type : 'get',
		cache : false,
		success : function(response, textStatus, xhr) {
			$('#associatedQuestionSubjects').html(response);
		}
	});
};
