<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>	

				<h1 class="page-header">전체 사용자 리스트(ajax Tiles)</h1>
				<!-- userList 정보를 화면에 출력하는 로직 작성 -->

				<div class="table-responsive">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>#</th>
								<th>사용자 아이디</th>
								<th>사용자 이름</th>
								<th>별명</th>
								<th>등록일시</th>
							</tr>
						</thead>
						<tbody id="userListTbody">
							
						</tbody>
					</table>
					
					<form action="${pageContext.request.contextPath }/user/userForm" method="get">
						<button type="submit" class="btn btn-default">사용자 등록</button>
					</form>
					
					
					
				<c:set var="lastPage"
				value="${Integer(userCnt/pageSize + (userCnt%pageSize > 0 ? 1 : 0))}"/>
					
					<nav style="text-align:center;">
						<ul id="pagination" class="pagination">
						
						</ul>
					</nav>
				</div>
	








	<script>
	//사용자 배열을 이용하여 사용자 리스트 HTML을 생성
	function makeUserList(userList){
		var html = "";
		
		for(var i = 0; i<userList.length; i++){
			var user = userList[i];
			html += "<tr class='userTr' data-userid='"+ user.userId +"'>";
			html += "	<td></td>";	
			html += "	<td>" + user.userId + "</td>";
			html += "	<td>" + user.userNm + "</td>";
			html += "	<td></td>";
			html += "	<td>" + user.reg_dt_fmt + "</td>";
			html += "</tr>";
		}
		
		$("#userListTbody").html(html);
	}
	
	function makePagination(userCnt, pageSize, page){
		pageSize =1;
		var lastPage = parseInt(userCnt/pageSize) + (userCnt%pageSize > 0 ? 1: 0);

		var html = "";
		
		if(page == 1){
			html +="<li class='disabled'>";
			html +="	<a aria-label='Previous'>";
			html +="		<span aria-hidden='true'>&laquo;</span>";
			html +="	</a>";
			html +="</li>";
		}
		else{
			html +="<li>";
//			html +="	<a href='${cp}/user/userPagingList' aria-label='Previous'>";
			html +="	<a href='javascript:getUserPageList(1)' aria-label='Previous'>";
			html +="		<span aria-hidden='true'>&laquo;</span>";
			html +="	</a>";
			html +="</li>";
		}
		for(var i =1 ; i<=lastPage;i++){
			var active ="";
			if(i==page)
				active = "active";
			
			html += "<li class='" + active + "'>";
//			html += "	<a href='${cp}/user/userPagingList?page="+i+"'>"+i+"</a>";
			html += "	<a href='javascript:getUserPageList("+i+");'>"+i+"</a>";
			html += "</li>";
		}
		if(page == lastPage) {
		      html += "<li class='disabled'>";
		      html += "   <a aria-label='Next'>";
		      html += "      <span aria-hidden='true'>&raquo;</span>";
		      html += "   </a>";
		      html += "</li>";
		   }else{
		      html += "<li>";
//		       html += "   <a href='${cp }/user/userPagingList?page="+lastPage+"' aria-label='Previous'>";
		       html += "   <a href='javascript:getUserPageList("+lastPage+");' aria-label='Previous'>";
		       html += "       <span aria-hidden='true'>&raquo;</span>";
		       html += "   </a>";
		      html += "</li>";
		   }
		
		$("#pagination").html(html);

	}


	
	function getUserPageList(page){
		$.ajax({
			url : "${cp}/user/userPagingListAjax",
			data : "page="+page,
			success : function(data){
				makeUserList(data.userList);
				makePagination(data.userCnt, data.pageSize, data.page);
//				makePagination(data.userCnt, 1, data.page);
			}
		});	
	}
	
	function getUserPageListHtml(page){
		$.ajax({
			url : "${cp}/user/userPagingListAjaxHtml",
			data : "page="+page,
			success : function(data){
/*	
			사용자 리스트 html...
			=======seperator=======
			페이지네이션 html
*/
			var htmlArr = data.split("==============seperator=================");
			
			$("#userListTbody").html(htmlArr[0]);
			$("#pagination").html(htmlArr[1]);
        
        // * ajax를 통한 html 생성시 이벤트 핸들러 등록 방법 2.
        // ajax callback 안에 클릭 이벤트를 넣어줌.
		        $(".userTr").on("click", function(){
		           var userId = $(this).data("userid");
		           
		           $("#userId").val(userId);
		           $("#frm").submit();
		        });
			}
		});
		
	}
		//문서로딩이 완료된 이후 이벤트 등록
		$(document).ready(function() {
			console.log("document ready");
			
//			getUserPageList(1,10);
			getUserPageListHtml(1);
			
			//msg 속성이 존재하면 alert, 존재하지 않으면 넘어가기
			<c:if test="${msg != null}">
				alert("${msg}");
				<%session.removeAttribute("msg");%>
			</c:if>
			

	         // * ajax를 통한 html 생성시 이벤트 핸들러 등록 방법 두가지.
	         // 1. html이 ajax호출에 의해 정상적으로 생성된이후 클릭 이벤트 핸들러를 등록.
	         // (success -> 사용자 html이 생성된 이후에 등록)
	         // 2. 이벤트 핸들러 대상을 변경.(.userTr -> #userListTbody) 동적으로 생성되는
	         // html을 감싸는 영역에 이벤트를 등록. 단, on 옵션에서 감싸는 영역 안에 처리되어야
	         // 할 selector를 명시. (기존 : $(".userTr").on("click", function(){))
	         $("#userListTbody").on("click", ".userTr", function(){
	            var userId = $(this).data("userid");
	            
	            $("#userId").val(userId);
	            $("#frm").submit();
	         });
		});
	</script>

	<form id="frm" action="${pageContext.servletContext.contextPath }/user/user"
		method="get">
		<input type="hidden" id="userId" name="userId" />
	</form>

</body>
</html>








