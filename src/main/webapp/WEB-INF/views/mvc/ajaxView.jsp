<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="${cp }/js/jquery-3.3.1.min.js?v=2"></script>
<script>
	$(document).ready(function() {
		console.log("ajaxView.jsp");

		//jsonData요청
		$("#jsonReqBtn").on("click", function() {
			responseBody();
		});
	});

	function responseBody(){
		var data = { userId : "brown", userNm : "브라운"};
		
		$.ajax({
			url : "${cp}/ajax/requestBody",
			method : "post",
//			data : "user=borwon&userNm=브랑누",
//			data : $("#frm").serialize(),
			data : JSON.stringify(data),		//data를 json 문자열로 전송한다
			dataType : "json",	//server에게 희망하는 리턴타입을 명시
			contentType : "application/json; charset=utf-8",
			success : function(data) {
				$("#jsonRecvTbody").html("<tr><td>" + data.userId + "</td></tr>");
				
/*
				var text = "";

				for (var i = 0; i < data.length; i++) {
					text += "<tr>";
					text += "	<td>" + data[i] + "</td>";
					text += "</tr>";
				}
				$("#jsonRecvTbody").html(text);
				
				*/
			}
		});
	}

	function jsonView() {
		$.ajax({

			url : "${cp}/ajax/jsonView",
			method : "post",
			success : function(data) {
				console.log("data : " + data);

				renderTable(data);
			}
		});
	}
</script>
</head>
<body>
<form id="frm">
	<input type="text"name="userId" value="brown"/>
	<input type="text"name="userNm" value="브라운"/>
</form>
	<h2>ajaxView.jsp</h2>
	<h3>json 수신</h3>
	<div>
		<button id="jsonReqBtn">jsonData요청</button>
		<div id="jsonRecv">
			<table>
				<thead>
					<tr>
						<th>이름</th>
					</tr>
				</thead>
				<tbody id="jsonRecvTbody">

				</tbody>
			</table>
		</div>
	</div>

</body>
</html>