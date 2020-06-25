var branchNo = 0;

function drawSelectBranch(currentBranchNo) {
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
				html +="<select class='form-control ml-4' id='selectBranch' name='selectBranch'>";
				
				if(branchList.length > 0) {
					for(var i = 0; i < branchList.length; i++) {
						if(currentBranchNo > 0) {
							if(currentBranchNo == branchList[i].no) {
								html += "<option value='" + branchList[i].no +"' selected>" + branchList[i].name + "</option>";
								branchNo = currentBranchNo;
							} else {
								html += "<option value='" + branchList[i].no +"'>" + branchList[i].name + "</option>";
							}
						} else {
							if(i == 0) {
								html += "<option value='" + branchList[i].no +"' 'selected'>" + branchList[i].name + "</option>";
								branchNo = branchList[i].no;
							} else {
								html += "<option value='" + branchList[i].no +"'>" + branchList[i].name + "</option>";
							}
						}
					}
				}
				html += "</select>";
				
				$('#selectBranchDiv').html(html);
			},
			error: function(error) {
				alert(error);
			}
		}); 
	}
}

function moveEmploy() {
	location.href="/employ/" + branchNo;
}

function moveOccupation() {
	location.href="/occupation/" + branchNo;
}