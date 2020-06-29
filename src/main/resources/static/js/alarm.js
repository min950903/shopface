var header = $("meta[name='_csrf_header']").attr("content");
var token = $("meta[name=_csrf]").attr("content");

function drawAlarmList() {
		var url = "/alarm?addresseeId=" + $('#user').html();
		
		$.ajax({
			url: url,
			type: "get",
			headers: {
				"Content-Type": "application/json;charset=UTF-8"
			},
			success: function(data) {
				var html = "";
				if (data.length > 0) {
					for(var i = 0; i < data.length; i++) {
						html += "<a href='#' id='alarmItem' class='list-group-item' onclick='deleteAlarm("+ data[i].no +")'>"
						html += "<div class='row no-gutters align-items-center' id='currentAlarm'>";
						html += 	"<input type='hidden' value='" + data[i].no + "' name='alarmNo'>";
						html += 	"<div class='col-2'></div>";
						//추후 그림 추가
						html += 	"<div class='col-10'>";
						html += 	"<div class='text-dark'>" + data[i].type  +"</div>";
						html += 	"<div class='text-muted small mt-1'>"+ data[i].contents +"</div>";
						html += 	"<div class='text-muted small mt-1'>"+ data[i].registerDate +"</div>";
						html += "</div>";
						html += "</div>";
						html += "</a>";
					}
				} else {
					html += "<div class='row no-gutters align-items-center'><div class='col-12'><div class='text-center'>알람이 없습니다.</div></div></div>";
				}
				$('#alarmSpace').html(html);
			},
			error: function (request, status, error) {
				console.log("Code:" + request.status + "\n" + "message:" + request.responseText + "\n" +
					"error:" + error);
			}
		})
	}

	function deleteAlarm(alarmNo) {
		 var url = "/alarm?no=" + alarmNo;
		
		$.ajax({
			url: url,
			type:'delete',
			contentType: "application/json;charset=UTF-8",
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			success: function(data) {
				$('#alertsDropdown').trigger('click');
			}
		});
	}