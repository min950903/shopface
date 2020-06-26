function drawSelectBranch() {
	if(userId != null && userId != '') {
		$.ajax({
			url: '/branch',
			type:'GET',
			data: {
				memberId: userId
			},
			dataType: 'JSON',
			contentType : 'application/json;charset=UTF-8',
			success: function(branchList) {
				var html="";
				html +="<select class='form-control ml-4' id='selectBranch'>";
				
				if(branchList.length > 0) {
					for(var i = 0; i < branchList.length; i++) {
						html += "<option value='" + branchList[i].no +"'>" + branchList[i].name + "</option>";
					}
				}
				html +="</select>";
				
				$('#selectBranchDiv').html(html);
			},
			error: function(error) {
				alert(error);
			}
		}); 
	}
}