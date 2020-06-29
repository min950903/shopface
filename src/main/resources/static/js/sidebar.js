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
			async: false,
			success: function(branchList) {
				var html="";
				html +="<select class='form-control ml-4' id='selectBranch'>";
				
				if(branchList.length > 0) {
					for(var i = 0; i < branchList.length; i++) {
						if (i == 0) {
							html += "<option value='" + branchList[i].no +"' selected='selected'>" + branchList[i].name + "</option>";	
						} else {
							html += "<option value='" + branchList[i].no +"'>" + branchList[i].name + "</option>";
						}
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